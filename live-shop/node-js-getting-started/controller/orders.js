const orders=(request,response)=>{
        // this is query string way
        //const orderId = request.query.orderid;        
        //const name = request.query.order_name;
        //response.send(new Date() + "OrderId Rec " + orderId+name);

        //this is path param way
        const orderId = request.params.orderid;           //this orderId should get matched with the variable name mentioned in routes/order.js
        response.send(new Date() + "OrderId Rec " + orderId);
    };
const history=(request,response)=>{
    response.send('history');
};
module.exports = {orders};