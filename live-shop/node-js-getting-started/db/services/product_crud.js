const ProductModel = require('../models/product')
module.exports = {
    add(productObject){
        let promise = ProductModel.create(productObject);
        return promise;
    },
    /*async view({email,pwd}){
        //return await UserModel.findOne({"emailid":email,"password":pwd});
        const doc= await UserModel.findOne({"emailid":email});
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
    }*/
    view(){
        const doc = ProductModel.find();
        if(doc){
            return doc;
        }
        else{
            return null;
        }
    }
}