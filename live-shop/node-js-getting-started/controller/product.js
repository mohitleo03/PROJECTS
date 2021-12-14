const {SUCCESS,SERVER_CRASH,NOT_FOUND} = require('../utils/config').STATUS_CODES;
const messageBundle = require('../locales/en');
const productOperations = require('../db/services/product_crud');
const jwt = require('../utils/token');
const productController = {
    show(request,response){
        response.send('u r on show section');
    },
    // async login(request,response){
    //     const user = request.body;
    //     try{
    //         const doc = await userOperations.login(user);
    //         if(doc){
    //             //generate token here
    //             let token = jwt.generateToken(user.email);
    //             response.status(SUCCESS).json({message:messageBundle['login.welcome'],name:doc.name,token:token});
    //         }
    //         else{
    //             response.status(NOT_FOUND).json({message:messageBundle['login.invaliduser']});
    //         }
    //     }
    //     catch(err){
    //         response.status(NOT_FOUND).json({message:messageBundle['login.invaliduser']});
    //     }
    //     //response.send('u r on login section'+JSON.stringify(json));
    // },
    view(request,response){
        const json = request.body;
        const doc = productOperations.view;
    console.log("JSON is ", doc);
    response
      .status(SUCCESS)
      .json(doc);
    },
    add(request,response){
        let productObject={
            product_id:request.body.p_id,
            product_name:request.body.p_name,
            product_price:request.body.p_price,
            product_img:request.body.p_img
        };
        const promise = productOperations.add(productObject);
        promise.then((doc)=>{
            console.log("doc recieved");
            response.status(SUCCESS).json({message:messageBundle['prodect.added'],doc:doc});
        }).catch((err)=>{
            console.log("no doc recieved");
            response.status(SERVER_CRASH).json({message:messageBundle['product.failed'],err})
        });
        //response.status(SUCCESS).json({message:messageBundle['register.welcome']});
    }
};
module.exports = productController;