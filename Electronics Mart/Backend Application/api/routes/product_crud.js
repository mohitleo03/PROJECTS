const express = require('express');
const router = express.Router();
const {add,delete_product,update_quantity, update_product} = require('../../controller/product');
const {ADD,UPDATE_PRODUCT,DELETE_PRODUCT,UPDATE_QUANTITY} = require('../../utils/config').ROUTES.PRODUCT;
router.post(ADD,add);
router.post(UPDATE_PRODUCT,update_product);
router.post(UPDATE_QUANTITY,update_quantity)
router.post(DELETE_PRODUCT,delete_product);
module.exports = router;