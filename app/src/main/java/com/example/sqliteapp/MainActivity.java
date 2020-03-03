package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        DatabaseHelper myDb;
        EditText name,surname,marks,id;
        Button add,view,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);

        name=(EditText)findViewById(R.id.txt_name);
        surname=(EditText)findViewById(R.id.txt_surname);
        marks=(EditText)findViewById(R.id.txt_marks);
        id=(EditText)findViewById(R.id.txt_id);

        add=(Button)findViewById(R.id.btn_add);
        view=(Button)findViewById(R.id.btn_view);
        update=(Button)findViewById(R.id.btn_update);
        delete=(Button)findViewById(R.id.btn_delete);
        AddData();
        viewall();
    }
    public void AddData()
    {
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isInserted= myDb.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());

                if(isInserted==true)
                    Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void viewall()
    {
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    //show message
                    showMessage("Error","no data found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id:" + res.getString(0) + "\n");
                    buffer.append("Name:" + res.getString(1) + "\n");
                    buffer.append("Surname:" + res.getString(2) + "\n");
                    buffer.append("Marks:" + res.getString(3) + "\n\n");

                }
                 showMessage("Data",buffer.toString()); //show the data.

            }
        });
    }

    public  void showMessage(String Title,String Message) // showing a alert dialog box.
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
}
