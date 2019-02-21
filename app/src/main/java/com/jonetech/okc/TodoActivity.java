package com.jonetech.okc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jonetech.okc.data.TodoContract;
import com.jonetech.okc.data.TodoManager;

public class TodoActivity extends AppCompatActivity {

    public static final String CLASS_NAME = "MainActivity";
    private EditText nameText;
    private EditText descriptionText;
    private Spinner statusSpinner;
    private TodoManager fTodoManager;

    private int status = TodoContract.TodoEntry.START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        nameText = (EditText) findViewById(R.id.name_todo);
        descriptionText = (EditText) findViewById(R.id.description);
        statusSpinner = (Spinner) findViewById(R.id.spinner_status);

        setUpSpinner();
    }

    private void setUpSpinner() {

        try{
            ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.todo_status,android.R.layout.simple_spinner_item);

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            statusSpinner.setAdapter(spinnerAdapter);

            statusSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int selection =  parent.getSelectedItemPosition();


                        if(selection == 0){
                            status = TodoContract.TodoEntry.START;
                        }else if(selection == 1){
                            status = TodoContract.TodoEntry.PENDING;
                        }else if(selection == 2){
                            status = TodoContract.TodoEntry.END;
                        }

                }
            });
        }catch (Exception e){
            Log.d(CLASS_NAME, e.getMessage() );
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.todo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.save){
            insertTodo();
        }else if(id == R.id.delete){
            sendMessage("You deleting");
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertTodo() {

        fTodoManager = new TodoManager();
        String name = nameText.getText().toString();
        String description = descriptionText.getText().toString();
        int todoStatus = status;

        fTodoManager.insertTodo(this, name, description, todoStatus);


    }

    private void sendMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
