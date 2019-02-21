package com.jonetech.okc.data;

// Make contract final so it cant be edited

import android.provider.BaseColumns;

public final class TodoContract {

    public TodoContract() {}

    public static final class TodoEntry implements BaseColumns{

        /**
         *  This table have 5 columns
         *  1. ID
         *  2. Name
         *  3. Description
         *  4. Date
         *  5. Status
         *
         */

        // Name of the database for todos
        public final static String TABLE_NAME = "alltodos";

        // Auto increment ID which is use to identify each row
        public final static String _ID = BaseColumns._ID;

        // Name of the Todos
        public final static String COLUMN_NAME = "name";

        //Name of the Description
        public final static String COLUMN_DESC = "description";


        //Name of status
        public final static String COLUMN_STATUS = "status";


        public final static int START = 0;

        public final static int PENDING = 1;

        public final static int END = 2;

    }
}
