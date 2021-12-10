package com.example.shareadrop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Settings extends AppCompatActivity {

    Button btnUpdate;
    EditText updateFname, updateLname, updateContact, updateEmail;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        updateFname = findViewById(R.id.updateFname);
        updateLname = findViewById(R.id.updateLname);
        updateContact = findViewById(R.id.updateContact);
        updateEmail = findViewById(R.id.updateEmail);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String firstname = updateFname.getText().toString().trim();
                String lastname = updateLname.getText().toString().trim();
                String contact = updateContact.getText().toString().trim();
                String email = updateEmail.getText().toString().trim();
                if (firstname.isEmpty()) {
                    updateFname.setError("First name is required!");
                    updateFname.requestFocus();
                    return;
                }
                if (lastname.isEmpty()) {
                    updateLname.setError("Last name is required!");
                    updateLname.requestFocus();
                    return;
                }
                if (contact.isEmpty()) {
                    updateContact.setError("Contact number is required!");
                    updateContact.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    updateEmail.setError("Email is required!");
                    updateEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    updateEmail.setError("Please provide valid email");
                    updateEmail.requestFocus();
                    return;
                }

                HashMap hashMap =  new HashMap();
                hashMap.put("firstname", firstname);
                hashMap.put("lastname", lastname);
                hashMap.put("contact", contact);
                hashMap.put("email", email);


                reference.child(userID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Settings.this, "Profile successfully updated! Please login again!", Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Settings.this,MainActivity.class));
                    }
                });


            }
        });
    }
}