package com.example.myassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText metname;
    private EditText metphonenumber;
    private EditText metaddress;
    private EditText metnationality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        metname=findViewById(R.id.ET_edittedname);
        metphonenumber=findViewById(R.id.ET_edittedphonenumber);
        metaddress=findViewById(R.id.ET_edittedaddress);
        metnationality=findViewById(R.id.ET_edittednationality);


        Button mbtnsave=findViewById(R.id.btn_save);
        Button mbtncancel=findViewById(R.id.btn_cancel);

        mbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=metname.getText().toString();
                String phnno=metphonenumber.getText().toString();
                String address=metaddress.getText().toString();
                String nationality=metnationality.getText().toString();
                Intent object=new Intent(EditActivity.this,HomeActivity.class);
                object.putExtra("NAME",name);
                object.putExtra("PHONENUMBER",phnno);
                object.putExtra("ADDRESS",address);
                object.putExtra("NATIONALITY",nationality);
                startActivity(object);
                setResult(Activity.RESULT_OK);


            }
        });
        mbtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
                Toast.makeText(EditActivity.this,"Cancelled Successfully",Toast.LENGTH_LONG).show();

            }
        });

    }
}