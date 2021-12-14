const { request, response } = require('express');
const express = require('express');
const app = express();      //call express function and it returns app function
//it creates a new app for aour application
const cors = require('cors');
app.use(express.static('public'));          //app.use(middleware) middlware is a function
require('dotenv').config();
app.use(express.json());                    //for reading json format data key:value
app.use(express.urlencoded());              //for reading key=value&key=value
const {ROOT} = require('./utils/config').ROUTES;
app.use(cors());
app.use(ROOT,require('./api/routes/user'));
//app.use(require('./utils/middlewares/auth'));
app.use(ROOT,require('./api/routes/product'));
app.use(ROOT,require('./api/routes/orders'));
app.use(ROOT,require('./api/routes/payments'));
app.use(require('./utils/middlewares/404'));
const server = app.listen(process.env.PORT || 1234,err=>{       //.listen up the our application on that port in local host this port number should be unique , should not get conflict with other apps
    if(err){
        console.log("app crash",err);
    }
    else{
        console.log("server started...",server.address().port);
    }
});