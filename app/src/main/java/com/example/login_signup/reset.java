package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class reset extends AppCompatActivity {
   EditText reseName;
    EditText paSs, repass;
    Button confirm;
    DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
    reseName =(EditText) findViewById(R.id.conUser);
    paSs = (EditText) findViewById(R.id.rPass);
    repass = (EditText) findViewById(R.id.rpas);
    confirm = (Button) findViewById(R.id.conBut);
    db = new DBhelper(this);



       reseName.setText(getIntent().getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String Ruser = reseName.getText().toString();
                String pass = paSs.getText().toString();
                String Repas = repass.getText().toString();
                if (Repas.equals(pass)) {

                    Boolean conPass = db.updatePass(Ruser, pass);
                    if (conPass == true) {
                        Intent i = new Intent(getApplicationContext(), signUp.class);
                        Toast.makeText(reset.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    } else {
                        Toast.makeText(reset.this, "Not updated", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(reset.this, "Password  Not Matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

