const express = require('express');
const router = express.Router();
const {update_name,update_email,update_password,delete_user,update_address,activate_two_factor_auth,deactivate_two_factor_auth,view_my_account} = require('../../controller/user');
const {UPDATE_NAME,UPDATE_EMAIL,UPDATE_PASSWORD,DELETE_USER,UPDATE_ADDRESS,ACTIVATE_TWO_FACTOR_AUTH,DEACTIVATE_TWO_FACTOR_AUTH,VIEW_MY_ACCOUNT} = require('../../utils/config').ROUTES.USER;
router.get(VIEW_MY_ACCOUNT,view_my_account);
router.get(ACTIVATE_TWO_FACTOR_AUTH,activate_two_factor_auth);
router.get(DEACTIVATE_TWO_FACTOR_AUTH,deactivate_two_factor_auth);
router.post(UPDATE_NAME,update_name);
router.post(UPDATE_EMAIL,update_email);
router.post(UPDATE_PASSWORD,update_password);
router.post(UPDATE_ADDRESS,update_address);
router.post(DELETE_USER,delete_user);
module.exports = router;