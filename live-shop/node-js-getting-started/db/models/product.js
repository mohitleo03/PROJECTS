const {Schema,SchemaTypes} = require('../connect');
const {PRODUCT} = require('../../utils/config').SCHEMAS;
const mongoose = require('../connect');
const productSchema = new Schema({
    product_id:{type:SchemaTypes.String,required:true,unique:true},
    product_name:{type:SchemaTypes.String,required:true},
    product_img:{type:SchemaTypes.String,required:true},
    product_price:{type:SchemaTypes.Number,required:true}
},
{
    timestamps:true         //this will add time in data object when the product is created in database
});
const ProductModel = mongoose.model(PRODUCT,productSchema);
module.exports = ProductModel;