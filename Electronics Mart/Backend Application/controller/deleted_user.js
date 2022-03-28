const {SUCCESS,SERVER_CRASH,NOT_FOUND} = require('../utils/config').STATUS_CODES;
const messageBundle = require('../locales/en');
const deleted_userOperations = require('../db/services/deleted_user_crud');
const deleted_userController = {
    add_to_deleted(userObject,deletedby){
        let deleted_userObject={
            emailid:userObject.emailid,
            password:userObject.password,
            old_pass:userObject.old_pass,
            name:userObject.name,
            user_id:userObject.user_id,
            address:userObject.address,
            interested_in:userObject.interested_in,
            date:new Date(),
            deleted_by:deletedby
        }
        var promise = deleted_userOperations.add_to_deleted(deleted_userObject);
        promise.then((doc)=>{
            console.log("user : ",doc.name," is added to deleted users");
        }).catch((err)=>{
            console.log("Can't add user : ",doc.name," to deleted users because of this error ",err);
        });
    },
    async login(request,response){                  //not created url but just incase needed.
        try{
            var user = request.body;
            var doc = await deleted_userOperations.login(user);
            if(doc){
                //generate token here
                    let token = jwt.generateToken(doc);
                            response.status(SUCCESS).json({message:messageBundle['login.welcome'],name:doc.name,token:token});    
                    } 
            else{
                response.status(NOT_FOUND).json({message:messageBundle['login.invaliduser']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_all(request,response){
        try{
            var allusers = await deleted_userOperations.find_all();
            if(allusers){
                response.status(SUCCESS).json({message:messageBundle['users.viewall'],AllUsers:allusers});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['users.null']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_user_id(request,response){
        try{
            var user_id = request.body.user_id;
            var user = await deleted_userOperations.find_user(user_id);
            if(user){
                response.status(SUCCESS).json({message:messageBundle['users.viewall'],user:user});   
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['users.null']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_emailid(request,response){
        try{
            var email = request.body.email;
            var user = await deleted_userOperations.find_by_email(email);
            if(user){
                response.status(SUCCESS).json({message:messageBundle['user.found'],AllUsers:user});  
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['user.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_name(request,response){
        try{
            var name = request.body.name;
            var allusers = await deleted_userOperations.find_by_name(name);
            if(allusers!=null){
                response.status(SUCCESS).json({message:messageBundle['user.found'],AllUsers:allusers}); 
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['user.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_pincode(request,response){
        try{
            var pincode = request.query.pincode;
            var allusers = await deleted_userOperations.find_by_pincode(pincode);
            if(allusers!=null){
                response.status(SUCCESS).json({message:messageBundle['user.found'],AllUsers:allusers});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['user.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_city(request,response){
        try{
            var city = request.query.city;
            var allusers = await deleted_userOperations.find_by_city(city);
            if(allusers!=null){
                response.status(SUCCESS).json({message:messageBundle['user.found'],AllUsers:allusers});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['user.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_state(request,response){
        try{
            var state = request.query.state;
            var allusers = await deleted_userOperations.find_by_state(state);
            if(allusers!=null){
                response.status(SUCCESS).json({message:messageBundle['user.found'],AllUsers:allusers});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['user.notfound']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async delete_any_user(request,response){
        try{
            var user_id = request.body.user_id;
            var deleted = await deleted_userOperations.delete_any_user(user_id);
            if(deleted.deletedCount){
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
module.exports = deleted_userController;