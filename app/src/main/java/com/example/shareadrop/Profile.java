package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kotlinx.coroutines.channels.ProduceKt;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout, btnDelete, button_editprof, button_donate, button_receive;;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private String userID = user.getUid();
    private FirebaseAuth mAuth;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        mAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        final TextView firstnameTextView = (TextView) findViewById(R.id.edit_fname);
        final TextView lastnameTextView = (TextView) findViewById(R.id.edit_lname);
        final TextView contactTextView = (TextView) findViewById(R.id.edit_contact);
        final TextView emailTextView = (TextView) findViewById(R.id.edit_email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String firstname = userProfile.firstname;
                    String lastname = userProfile.lastname;
                    String contact = userProfile.contact;
                    String email = userProfile.email;

                    firstnameTextView.setText(firstname);
                    lastnameTextView.setText(lastname);
                    contactTextView.setText(contact);
                    emailTextView.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this,MainActivity.class));

            }
        });

        button_editprof =  findViewById(R.id.button_editprof);
        button_editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Settings.class);
                startActivity(intent);
            }
        });

        button_donate = findViewById(R.id.button_donate);
        button_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Donate.class);
                startActivity(intent);

            }
        });
        button_receive = findViewById(R.id.button_receive);
        button_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Receive.class);
                startActivity(intent);

            }
        });

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_profile);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Profile.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in completely removing your account from the system and you wont be able to access the app.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Profile.this, "Account successfully deleted!", Toast.LENGTH_LONG).show();
                                    reference.child(userID).removeValue();
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(Profile.this,MainActivity.class));
                                }else{
                                    Toast.makeText(Profile.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(Profile.this, Home.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_forum:
                intent = new Intent(Profile.this, Forum.class);
                startActivity(intent);
                break;
            case R.id.nav_map:
                intent = new Intent(Profile.this, Map.class);
                startActivity(intent);
                break;
            case R.id.nav_report:
                intent = new Intent(Profile.this, Report.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us:
                intent = new Intent(Profile.this, AboutUs.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Profile.this, MainActivity.class));
            finish();
        }
    }
}