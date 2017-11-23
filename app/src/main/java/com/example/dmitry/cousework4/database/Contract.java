package com.example.dmitry.cousework4.database;

import android.provider.BaseColumns;


public class Contract {

    private Contract()
    {

    };
    public static final class Entry implements BaseColumns {
        public final static String TABLE_NAME = "product";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "product_name";

    }
    public static final class Note implements BaseColumns {
        public final static String TABLE_NAME = "Note";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Note_name";

    }
    public static final class Waste implements BaseColumns {
        public final static String TABLE_NAME = "Waste";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Cost_name";
        public final static String COLUMN_NAME_1 = "Cost_mon";
        public final static String COLUMN_NAME_2 = "Data";

    }
    public static final class Price implements BaseColumns {
        public final static String TABLE_NAME = "Price";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "product";
        public final static String COLUMN_NAME_1 = "product_price";

    }
    public static final class Basket implements BaseColumns {
        public final static String TABLE_NAME = "Basket";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "product";
       // public final static String COLUMN_NAME_1 = "product_price";

    }
    public static final class List implements BaseColumns {
        public final static String TABLE_NAME = "List";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "List_name";

    }
    public static final class  Space implements BaseColumns {
        public final static String TABLE_NAME = "Space";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Space_name";

    }
    public static final class  Prod implements BaseColumns {
        public final static String TABLE_NAME = "Prod";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Prod_name";

    }
    public static final class  Pro implements BaseColumns {
        public final static String TABLE_NAME = "Pro";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Pro_name";

    }
    public static final class  Pr implements BaseColumns {
        public final static String TABLE_NAME = "Pr";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Pr_name";

    }
}

