package com.jonetech.okc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jonetech.okc.data.TodoContract;
import com.jonetech.okc.data.TodoHelper;
import com.jonetech.okc.data.TodoManager;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MainActivity";
    private TodoHelper fTodoHelper;
    private TextView fDisplaySampleText;
    private TextView fResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fTodoHelper = new TodoHelper(this);

        fDisplaySampleText = (TextView) findViewById(R.id.dispay);
        fResultText = (TextView) findViewById(R.id.result_text);

        fResultText.setText("");

        // Display a copy of Displayed text like
        // ID       Name        Description     Status
        fDisplaySampleText.setText("");



        fDisplaySampleText.append(
                TodoContract.TodoEntry._ID + "\t\t" +
                        TodoContract.TodoEntry.COLUMN_NAME + "\t\t" +
                        TodoContract.TodoEntry.COLUMN_DESC + "\t\t" +
                        TodoContract.TodoEntry.COLUMN_STATUS + "\t\t\n"
        );


    }

    @Override
    protected void onStart() {

        readDataFromDatabase();
        super.onStart();
    }

    private void readDataFromDatabase() {

        // Create or open database readable
        SQLiteDatabase mDatabase = fTodoHelper.getReadableDatabase();

        // Define a projection which shows the column of the database you want to get
        String[] projection = {
                TodoContract.TodoEntry._ID,
                TodoContract.TodoEntry.COLUMN_NAME,
                TodoContract.TodoEntry.COLUMN_DESC,
                TodoContract.TodoEntry.COLUMN_STATUS
        };

        // Run query to database

        Cursor cursor = mDatabase.query(TodoContract.TodoEntry.TABLE_NAME, projection, null, null, null, null, null);



        try {

            fDisplaySampleText.append("You have " + cursor.getCount() + " Data in db");

            // Figure out the column for each
            int idColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_NAME);
            int descColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_DESC);
            int statusColumnIndex = cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_STATUS);

            //Iterate through all the returned rows

            while (cursor.moveToNext()) {

                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDescription = cursor.getString(descColumnIndex);
                String currentStatus = cursor.getString(statusColumnIndex);

                fResultText.append("\n"+currentId + "\t\t" +
                        currentName + "\t\t" +
                        currentDescription + "\t\t" +
                        currentStatus + "\t\t"
                );


            }
        } catch (Exception e) {

            Log.d(LOG_TAG, e.getMessage());

        } finally {
            cursor.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.new_todo) {
            navigateToNewTodo();
        }else if(id  == R.id.dummy_data){
            insertDummyData();
            readDataFromDatabase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertDummyData() {

        String dummyName = "Read";
        String dummyDesc ="Read alot";
        int status =0;

        TodoManager mTodoManager = new TodoManager();

        mTodoManager.insertTodo(this, dummyName, dummyDesc, status);

    }

    private void navigateToNewTodo() {

        Intent intent = new Intent(MainActivity.this, TodoActivity.class);
        startActivity(intent);
    }




}
