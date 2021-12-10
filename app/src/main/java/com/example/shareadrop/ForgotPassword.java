package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener{

    private TextView login;
    private EditText editTextForgotEmail;
    private Button button_forgot;
    private ProgressBar progressBar3;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        login = (TextView) findViewById(R.id.text_login2);
        login.setOnClickListener(this);

        editTextForgotEmail = (EditText) findViewById(R.id.editTextForgotEmail);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

        auth = FirebaseAuth.getInstance();

        button_forgot = (Button) findViewById(R.id.button_forgot);
        button_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_forgot();
            }
        });
    }

    private void button_forgot(){
        String email = editTextForgotEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextForgotEmail.setError("Email is required!");
            editTextForgotEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextForgotEmail.setError("Please provide valid email!");
            editTextForgotEmail.requestFocus();
            return;
        }

        progressBar3.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Please check your email to reset your password!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                }else{
                    Toast.makeText(ForgotPassword.this, "Error! Please try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.text_login2:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}