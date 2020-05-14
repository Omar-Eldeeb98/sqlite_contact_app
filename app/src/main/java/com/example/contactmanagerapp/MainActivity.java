package com.example.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DataBaseHelper dataBaseHelper;

    private EditText
            nameEditText,
            lastNameEditText,
            emailEditText,
            idEditText;

    private Button
            insertButton,
            readButton,
            updateButton,
            deleteButton,
            hideButton;

    private ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         dataBaseHelper = new DataBaseHelper(this);

         listView = (ListView) findViewById(R.id.myList);

        nameEditText = (EditText) findViewById(R.id.nameditextID);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditTextId);
        emailEditText = (EditText) findViewById(R.id.emailEditTextId);
        idEditText =(EditText) findViewById(R.id.editText4);

        insertButton = (Button) findViewById(R.id.insertBtnId);
        readButton =(Button) findViewById(R.id.readBtnId);
        updateButton = (Button) findViewById(R.id.updateBtnId);
        deleteButton = (Button) findViewById(R.id.deleteBtnId);
        hideButton =(Button) findViewById(R.id.hideListBtn);

        insertButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        hideButton.setOnClickListener(this);



    }

    public void showData()
    {
        ArrayList<String> arrayList = dataBaseHelper.getAllRecords();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.insertBtnId:

                String name = nameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                boolean result =  dataBaseHelper.insertData(name,lastName,email);
                if(result==true)
                {
                    Toast.makeText(getApplicationContext(),"inserted" , Toast.LENGTH_SHORT).show();
                    nameEditText.setText("");
                    lastNameEditText.setText("");
                    emailEditText.setText("");


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error ... " , Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.deleteBtnId:

                String id  = idEditText.getText().toString();
              Integer result__ = dataBaseHelper.delete(id);
              if(result__>0)
              {
                  Toast.makeText(getApplicationContext(),"Deleted" , Toast.LENGTH_SHORT).show();
                  idEditText.setText("");

              }
              else
              {
                  Toast.makeText(getApplicationContext(),"Error....." , Toast.LENGTH_SHORT).show();


              }
                break;

            case R.id.readBtnId:

                showData();
                listView.setVisibility(View.VISIBLE);
                break;

            case R.id.updateBtnId:
                String name_ = nameEditText.getText().toString();
                String lastName_ = lastNameEditText.getText().toString();
                String email_ = emailEditText.getText().toString();
                String id_ = idEditText.getText().toString();
                boolean result_ =  dataBaseHelper.updateData(id_,name_,lastName_,email_);
                if(result_==true)
                {
                    Toast.makeText(getApplicationContext(),"updated" , Toast.LENGTH_SHORT).show();
                    nameEditText.setText("");
                    lastNameEditText.setText("");
                    emailEditText.setText("");
                    idEditText.setText("");

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error ... " , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hideListBtn:

                listView.setVisibility(View.INVISIBLE);
                break;


        }

    }
}
