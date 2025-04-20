package com.buz.buzqb.common;

public final class Constants {

    private Constants() {
    }

    // uri constants
    public static final String V1_URI = "/v1";
    public static final String AUTH_URI = "/auth";
    public static final String USERS_URI = "/users";
    public static final String SIGNUP_URI = "/signup";
    public static final String RESET_PASSWORD_URI = "/resetPassword";
    public static final String LOGIN_URI = "/login";
    public static final String BUSINESS_URI = "/business";
    public static final String ROLE_URI = "/role";
    public static final String PICTURE_URI = "/picture";
    public static final String BRAND_URI = "/brand";
    public static final String CATEGORY_URI = "/category";
    public static final String SUB_CATEGORY_URI = "/subCategory";
    public static final String ITEM_URI = "/item";
    public static final String STOCK_URI = "/stock";
    public static final String PERMISSION_URI = "/permission";
    public static final String POST_URI = "/post";
    public static final String POST_REPLIES_URI = "/postReplies";
    public static final String SEGMENT_URI = "/segment";

    // column constants
    public static final String ID = "Id";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    // field constants
    public static final String PENDING = "Pending";
    public static final String BUSINESS = "Business";
    public static final String REQUEST = "Request";
    public static final String NAME = "Name";
    public static final String MOBILE = "Mobile";
    public static final String MOBILE_VERIFICATION = "Mobile Verification";
    public static final String DESCRIPTION = "Description";
    public static final String EMAIL_VERIFICATION_PENDING = "Email Verification Pending";

    // oAuthProviders
    public static final String GOOGLE = "google";
    public static final String GITHUB = "github";

    // security constants
    public static final String SECURITY_SCHEME = "Bearer";
    public static final String SECURITY_SCHEME_NAME = "buzAuth";

    // error constants
    public static final String ERROR_IN = "EI_";

    // info constants
    public static final String TIME_TAKEN_TO_EXECUTE = "TTTE_";
    public static final String AUTHENTICATED_BUSINESS = "Authenticated_Business_";
    public static final Long ZERO = 0L;

    // permission constants
    public static final String READ = "R";
    public static final String EDIT = "E";
    public static final String ADD = "A";
    public static final String DELETE = "D";
}
