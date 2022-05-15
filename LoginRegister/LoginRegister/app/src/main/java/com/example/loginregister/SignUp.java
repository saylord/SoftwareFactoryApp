package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextName, textInputEditTextSurname, textInputEditTextEmail, textInputEditTextUsername, textInputEditTextPassword;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextName = findViewById(R.id.user_name);
        textInputEditTextSurname = findViewById(R.id.user_surname);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_name, user_surname, email, username, password;
                user_name = String.valueOf(textInputEditTextName.getText());
                user_surname = String.valueOf(textInputEditTextSurname.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if(!user_name.equals("") && !user_surname.equals("") && !email.equals("") && !username.equals("") && !password.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL

                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "user_name";
                            field[1] = "user_surname";
                            field[2] = "email";
                            field[3] = "username";
                            field[4] = "password";

                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = user_name;
                            data[1] = user_surname;
                            data[2] = email;
                            data[3] = username;
                            data[4] = password;

                            PutData putData = new PutData("http://192.168.0.104/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}