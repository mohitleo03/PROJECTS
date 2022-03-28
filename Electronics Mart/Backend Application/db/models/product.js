const {Schema,SchemaTypes} = require('../connect');
const {PRODUCT} = require('../../utils/config').SCHEMAS;
const mongoose = require('../connect');
const productSchema = new Schema({
    product_id:{type:SchemaTypes.String,required:true,unique:true,index:true},
    product_name:{type:SchemaTypes.String,required:true,index:true},
    product_category:{type:SchemaTypes.String,required:true,index:true},
    product_img:{type:SchemaTypes.String,required:true},
    product_price:{type:SchemaTypes.Number,required:true,index:true},
    product_desc:{type:SchemaTypes.String,required:true},
    product_rating:{type:SchemaTypes.Number,index:true},
    product_qty:{type:SchemaTypes.Number,required:true},
    rated_by:{type:SchemaTypes.Number},
    reviews:{type:SchemaTypes.Array}
},
{
    timestamps:true         //this will add time in data object when the product is created in database
});
const ProductModel = mongoose.model(PRODUCT,productSchema);
module.exports = ProductModel;