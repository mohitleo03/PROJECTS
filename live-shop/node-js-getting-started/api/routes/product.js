const express = require('express');
const router = express.Router();
const {show,add,view} = require('../../controller/product');
const {ADD,VIEW,PROFILE} = require('../../utils/config').ROUTES.PRODUCT;
router.get(PROFILE,show);
router.get(VIEW,view);
router.post(ADD,add);
module.exports = router;