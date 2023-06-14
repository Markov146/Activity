package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{4,}" +
                    "$");
    EditText signupLogin,  signupEmail, signupPassword;
     TextView loginRedirectText;
     Button btnGoToSecAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupLogin = findViewById(R.id.signup_login);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.signupRedirectText);


        Button btnGoToSecAct = (Button) findViewById(R.id.btnGoToSecAct);

        btnGoToSecAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateLogin() | !validatePassword()) {
                } else {
                    System.out.println("Неверный логин или пароль");
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
        public Boolean validateLogin() {
            String val = signupLogin.getText().toString();
            if (val.isEmpty()) {
                signupLogin.setError("Имя пользователя не может быть пустым");
                return false;
            }
            else {
                signupLogin.setError(null);
                return true;
            }
        }
        public Boolean validatePassword(){

            String val = signupPassword.getText().toString();
                if (val.isEmpty()) {
                    signupPassword.setError("Пароль не может быть пустым");
                    return false;
                }

                else if (!PASSWORD_PATTERN.matcher(val).matches()) {
                    signupPassword.setError("Пароль не надежный");
                    return false;
                } else {
                    signupPassword.setError(null);
                    return true;
                }
        }
}

