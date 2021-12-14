const jwt = require('jsonwebtoken');
module.exports = {
    secret:process.env.SECRET,
    generateToken(emailid){
        let tokenId = jwt.sign({userid:emailid},this.secret,{expiresIn:"1h"});
        return tokenId;
    },
    verifyToken(tokenId){
        try{
            let decode = jwt.verify(tokenId,this.secret);
        console.log(tokenId,this.secret,decode);
        if(decode && decode.userid){
            return true;
        }
        else{
            return false;
        }
        }
        catch (err){
            console.log("VERIFY TOKEN",err);
        }
    }
}