const {SUCCESS,SUCCESS2,SERVER_CRASH,NOT_FOUND} = require('../utils/config').STATUS_CODES;
const uniqid = require('uniqid');
var sendmail = require('../utils/nodemailer');
const emailBundle = require('../locales/mailcontent');
const messageBundle = require('../locales/en');
const adminOperations = require('../db/services/admin_crud');
const otp_generate = require('otp-generator');
const jwt = require('../utils/token');
const encryption = require('../utils/encrypt');
const deleted_adminController = require('./deleted_admin');
const adminController = {
    async check_email(request,response){
        try{
            var email = request.body.email;
            var check = await adminOperations.find_by_email(email)
            if(check==null){
                response.status(SUCCESS).json({message:messageBundle['email.not_used']});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['email.already_used']});
            }
        }
        catch(err){
                response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    register(request,response){
        var full_name = request.body.name;
        var fname = full_name.split(" ");
        var key_=jwt.generatekey();
        let adminObject={
            emailid:request.body.email,
            password:request.body.pwd,
            old_pass:request.body.pwd,
            name:request.body.name,
            admin_id:uniqid(fname[0]),
            isadmin:0,
            address:{
                Houseno:request.body.address.Houseno,
                City:request.body.address.City,
                State:request.body.address.State,
                Pincode:request.body.address.Pincode,
                Landmark:request.body.address.Landmark
        },
        account_activated:0,
        key:key_,
        two_factor_auth:0,
        otp_checked:0
        };
        var promise = adminOperations.register(adminObject);
        promise.then((doc)=>{
            sendmail(request.body.email,emailBundle['registersuccessfull.sub'],emailBundle['adminregistersuccessfull.body']+key_);
            response.status(SUCCESS).json({message:messageBundle['register.welcome'],doc:doc});
        }).catch((err)=>{
            response.status(SERVER_CRASH).json({message:messageBundle['register.fail'],ERROR:err})
        });
    },
    async activate_acc(request,response){
        try{
            var key = request.query.key;
            var activate = await adminOperations.activate_admin_acc(key);
            if(activate.modifiedCount && key){
                var admin = await adminOperations.find_by_key(key);
                sendmail(admin.emailid,emailBundle['activatesuccessfull.sub'],emailBundle['activatesuccessfull.body']);
                response.status(SUCCESS).json({message:messageBundle['activate.successful']});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['activate.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async login(request,response){
        try{
            var doc = request.body;
            var admin = await adminOperations.login(doc);
            var old_pass = await adminOperations.check_old_pass(doc.email,doc.pwd);
            if(admin){
                if(admin.account_activated==1){
                    let token = jwt.generateToken(admin);    //generate token here
                    if(admin.two_factor_auth==1){
                        var otp = otp_generate.generate(6, {lowerCaseAlphabets:false, upperCaseAlphabets: false, specialChars: false });
                        var save_key = await adminOperations.save_key(admin.emailid,otp);
                        var uncheck_otp = await adminOperations.uncheck_otp(admin.admin_id);
                        if(save_key.modifiedCount && uncheck_otp.modifiedCount){
                            sendmail(admin.emailid,emailBundle['login.sub'],emailBundle['login.body']+" OTP for login is "+otp);
                            response.status(SUCCESS).json({message:messageBundle['login.welcome'],name:admin.name,token:token,two_factor_authentication:1,OtP_SENT:1});
                            }
                        else{
                            response.status(SERVER_CRASH).json({message:messageBundle['opt.was_not_send'],name:admin.name,two_factor_authentication:1,OtP_SENT:0});
                        }
                    }
                    else{
                        sendmail(admin.email,emailBundle['login.sub'],emailBundle['login.body']);
                        response.status(SUCCESS).json({message:messageBundle['login.welcome'],name:admin.name,token:token,two_factor_authentication:0});
                    }
                }
                else{
                    response.status(NOT_FOUND).json({message:messageBundle['account.not_activated']});
                }
            }
            else if(old_pass){
                response.status(SUCCESS2).json({message:messageBundle['old_pass.success'],name:old_pass.name});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['login.invaliduser']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async forgot_pass(request,response){
        try{
            var email = request.body.email;
            var admin = await adminOperations.find_by_email(email);
            if(admin){    
                var key = jwt.generatekey();
                var save_key = await adminOperations.save_key(email,key);
                if(save_key.modifiedCount){
                        sendmail(email,emailBundle['forgot_pass.sub'],emailBundle['forgot_pass_admin.body']+key);
                        response.status(SUCCESS).json({message:messageBundle['forgotpass.success']});
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['forgotpass.fail']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['email.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async acc_recover(request,response){
        try{
            var password = request.body.pwd;
            var key = request.query.key;
            var check_key = await adminOperations.find_by_key(key);
            if(check_key){
                var save_old_pass = await adminOperations.save_old_pass(check_key.emailid);
                var update_pass = await adminOperations.update_pass_for_recovery(check_key.admin_id,password);
                if(update_pass.modifiedCount && save_old_pass.modifiedCount){
                    sendmail(check_key.emailid,emailBundle['account_recover.sub'],emailBundle['account_recover.body']);
                    response.status(SUCCESS).json({message:messageBundle['acc_recovery.success']});
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['acc_recovery.fail']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['acc_recovery.fail']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async acc_recover_by_old_pass(request,response){
        try{
            var email = request.body.email;       
            var old_pass = request.body.old_pass;
            var new_pass = request.body.new_pass;
            var check_old_pass = await adminOperations.check_old_pass(email,old_pass);
            if(check_old_pass){
                var save_old_pass = await adminOperations.save_old_pass(email);
                var update_pass = await adminOperations.update_pass_by_email(email,new_pass);
                if(update_pass.modifiedCount && save_old_pass.modifiedCount){
                    sendmail(email,emailBundle['account_recover.sub'],emailBundle['account_recover.body']);
                    response.status(SUCCESS).json({message:messageBundle['acc_recovery.success']});
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['acc_recovery.fail']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['old_pass.fail']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_my_account(request,response){
        try{
            var token = request.headers['authorization'];
            var doc=jwt.getdoc(token);
            var admin  = await adminOperations.view_my_account(doc.admin_id);
            if(admin){
                response.status(SUCCESS).json({message:messageBundle['admin.found'],admin:admin});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['admin.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async activate_two_factor_auth(request,response){
        try{
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var updated = await adminOperations.activate_two_factor_auth(admin.admin_id);
            if(updated.modifiedCount){
                var admin = await adminOperations.find_admin_by_admin_id(admin.admin_id);
                var new_token = jwt.generateToken(admin);
                sendmail(admin.emailid,emailBundle['activate_two_factor_auth.sub'],emailBundle['activate_two_factor_auth.body']);
                response.status(SUCCESS).json({message:messageBundle['activate_two_factor_auth.success'],token:new_token});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['activate_two_factor_auth.fail']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async check_otp(request,response){
        try{
            var otp = request.body.otp;
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var check_otp = await adminOperations.check_otp(admin.admin_id,otp);
            var opt_checked=await adminOperations.otp_checked(admin.admin_id);
            if(check_otp && opt_checked.modifiedCount){
                admin.otp_checked=1
                var new_token = jwt.generateToken(admin);
                response.status(SUCCESS).json({message:messageBundle['otp.verified'],token:new_token});
            }
            else{
                sendmail(admin.emailid,emailBundle['acc_compromised.sub'],emailBundle['acc_compromised.body']);
                response.status(NOT_FOUND).json({message:messageBundle['otp.wrong']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async deactivate_two_factor_auth(request,response){
        try{
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var updated = await adminOperations.deactivate_two_factor_auth(admin);
            if(updated.modifiedCount){
                var admin = await adminOperations.find_admin_by_admin_id(admin.admin_id);
                var new_token = jwt.generateToken(admin);
                sendmail(admin.emailid,emailBundle['deactivate_two_factor_auth.sub'],emailBundle['deactivate_two_factor_auth.body']);
                response.status(SUCCESS).json({message:messageBundle['deactivate_two_factor_auth.success'],token:new_token});                
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['deactivate_two_factor_auth.fail']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async authorize_admin(request,response){
        try{
            var admin_id = request.body.admin_id;
            var token = request.headers['authorization'];
            var admin = jwt.getdoc(token)
            let authorized_by = {
                name : admin.name,
                email : admin.emailid,
                admin_id : admin.admin_id
            }
            var updated = await adminOperations.authorize_admin(admin_id,authorized_by);
            if(updated.modifiedCount && admin_id){
                var find = await adminOperations.find_admin_by_admin_id(admin_id);
                sendmail(find.emailid,emailBundle['authorizationsuccessful.sub'],emailBundle['authorizationsuccessful.body']);
                response.status(SUCCESS).json({message:messageBundle['update.successful']});              
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['update.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async unauthorize_admin(request,response){
        try{
            var admin_id = request.body.admin_id;
            var token = request.headers['authorization'];
            var admin = jwt.getdoc(token)
            let unauthorized_by = {
                name : admin.name,
                email : admin.emailid,
                admin_id : admin.admin_id
            }
            var updated = await adminOperations.unauthorize_admin(admin_id,unauthorized_by);
            if(updated.modifiedCount && admin_id){
                var find = await adminOperations.find_admin_by_admin_id(admin_id);
                sendmail(find.emailid,emailBundle['unauthorizationsuccessful.sub'],emailBundle['unauthorizationsuccessful.body']);
                response.status(SUCCESS).json({message:messageBundle['update.successful']});                
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['update.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_all(request,response){
        try{
            var alladmins = await adminOperations.find_all();
            if(alladmins){
                response.status(SUCCESS).json({message:messageBundle['admin.found'],Adminfound:alladmins});                
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['admin.notfound']});                
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_name(request,response){
        try{
            var name = request.body.name;
            var admin = await adminOperations.find_by_name(name);
            if(admin && name){
                response.status(SUCCESS).json({message:messageBundle['admin.found'],admin:admin});               
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['admin.notfound']});                
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_email(request,response){
        try{
            var email = request.body.email;
            var admin = await adminOperations.find_by_email(email);
            if(admin && email){
                response.status(SUCCESS).json({message:messageBundle['admin.found'],admin:admin});               
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['admin.notfound']});                
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_admin_id(request,response){
        try{
            var admin_id = request.body.admin_id;
            var admin = await adminOperations.find_admin_by_admin_id(admin_id);
            if(admin && admin_id){
                response.status(SUCCESS).json({message:messageBundle['admin.found'],admin:admin});               
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['admin.notfound']});                
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async update_name(request,response){
        try{
            var name = request.body.name;
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var updated = await adminOperations.update_name(admin.admin_id,name);
            if(updated.modifiedCount && name){
                admin.name = name;
                var new_token = jwt.generateToken(admin);
                response.status(SUCCESS).json({message:messageBundle['update.successful'],name:name,token:new_token});                
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['update.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async update_email(request,response){
        try{
            var email = request.body.email;
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var updated = await adminOperations.update_email(admin.admin_id,email);
            if(updated.modifiedCount && email){
                admin.emailid = email;
                var new_token = jwt.generateToken(admin);
                sendmail(admin.emailid,emailBundle['emailupdated.sub'],emailBundle['emailupdated.body']);
                response.status(SUCCESS).json({message:messageBundle['update.successful'],email:email,token:new_token});                
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['update.unsuccessful']});
            }
        }
        catch(err){
            response.status(NOT_FOUND).json({ERROR:err});
        }
    },
    async update_address(request,response){
        try{
            var Houseno = request.body.Houseno;
            var City = request.body.City;
            var State = request.body.State;
            var Pincode = request.body.Pincode;
            var Landmark = request.body.Landmark
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var updated = await adminOperations.update_address(admin.admin_id,Houseno,City,State,Pincode,Landmark);
            if(updated.modifiedCount){
                response.status(SUCCESS).json({message:messageBundle['update.successful']});               
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['update.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async update_password(request,response){
        try{
            var old_pass = request.body.old_pass;
            var new_pass = request.body.new_pass;
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            var save_old_pass = await adminOperations.save_old_pass(admin.emailid);
            var updated = await adminOperations.update_pass(admin,old_pass,new_pass);
            if(updated.modifiedCount && save_old_pass.modifiedCount){
                admin.password = encryption.generateHash(new_pass);
                var new_token = jwt.generateToken(admin);
                sendmail(admin.emailid,emailBundle['passwordchanged.sub'],emailBundle['passwordchanged.body']);
                response.status(SUCCESS).json({message:messageBundle['update.successful'],token:new_token});                   
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['update.unsuccessful'],Error:"Old Passowrd was wrong"});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async delete_admin(request,response){
        try{
            var token = request.headers['authorization'];
            var admin=jwt.getdoc(token);
            pass = request.body.pwd;
            let obj = {
                email : admin.email,
                pwd : pass
            }
            var deletedby = "SELF_ADMIN";
            var find = await adminOperations.login(obj);
            var deleted = await adminOperations.delete(admin,pass);
            deleted_adminController.add_to_deleted(find,deletedby);
            if(deleted.deletedCount){
                sendmail(admin.emailid,emailBundle['acc_deleted.sub'],emailBundle['acc_deleted.body']);
                response.status(SUCCESS).json({message:messageBundle['delete.successful']});                
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['delete.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async delete_any_admin(request,response){
        try{
            var admin_id = request.body.admin_id;
            var find = await adminOperations.find_admin_by_admin_id(admin_id);
            var token = request.headers['authorization'];
            var admin = jwt.getdoc(token)
            let deletedby = {
                name : admin.name,
                email : admin.emailid,
                admin_id : admin.admin_id
            }
            var deleted = await adminOperations.deleteanyadmin(admin_id);
            deleted_adminController.add_to_deleted(find,deletedby);
            if(deleted.deletedCount){
                sendmail(find.emailid,emailBundle['acc_deletedbyadmin.sub'],emailBundle['acc_deletedbyadmin.body']);
                response.status(SUCCESS).json({message:messageBundle['delete.successful']});               
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['delete.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    
};
module.exports = adminController;