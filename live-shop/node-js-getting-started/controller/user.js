const {SUCCESS,SERVER_CRASH,NOT_FOUND} = require('../utils/config').STATUS_CODES;
const messageBundle = require('../locales/en');
const userOperations = require('../db/services/user_crud');
const jwt = require('../utils/token');
const userController = {
    show(request,response){
        response.send('u r on show section');
    },
    async login(request,response){
        const user = request.body;
        try{
            const doc = await userOperations.login(user);
            if(doc){
                //generate token here
                let token = jwt.generateToken(user.email);
                response.status(SUCCESS).json({message:messageBundle['login.welcome'],name:doc.name,token:token});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['login.invaliduser']});
            }
        }
        catch(err){
            response.status(NOT_FOUND).json({message:messageBundle['login.invaliduser']});
        }
        //response.send('u r on login section'+JSON.stringify(json));
    },
    register(request,response){
        
        let userObject={
            emailid:request.body.email,
            password:request.body.pwd,
            name:request.body.name
        };
        const promise = userOperations.register(userObject);
        promise.then((doc)=>{
            console.log("doc recieved");
            response.status(SUCCESS).json({message:messageBundle['register.welcome'],doc:doc});
        }).catch((err)=>{
            console.log("no doc recieved");
            response.status(SERVER_CRASH).json({message:messageBundle['register.fail'],err})
        });
        //response.status(SUCCESS).json({message:messageBundle['register.welcome']});
    }
};
module.exports = userController;