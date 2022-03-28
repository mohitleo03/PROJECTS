const {SUCCESS,SERVER_CRASH,NOT_FOUND} = require('../utils/config').STATUS_CODES;
const uniqid = require('uniqid');
const messageBundle = require('../locales/en');
const productOperations = require('../db/services/product_crud');
const jwt = require('../utils/token');
const orderOperations = require('../db/services/orders_crud')
const productController = {
    add(request,response){
        if(request.body.p_qty>=0 && request.body.p_price>=0){
            var p = request.body.p_category;
            var category = p.split(" ");
            let productObject={
                product_name:request.body.p_name,
                product_category:request.body.p_category,
                product_price:request.body.p_price,
                product_img:request.body.p_img,
                product_desc:request.body.p_desc,
                product_rating:0,
                rated_by:0,
                product_id:uniqid(category[0]),
                product_qty:request.body.p_qty
            };
            var promise = productOperations.add_product(productObject);
            promise.then((doc)=>{
                response.status(SUCCESS).json({message:messageBundle['prodect.added'],doc:doc});
            }).catch((err)=>{
                response.status(SERVER_CRASH).json({message:messageBundle['product.failed'],ERROR:err})
            });
        }
        else{
            response.status(SERVER_CRASH).json({message:messageBundle['product.qty_or_price']})
        }
    },
    async view_all_products(request,response){
        try{
            if(request.query.rating){
                rating = request.query.rating;
                }
                else {rating = 0;}
            var products = await productOperations.view_all_products(rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_interest(request,response){
        try{
            var token = request.headers['authorization'];
            var doc = jwt.getdoc(token);
            if(request.query.rating){
            rating = request.query.rating;
            }
            else {rating = 0;}
            var products = await productOperations.find_by_interest(doc.interested_in,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_name(request,response){
        try{
            var name = request.query.name;
            if(request.query.rating){
                rating = request.query.rating;
                }
                else {rating = 0;}
            var products =await productOperations.view_by_name(name,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_category(request,response){
        try{
            var category = request.query.category;
            if(request.query.rating){
                rating = request.query.rating;
                }
                else {rating = 0;}
            var products =await productOperations.view_by_category(category,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_categories(request,response){
        try{
            var categories = request.body.categories;
            if(request.query.rating){
                rating = request.query.rating;
                }
                else {rating = 0;}
            var products =await productOperations.view_by_categories(categories,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_price(request,response){
        try{
            var gt=request.query.gt;
            var lt=request.query.lt;
            if(request.query.rating){
                rating = request.query.rating;
                }
                else {rating = 0;}
            var products =await productOperations.view_by_price(gt,lt,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_rating(request,response){
        try{
            var rating=request.query.rating;
            var products =await productOperations.view_by_rating(rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_name_category(request,response){
        try{
            var name = request.query.name;
            var category = request.query.category;
            if(request.query.rating){
                var rating = request.query.rating;
                }
                else rating = 0;
            var products = await productOperations.view_by_name_category(name,category,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_name_categories(request,response){
        try{
            var name = request.body.name;
            var categories = request.body.categories;
            if(request.query.rating){
                var rating = request.query.rating;
                }
                else rating = 0;
            var products = await productOperations.view_by_name_categories(name,categories,rating);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_price_categories(request,response){
        try{
            var gt=request.query.gt;
            var lt=request.query.lt;
            var categories = request.body.categories;
            var products = await productOperations.view_by_price_categories(gt,lt,categories);
            if(products){
                response.status(SUCCESS).json({message:messageBundle["product.found"],AllProducts:products});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_by_product_id(request,response){
        try{
            var product_id = request.body.product_id;
            var product = await productOperations.view_by_product_id(product_id);
            if(product){
                response.status(SUCCESS).json({message:messageBundle["product.found"],Product:product});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.notfound"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async rate_product(request,response){
        try{
            var token = request.headers['authorization'];
            var doc = jwt.getdoc(token);
            var product_id = request.body.product_id;
            var rating = request.body.rating;
            var check = await orderOperations.check_order_status(doc.user_id,product_id);
            if(check){
                var check_rated = await orderOperations.check_rated(doc.user_id,product_id);
                if(check_rated){
                    var product =  await productOperations.view_by_product_id(product_id);
                    var rated_by=product.rated_by + 1;
                    var new_rating = ((product.product_rating*product.rated_by)+rating)/rated_by;
                    var update = await productOperations.update_rating(product_id,new_rating,rated_by);
                    if(update.modifiedCount){
                        orderOperations.product_rated(doc.user_id,product_id,rating);
                        response.status(SUCCESS).json({message:messageBundle['rating_or_review.successful']});
                    }
                    else{
                        response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful']});
                    }
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['product_already.rated']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['rating_or_review.unsuccessful']});
            }
        }
        catch(err){
                response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async edit_rating(request,response){
        try{
            var token = request.headers['authorization'];
            var doc = jwt.getdoc(token);
            var product_id = request.body.product_id;
            var rating = request.body.rating;
            var obj =await orderOperations.view_order_by_product_id(doc.user_id,product_id);
            var product = await productOperations.view_by_product_id(product_id);
            if(obj){
                var product_rating = product.product_rating;
                var old_rating = obj.product_rated;
                var rated_by = product.rated_by;
                var new_rating = ((product_rating*rated_by)+rating-old_rating)/rated_by;
                var change_rated = await orderOperations.product_rated(doc.user_id,product_id,new_rating);
                var change =await productOperations.update_rating(product_id,new_rating,rated_by);
                if(change.modifiedCount && change_rated.modifiedCount){
                    response.status(SUCCESS).json({message:messageBundle['rating_or_review.successful']});
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['rating_or_review.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async review_product(request,response){
        try{
            var token = request.headers['authorization'];
            var doc = jwt.getdoc(token);
            var product_id = request.body.product_id;
            var review = request.body.review;
            let object = {
                user_id : doc.user_id,
                review : review
            }
            var check = await orderOperations.check_order_status(doc.user_id,product_id);
            if(check){
                var check_reviewed = await productOperations.check_reviewed(doc.user_id,product_id);
                if(!check_reviewed){
                    var update = await productOperations.add_review(product_id,object);
                    if(update.modifiedCount){
                        console.log(update)
                        response.status(SUCCESS).json({message:messageBundle['rating_or_review.successful']});
                    }
                    else{
                        response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful']});
                    }
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['product_already.reviewed']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['rating_or_review.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async edit_review(request,response){
        try{
            var token = request.headers['authorization'];
            var doc = jwt.getdoc(token);
            var product_id = request.body.product_id;
            var review = request.body.review;
            var check = await productOperations.check_reviewed(doc.user_id,product_id);
            if(check){
                var update_review = await productOperations.update_review(doc.user_id,product_id,review);
                if(update_review.modifiedCount){
                    response.status(SUCCESS).json({message:messageBundle['rating_or_review.successful']});
                }
                else{
                    response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful']});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['rating_or_review.unsuccessful']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async view_all_reviews(request,response){
        try{
            var product_id = request.body.product_id;
            var reviews = await productOperations.view_all_reviews(product_id);
            if(reviews){
                response.status(SUCCESS).json({message:messageBundle['found'],reviews:reviews});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['not_found']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async search_in_reviews(request,response){
        try{
            var product_id = request.body.product_id;
            var search = request.body.search;
            var reviews = await productOperations.search_in_reviews(product_id,search);
            if(reviews){
                response.status(SUCCESS).json({message:messageBundle['found'],reviews:reviews});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle['not_found']});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async update_product(request,response){
        try{
            if(request.body.p_price>=0){
                var product_id=request.body.product_id;
                let productObject={
                    product_name:request.body.p_name,
                    product_category:request.body.p_category,
                    product_price:request.body.p_price,
                    product_img:request.body.p_img,
                    product_desc:request.body.p_desc
                };
                var product = await productOperations.update_product(product_id,productObject);
                if(product.modifiedCount && productObject){
                    response.status(SUCCESS).json({message:messageBundle["update.successful"],Product:productObject});
                }
                else{
                    response.status(NOT_FOUND).json({message:messageBundle["update.unsuccessful"]});
                }
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["product.qty_or_price"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async update_quantity(request,response){
        try{
            var product_id = request.body.product_id;
            var product_qty = request.body.product_qty;
            var product = await productOperations.update_quantity(product_id,product_qty);
            
            if(product.modifiedCount && product_id && product_qty){
                response.status(SUCCESS).json({message:messageBundle["update.successful"]});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["update.unsuccessful"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
        }
    },
    async delete_product(request,response){
        try{
            var product_id = request.body.product_id;
            var product = await productOperations.delete_product(product_id);
            if(product.deletedCount && product_id){
                response.status(SUCCESS).json({message:messageBundle["delete.successful"]});
            }
            else{
                response.status(NOT_FOUND).json({message:messageBundle["delete.unsuccessful"]});
            }
        }
        catch(err){
            response.status(SERVER_CRASH).json({message:messageBundle['unsuccessful'],ERROR:err});
    }
    }
};
module.exports = productController;