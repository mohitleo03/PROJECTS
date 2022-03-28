module.exports = {
    SCHEMAS:{
        USERS:'users',
        PRODUCT:'product',
        ADMIN:"admin",
        CART:"cart",
        ORDERS:"orders",
        DELETED_USERS:"deleted_users",
        DELETED_ADMIN:"deleted_admin",
        EMAIL:"email",
        NEWS_LETTER:"news_letter"
    },
    STATUS_CODES:{
        SUCCESS:200,
        SUCCESS2:201,
        SERVER_CRASH:500,
        FILE_NOT_FOUND:404,
        NOT_FOUND:404
    },
    ROUTES:{
        ROOT:'/',
        PAYMENTS:{
            PAY:'/pay'
        },
        USER:{
            LOGIN:'/login',
            REGISTER:'/register',
            CHECK_EMAIL:'/check_email',
            VIEW_MY_ACCOUNT:'/view_my_account',
            ACTIVATE_ACC:'/activate_acc',
            ACTIVATE_TWO_FACTOR_AUTH:'/activate_two_factor_auth',
            DEACTIVATE_TWO_FACTOR_AUTH:'/deactivate_two_factor_auth',
            CHECK_OTP:'/check_otp',
            FORGOT_PASS:'/forgot_pass',
            ACC_RECOVER:'/acc_recover',
            ACC_RECOVER_BY_OLD_PASS:'/acc_recover_old_pass',
            LOGOUT:'/logout',
            UPDATE_NAME:'/update_user_name',
            UPDATE_EMAIL:'/update_user_email',
            UPDATE_PASSWORD:'/update_user_password',
            UPDATE_ADDRESS:'/update_user_address',
            DELETE_USER:'/delete_user',
            VIEW_ALL:'/view_all_users',
            VIEW_BY_EMAIL_ID:'/view_user_by_emailid',
            VIEW_BY_NAME:'/view_user_by_name',
            VIEW_BY_USER_ID:'/view_user_by_user_id',
            VIEW_BY_PINCODE:'/view_user_by_pincode',
            VIEW_BY_CITY:'/view_user_by_city',
            VIEW_BY_STATE:'/view_user_by_state',
            DELETE_ANY_USER:'/delete_any_user'
        },
        ADMIN:{
            LOGIN:'/admin_login',
            REGISTER:'/admin_register',
            CHECK_EMAIL:'/check_email_admin',
            ACTIVATE_ACC:'/activate_admin_acc',
            ACTIVATE_TWO_FACTOR_AUTH:'/admin_activate_two_factor_auth',
            DEACTIVATE_TWO_FACTOR_AUTH:'/admin_deactivate_two_factor_auth',
            CHECK_OTP:'/check_otp_admin',
            FORGOT_PASS:'/forgot_pass_admin',
            ACC_RECOVER:'/acc_recover_admin',
            ACC_RECOVER_BY_OLD_PASS_ADMIN:'/acc_recover_old_pass_admin',
            VIEW_ALL:'/view_all_admin',
            VIEW_BY_NAME:'/view_admin_by_name',
            VIEW_BY_EMAIL:'/view_admin_by_email',
            VIEW_BY_ADMIN_ID:'/view_admin_by_admin_id',
            AUTHORIZE_ADMIN:'/authorize_admin',
            UNAUTHORIZE_ADMIN:'/unauthorize_admin',
            VIEW_MY_ACCOUNT:'/view_my_account_admin',
            UPDATE_NAME:'/update_admin_name',
            UPDATE_EMAIL:'/update_admin_email',
            UPDATE_PASSWORD:'/update_admin_password',
            UPDATE_ADDRESS:'/update_admin_address',
            DELETE_ADMIN:'/delete_admin',
            DELETE_ANY_ADMIN:'/delete_any_admin',
        },
        CART:{
            ADD_TO_CART:'/add_to_cart',
            VIEW_MY_CART:'/view_my_cart',
            VIEW_ONE_CART:'/view_one_cart',
            SEARCH_IN_CART:'/searh_in_cart',
            UPDATE_MY_CART:'/update_my_cart',
            DELETE_MY_CART_ITEM:'/delete_my_cart_item',
            DELETE_USER_CART:'/delete_user_cart',
        },
        ORDERS:{
            ORDER_PRODUCT:'/order_product',
            VIEW_ONE_ORDER:'/view_one_order',
            VIEW_ORDERS:'/view_orders',
            VIEW_ALL_ORDERS:'/view_all_orders',
            VIEW_ORDERS_BY_PRODUCT_ID:'/view_orders_by_product_id',
            CHANGE_ORDER_STATUS:'/change_order_status',
            VIEW_SINGLE_ORDER:'/view_single_order',
            VIEW_ORDERS_BY_USER:'/view_orders_by_user',
            VIEW_ORDERS_BY_ORDER_STATUS:'/view_order_by_order_status',
            UPDATE_ORDER_STATUS:'/update_order_status'
        },
        PRODUCT:{
            ADD:'/add_product',
            VIEW_ALL_PRODUCTS:'/view_all_products',
            VIEW_BY_interest:'/view_by_interest',
            VIEW_BY_NAME:'/view_by_name',
            VIEW_BY_CATEGORY:'/view_by_category',
            VIEW_BY_CATEGORIES:'/view_by_categories',
            VIEW_BY_PRICE:'/view_by_price',
            VIEW_BY_RATING:'/view_by_rating',
            VIEW_BY_NAME_CATEGORY:'/view_by_name_category',
            VIEW_BY_NAME_CATEGORIES:'/view_by_name_categories',
            VIEW_BY_PRICE_CATEGORIES:'/view_by_price_categories',
            VIEW_BY_PRODUCT_ID:'/view_by_product_id',
            VIEW_ALL_REVIEWS:'/view_all_reviews',
            SEARCH_IN_REVIEWS:'/search_in_reviews',
            RATE_PRODUCT:'/rate_product',
            REVIEW_PRODUCT:'/review_product',
            EDIT_RATING:'/edit_rating',
            EDIT_REVIEW:'/edit_review',
            UPDATE_PRODUCT:'/update_product',
            UPDATE_QUANTITY:'/update_quantity',
            DELETE_PRODUCT:'/delete_product',
        },
        NEWS_LETTER:{
            NEWS_LETTER:'/news_letter',
            SEND_NEWS_LETTER:'/send_news_letter',
            VIEW_NEWS_LETTERS:'/view_news_letters'
        },
        DELETED_USERS:{
            VIEW_ALL:'/view_all_deleted_users',
            VIEW_BY_EMAIL_ID:'/view_deleted_user_by_emailid',
            VIEW_BY_NAME:'/view_deleted_user_by_name',
            VIEW_BY_USER_ID:'/view_deleted_user_by_user_id',
            VIEW_BY_PINCODE:'/view_deleted_user_by_pincode',
            VIEW_BY_CITY:'/view_deleted_user_by_city',
            VIEW_BY_STATE:'/view_deleted_user_by_state',
            DELETE_ANY_USER:'/delete_deleted_any_user'
        },
        DELETED_ADMINS:{
            VIEW_ALL:'/view_all_deleted_admin',
            DELETE_ANY_ADMIN:'/delete_any_deleted_admin',
        }
    }
}