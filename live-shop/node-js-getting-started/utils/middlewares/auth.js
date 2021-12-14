const {NOT_FOUND}=require('../config').STATUS_CODES;
const messageBunddle = require('../../locales/en');
const jwt = require('../token');
module.exports = (request,response,next)=>{
    console.log("Headers",request.headers['authorization']);
    if(request.headers['authorization']){
        let tokenId = request.headers['authorization'];
        if(jwt.verifyToken(tokenId)){
            next();     //moves to next route next route
        }else{
            response.status(NOT_FOUND).json({message:messageBunddle['auth.fail1']});
        }
    }else{
        response.status(NOT_FOUND).json({message:messageBunddle['auth.fail2']});
    }
};