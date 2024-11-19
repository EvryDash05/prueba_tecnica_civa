package com.example.back_prueba_tecnica_civa.commons.constants;

public abstract class DatabaseConstants {

    /**
     * <h1>BUS TABLES</h1>
     * <h2>Bus table</h2>
     */
    public static final String BUS_TABLE = "tb_bus";
    public static final String BUS_ID = "bus_id";
    public static final String BUS_NUMBER = "bus_number";
    public static final String BUS_LICENSE_PLATE = "license_plate";
    public static final String BUS_CREATED_AT = "created_at";
    public static final String BUS_FEATURES = "features";
    public static final String BUS_BRAND_ID = "brand_id";
    public static final String BUS_STATUS = "status";

    /**
     * <h2>Brand table</h2>
     */
    public static final String BRAND_TABLE = "tb_brand";
    public static final String BRAND_ID = "id";
    public static final String BRAND_NAME = "brand_name";

    /**
     * <h2>Role table 🛢</h2>
     */
    /* Property for the table name of table "tb_roles" */
    public static final String ROLE_TABLE = "tb_role";

    /* Property for the primary key on table "tb_roles" */
    public static final String ROLE_ID = "role_id";

    /* Property for the role name on table "tb_roles" */
    public static final String ROLE_NAME = "role_name";

    /**
     * <h2>Customer table 🛢</h2>
     */
    /* Property for the table name of "tb_user" */
    public static final String USER_TABLE = "tb_user";

    /* Property for the primary key on table "tb_user" */
    public static final String USER_ID = "user_id";

    /* Property for the username column on table "tb_user" */
    public static final String USERNAME = "username";

    /* Property for the email column on table "tb_user" */
    public static final String EMAIL = "email";

    /* Property for the password column on table "tb_user" */
    public static final String PASSWORD = "password";

    /**
     * <h2>Customer Role table 🛢</h2>
     */
    /* Property for the table name of "user_role" */
    public static final String USER_ROLE_TABLE = "user_role";

    /* Property for the customer ID column on table "user_role" */
    public static final String USER_ROLE_USER_ID = "user_id";

    /* Property for the role ID column on table "user_role" */
    public static final String USER_ROLE_ROLE_ID = "role_id";

    private DatabaseConstants() {}
}
