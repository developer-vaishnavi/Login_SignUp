package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText username,email,password,confirmPassword;
TextView acc;
Button Register;
DBhelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        confirmPassword = findViewById(R.id.comPas);
        acc = findViewById(R.id.acc);
        Register = findViewById(R.id.loginTv);
        Db = new DBhelper(this);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),signUp.class);
            startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String user = username.getText().toString();
            String Email = email.getText().toString();
            String pass = password.getText().toString();
            String conPass = confirmPassword.getText().toString();
            if(user.equals("")||Email.equals("")||pass.equals("")||conPass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(conPass)){
                    Boolean checkUser = Db.checkUsername(user);
                    if(checkUser==false) {
                        Boolean insert = Db.insertData(user, Email, pass);
                        if(insert==true){
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(getApplicationContext(),signUp.class);
                      startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User Already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }
}