const {Schema,SchemaTypes} = require('../connect');
const {USERS} = require('../../utils/config').SCHEMAS;
const mongoose = require('../connect');
const userSchema = new Schema({
    emailid:{type:SchemaTypes.String,required:true,unique:true},
    password:{type:SchemaTypes.String,required:true,min:3,max:25},
    name:{type:SchemaTypes.String,required:true}
},
{
    timestamps:true         //this will add time in data object when the user is created in database
});
const UserModel = mongoose.model(USERS,userSchema);
module.exports = UserModel;