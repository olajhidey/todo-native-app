package com.jonetech.okc.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoHelper extends SQLiteOpenHelper {

    public final static String LOG_TAG = TodoHelper.class.getSimpleName();

    // Database Name
    private static String DATABASE_NAME ="todo.db";

    // Database version
    private static int DATABASE_VERSION = 1;

    // Constructor of the TodoHelper class

    public TodoHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TODO = "CREATE TABLE "+TodoContract.TodoEntry.TABLE_NAME + "(" +
                TodoContract.TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TodoContract.TodoEntry.COLUMN_NAME + " TEXT NOT NULL," +
                TodoContract.TodoEntry.COLUMN_DESC + " TEXT, "+
                TodoContract.TodoEntry.COLUMN_STATUS+ " INTEGER DEFAULT 0 " +
                " )";

        // execute SQL statement
        db.execSQL(SQL_CREATE_TODO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
