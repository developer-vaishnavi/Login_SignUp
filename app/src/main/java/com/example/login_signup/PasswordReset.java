package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordReset extends AppCompatActivity {
EditText userR;
Button  resetR;
DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
    userR = findViewById(R.id.rUser);
    resetR = findViewById(R.id.rBut);
    db = new DBhelper(this);


    resetR.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = userR.getText().toString();
            Boolean check = db.checkUsername(user);
            if(check==true){
                Intent i = new Intent(PasswordReset.this,reset.class);
               i.putExtra("username",user);
                startActivity(i);
            }
            else{
                Toast.makeText(PasswordReset.this, "User doesn't exists", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
}