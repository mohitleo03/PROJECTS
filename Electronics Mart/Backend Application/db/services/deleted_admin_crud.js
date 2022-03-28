const Deleted_adminModel = require('../models/deleted_admin');
const encryption = require('../../utils/encrypt');
module.exports = {
    add_to_deleted(adminObject){
        try{
            let promise = Deleted_adminModel.create(adminObject);
            return promise;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async login({email,pwd}){
        try{
            var doc= await Deleted_adminModel.findOne({"emailid":email});
            if(doc){
                if(encryption.comapreHash(doc.password,pwd)){
                    return doc;
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async find_all(){
        try{
            var alladmins = await Deleted_adminModel.find(
                {

                },
                {
                    "_id":0,
                    "emailid":1,
                    "name":1,
                    "admin_id":1,
                    "address":1,
                    "two_factor_auth":1,
                    "authorizedby":1,
                    "unauthorizedby":1,
                    "deleted_date":1,
                    "deleted_by":1
                }
            );
            if(alladmins.length!=0){
                return alladmins;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async find_admin_by_admin_id(admin_id){
        try{
            var admin = await Deleted_adminModel.findOne(
                {
                    "admin_id":admin_id
                }
            );
            if(admin){
                return admin;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async find_by_email(email){
        try{
            var admin = await Deleted_adminModel.findOne(
                {
                    "emailid":email
                }
            );
            if(admin){
                return admin;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async find_by_name(name){
        try{
            var admin = await Deleted_adminModel.find(
                {
                    "name":new RegExp(".*"+name+".*","i")
                }
            );
            if(admin.length!=0){
                return admin;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async delete_any_admin(admin_id){
        try{
            var deleted = await Deleted_adminModel.deleteOne(
                {
                    "admin_id":admin_id
                }
            );
            return deleted;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    }    
}