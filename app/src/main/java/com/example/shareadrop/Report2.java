package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Report2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout;



    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Report");
    private ListView listView;
    private ArrayAdapter msgAdapter;



    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);
        mAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar);



        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Report2.this,MainActivity.class));

            }
        });


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_report2);

        listView = findViewById(R.id.list1);

        ArrayList<String> msgList =  new ArrayList<String>();

        msgAdapter = new ArrayAdapter<String>(this,R.layout.reportlistitem,msgList);

        listView.setAdapter(msgAdapter);

        loadMsg();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home2:
                Intent intent = new Intent(Report2.this, Home2.class);
                startActivity(intent);
                break;
            case R.id.nav_profile2:
                intent = new Intent(Report2.this, Profile2.class);
                startActivity(intent);
                break;
            case R.id.nav_forum2:
                intent = new Intent(Report2.this, Forum2.class);
                startActivity(intent);
                break;
            case R.id.nav_map2:
                intent = new Intent(Report2.this, Map2.class);
                startActivity(intent);
                break;
            case R.id.nav_stocks2:
                intent = new Intent(Report2.this, Stocks2.class);
                startActivity(intent);
                break;
            case R.id.nav_report2:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_about_us2:
                intent = new Intent(Report2.this, AboutUs2.class);
                startActivity(intent);
                break;
            case R.id.nav_admin_reply:
                intent = new Intent(Report2.this, AdminReply.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Report2.this, MainActivity.class));
            finish();
        }
    }

    private void loadMsg(){
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                msgAdapter.add(snapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}