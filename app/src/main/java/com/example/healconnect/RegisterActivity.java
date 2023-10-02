package com.example.healconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edusername, edemail, edpassword, edconfirmpassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edusername = findViewById(R.id.editTextText);
        edemail = findViewById(R.id.editTextText2);
        edpassword = findViewById(R.id.editTextText3);
        edconfirmpassword = findViewById(R.id.editTextText4);
        tv = findViewById(R.id.textViewalreadyhaveaccount);
        btn = findViewById(R.id.button);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername.getText().toString();
                String email = edemail.getText().toString();
                String password = edpassword.getText().toString();
                String conferm = edconfirmpassword.getText().toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || conferm.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(conferm) == 0) {
                        if(isvalid(password)) {
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "Record inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "password must contain 8 character and having letter digits and special character", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "password and confirm password is not matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public static boolean isvalid(String passwordhere) {
        int f1 = 8, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                if (Character.isLetter(passwordhere.charAt(s))) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;


        }
    }
}