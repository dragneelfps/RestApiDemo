package com.nooblabs.srawa.restapidemo.service.database;

import android.provider.BaseColumns;

public final class RequestDataHelper {

    private RequestDataHelper() {
    }

    public static class RequestEntry implements BaseColumns {
        public static final String TABLE_NAME = "request_entry";
        public static final String COLUMN_FIRST_NAME = "fname";
        public static final String COLUMN_LAST_NAME = "lname";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_RESTAURANT_NAME = "restau_name";
        public static final String COLUMN_REQUEST_BY = "request_by";
    }

}
