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

    private DatabaseConstants() {}
}
