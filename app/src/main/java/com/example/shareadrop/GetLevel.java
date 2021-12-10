package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetLevel extends AppCompatActivity {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private String userID = user.getUid();
    private FirebaseAuth mAuth;

    public String get_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_level);

        mAuth = FirebaseAuth.getInstance();




        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                   get_level = userProfile.level;
                    Intent intent;
                    if (get_level != null){
                        intent = new Intent(GetLevel.this, Home2.class);
                    }else{
                        intent = new Intent(GetLevel.this, Home.class);
                    }
                    startActivity(intent);


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}