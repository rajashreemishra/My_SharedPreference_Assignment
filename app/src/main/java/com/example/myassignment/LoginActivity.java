package com.example.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText metusername;
    private EditText metpassword;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefManager=getApplicationContext().getSharedPreferences("login_credentials",MODE_PRIVATE);
        editor=prefManager.edit();

        metusername=findViewById(R.id.et_username);
        metpassword=findViewById(R.id.et_password);

        Button mbtnlogin=findViewById(R.id.btn_login);
        mbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = metusername.getText().toString();
                String password = metpassword.getText().toString();
                editor.putString("USERNAME", username);
                editor.putString("PASSWORD", password);
                editor.putBoolean("ISLOGINSUCCESS", true);
                editor.apply();

                login();
                }

        });
    boolean ISUSERALREADYLOGGEDIN=prefManager.getBoolean("ISLOGINSUCCESS",false);
                if (ISUSERALREADYLOGGEDIN) {
                    login();
                }

        }
    private void login(){
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}