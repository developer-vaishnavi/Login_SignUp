package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends AppCompatActivity {
EditText  user,pass;
TextView forgPass;
Button Login, signin;
DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = findViewById(R.id.emailLog);
        pass = findViewById(R.id.PasswordLog);
        Login = findViewById(R.id.Log_in);
       signin = findViewById(R.id.Signup);
       forgPass =(TextView) findViewById(R.id.forPas);
        db = new DBhelper(this);

        forgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PasswordReset.class);
                startActivity(i);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           String users = user.getText().toString();
           String Pass = pass.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(signUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserPass = db.checkPass(users,Pass);
                    if(checkuserPass==true){
                        Toast.makeText(signUp.this, "Login succesfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(signUp.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}