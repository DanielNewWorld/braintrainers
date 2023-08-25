package com.daniel.braintrainers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DB_1.db";
    public static final String tableName = "table_1";

    public static final String keyLocaleDefault = "LocaleDefault";

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Log.d(TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table " + tableName + " ("
                    + "id integer primary key autoincrement,"
                    + keyLocaleDefault + " text"
                    + ");");

            db.execSQL("insert into " + tableName + " (" + keyLocaleDefault + ") values (\'en\');");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName + ";");
            onCreate(db);
        }
}
