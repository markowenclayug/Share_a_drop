package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_login;
    private EditText editTextEmailAddress, editTextPass;
    private TextView signup, forgot_pass;


    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(this);

        signup = (TextView) findViewById(R.id.text_SignUp);
        signup.setOnClickListener(this);

        forgot_pass = (TextView) findViewById(R.id.text_ForgotPass);
        forgot_pass.setOnClickListener(this);

        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPass = (EditText) findViewById(R.id.editTextPass);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.text_SignUp:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.text_ForgotPass:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
            case R.id.button_login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmailAddress.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmailAddress.setError("Email is required!");
            editTextEmailAddress.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.setError("Please enter a valid email!");
            editTextEmailAddress.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPass.setError("Password is required!");
            editTextPass.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPass.setError("Minimum password length is 6 characters!");
            editTextPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                   if(user.isEmailVerified()) {
                       //redirect to home
                       if (user != null){
                           startActivity(new Intent(MainActivity.this, GetLevel.class));
                           finish();
                       }
                   }else{
                       user.sendEmailVerification();
                       Toast.makeText(MainActivity.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                       progressBar.setVisibility(View.GONE);
                   }
                }else{
                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();
            if (user != null) {
                if(user.isEmailVerified()) {
                    startActivity(new Intent(MainActivity.this, GetLevel.class));
                    finish();
                }
            }

    }
}