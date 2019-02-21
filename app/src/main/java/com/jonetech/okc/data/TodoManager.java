package com.jonetech.okc.data;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;

public class TodoManager {

    public TodoManager() {
    }

    public void insertTodo(final Context context, String name, String description, int status ){

        // Create Database Helper
        TodoHelper mTodoHelper = new TodoHelper(context);

        //Get the writeable database
        SQLiteDatabase db = mTodoHelper.getWritableDatabase();

        // Create content Values where the column are keys

        ContentValues values = new ContentValues();
        values.put(TodoContract.TodoEntry.COLUMN_NAME, name);
        values.put(TodoContract.TodoEntry.COLUMN_DESC, description);
        values.put(TodoContract.TodoEntry.COLUMN_STATUS, status);

        long rowId = db.insert(TodoContract.TodoEntry.TABLE_NAME, null,values);

        if(rowId == -1){
            // Something went wrong

            AlertDialog.Builder  alertDialog = new AlertDialog.Builder(context);

            alertDialog.setMessage("Error couldn't save data");

            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog mDialog = alertDialog.create();
            mDialog.show();
        }else{
            AlertDialog.Builder  alertDialog = new AlertDialog.Builder(context);

            alertDialog.setMessage("Data saved successfully");

            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            AlertDialog mDialog = alertDialog.create();
            mDialog.show();
        }

    }


}
