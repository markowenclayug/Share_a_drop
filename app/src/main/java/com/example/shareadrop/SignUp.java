package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    private TextView login, signup, txtTerms;
    private CheckBox checkBox;
    private EditText FirstName, LastName, Contact, Email, Password;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        login = (TextView) findViewById(R.id.text_login1);
        login.setOnClickListener(this);

        signup = (Button) findViewById(R.id.buttonSignUp);
        signup.setOnClickListener(this);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(this);

        FirstName = (EditText) findViewById(R.id.editTextFirstName);
        LastName = (EditText) findViewById(R.id.editTextLastName);
        Contact = (EditText) findViewById(R.id.editTextContact);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        txtTerms = findViewById(R.id.txtTerms);
        txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Terms.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_login1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.buttonSignUp:
                signup();
                break;
        }
    }

    private void signup() {
        String firstname = FirstName.getText().toString().trim();
        String lastname = LastName.getText().toString().trim();
        String contact = Contact.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (firstname.isEmpty()) {
            FirstName.setError("First name is required!");
            FirstName.requestFocus();
            return;
        }
        if (lastname.isEmpty()) {
            LastName.setError("Last name is required!");
            LastName.requestFocus();
            return;
        }
        if (contact.isEmpty()) {
            Contact.setError("Contact number is required!");
            Contact.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            Email.setError("Email is required!");
            Email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Please provide valid email");
            Email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Password.setError("Password is required!");
            Password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Password.setError("Minimum password length should be 6 characters!");
            Password.requestFocus();
            return;
        }
        if(!checkBox.isChecked()){
            Toast.makeText(SignUp.this, "Agree to the Terms of Services and Privacy Policies first before you sign up!", Toast.LENGTH_LONG).show();
            return;
        }

        progressBar2.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(firstname, lastname, contact, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                        }
                        progressBar2.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "User registered successfully!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                     }
                });


    }
}