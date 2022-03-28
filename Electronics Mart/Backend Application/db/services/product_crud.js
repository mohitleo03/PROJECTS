const ProductModel = require('../models/product');
module.exports = {
    add_product(productObject){
        try{
            let promise = ProductModel.create(productObject);
            return promise;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_all_products(rating){
        try{
            var products =await ProductModel.find(
                {
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async find_by_interest(interested_in,rating){
        try{
            var products = await ProductModel.find(
                {
                    "product_category":{
                        $in:interested_in
                    },
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_name(name){
        try{
            var products =await ProductModel.find(
                {
                    "product_name":new RegExp(".*"+name+".*","i"),
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_category(category,rating){       //by find()
        try{
            var products = await ProductModel.find(
                {
                    "product_category":new RegExp(".*"+category+".*","i"),
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_categories(categories,rating){       //by find()
        try{
            var products = await ProductModel.find(
                {
                    "product_category":{
                        $in:categories
                    },
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_price(gt,lt,rating){       //by comaprision from x to y price
        try{
            var products =await ProductModel.find(
                {
                    $and:[
                            {
                                "product_price":{$gt:gt}
                            },
                            {
                                "product_price":{$lt:lt}
                            }    
                        ],
                        "product_rating":{$gte:rating}
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_rating(rating){       //by find()
        try{
            var products = await ProductModel.find(
                {
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_name_category(name,category,rating){
        try{
            var products = await ProductModel.find(
                {
                    "product_category":new RegExp(".*"+category+".*","i"),
                    "product_name":new RegExp(".*"+name+".*","i"),
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_price_categories(gt,lt,categories){
        try{
            var products = await ProductModel.find(
                {
                    "product_category":{
                        $in:categories
                    },
                    $and:[
                        {
                            "product_price":{$gt:gt}
                        },
                        {
                            "product_price":{$lt:lt}
                        }    
                    ]
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_by_product_id(product_id){
        try{
            var product = await ProductModel.findOne(
                {
                    "product_id":product_id
                }
            );
            if(product){
                return product;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }    
    },
    async view_by_name_categories(name,categories,rating){
        try{
            var products = await ProductModel.find(
                {
                    "product_category":{
                        $in:categories
                    },
                    "product_name":new RegExp(".*"+name+".*","i"),
                    "product_rating":{
                        $gte:rating
                    }
                },
                {
                    "_id":0,
                    "product_id":1,
                    "product_name":1,
                    "product_img":1,
                    "product_price":1
                }
            );
            if(products.length!=0){
                return products;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async update_rating(product_id,rating,rated_by){
        try{
            var update = await ProductModel.updateOne(
                {
                    "product_id":product_id
                },
                {
                    $set:{
                        "product_rating":rating,
                        "rated_by":rated_by
                    }
                }
            );
            return update;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async check_reviewed(user_id,product_id){
        try{
            var check = await ProductModel.findOne(
                {
                    "product_id":product_id,
                    "product.reviews.user_id":user_id
                },
                {
                    "_id":1
                }
            );
            if(check){
                return check;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async add_review(product_id,review){
        try{
            var update = await ProductModel.updateOne(
                {
                    "product_id":product_id
                },
                {
                    $push:{
                        "reviews":review
                    }
                }
            );
            return update;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async update_review(user_id,product_id,review){
        try{
            var update_review = await ProductModel.updateOne(
                {
                    "product_id":product_id,
                    "reviews.user_id":user_id
                },
                {
                    $set:{
                        "reviews.$.review":review
                    }
                }
            );
            return update_review;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async view_all_reviews(product_id){
        try{
            var reviews = await ProductModel.aggregate(
                [
                    {
                        $match:{
                            "product_id":product_id
                        }
                    },
                    {
                        $unwind:"$reviews"
                    },
                    {
                        $lookup:{
                                from:'users',
                                localField:'reviews.user_id',
                                foreignField:'user_id',
                                as:"user"
                        }
                    },
                    {
                        $project:{
                            "_id":0,
                            "user.name":1,
                            "reviews":1,
                        }
                    }
                ]
            )
            if(reviews.length!=0){
                return reviews;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async search_in_reviews(product_id,search){
        try{
            var reviews = await ProductModel.aggregate(
                [
                    {
                        $unwind:"$reviews"
                    },
                    {
                        $match:{
                            "product_id":product_id,
                            "reviews.review":new RegExp(search,"i")
                        }
                    },
                    {
                        $lookup:{
                                from:'users',
                                localField:'reviews.user_id',
                                foreignField:'user_id',
                                as:"user"
                        }
                    },
                    {
                        $project:{
                            "_id":0,
                            "user.name":1,
                            "reviews.review":1
                        }
                    }
                ]
            )
            if(reviews.length!=0){
                return reviews;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async update_product(product_id,product){
        try{
            var update = await ProductModel.updateOne(
                {
                    "product_id":product_id
                },
                {
                    $set:{
                        "product_name":product.product_name,
                        "product_category":product.product_category,
                        "product_price":product.product_price,
                        "product_img":product.product_img,
                        "product_desc":product.product_desc
                    }
                }
            );
            return update;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async change_quantity(p_id,p_qty){
        try{
            var find = await ProductModel.findOne({"product_id":p_id});
            console.log(find)
            if(find.product_qty>=p_qty){
                var update = await ProductModel.updateOne(
                    {
                        "product_id":p_id
                    },
                    {
                        $inc:{
                            "product_qty":-p_qty
                        }
                    }
                );
                return update;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async update_quantity(p_id,p_qty){
        try{
            var find = await ProductModel.findOne({"product_id":p_id});
            if(find.product_qty+p_qty>=0){
                var update = await ProductModel.updateOne(
                    {
                        "product_id":p_id
                    },
                    {
                        $inc:{
                            "product_qty":p_qty
                        }
                    }
                );
                return update;
            }
            else{
                return null;
            }
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    },
    async delete_product(p_id){
        try{
            var deleted = await ProductModel.deleteOne(
                {
                    "product_id":p_id
                }
            );
            return deleted;
        }
        catch(err){
            console.log("ERROR is : ",err);
        }
    }
}