package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.List;

public class Stocks2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Stocks");

    private FirebaseAuth mAuth;
    private RecyclerView recyclerview;
    private stockAdapter adapter;
    private List<stockmodel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks2);
        mAuth = FirebaseAuth.getInstance();

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);

        recyclerview.setLayoutManager(new LinearLayoutManager(  this));

        list = new ArrayList<>();
        adapter = new stockAdapter( this , list);




        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar);
        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Stocks2.this,MainActivity.class));

            }
        });


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_stocks2);

    }

   /* private void showData() {
        db.getReference("Stocks").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        list.clear();
                        for(DataSnapshot snapshot : ){

                            stockmodel model = new stockmodel(snapshot.getValue().toString());
                        }
                    }
                });
    }*/

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
                Intent intent = new Intent(Stocks2.this, Home2.class);
                startActivity(intent);
                break;
            case R.id.nav_profile2:
                intent = new Intent(Stocks2.this, Profile2.class);
                startActivity(intent);
                break;
            case R.id.nav_forum2:
                intent = new Intent(Stocks2.this, Forum2.class);
                startActivity(intent);
                break;
            case R.id.nav_map2:
                intent = new Intent(Stocks2.this, Map2.class);
                startActivity(intent);
                break;
            case R.id.nav_stocks2:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_report2:
                intent = new Intent(Stocks2.this, Report2.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us2:
                intent = new Intent(Stocks2.this, AboutUs2.class);
                startActivity(intent);
                break;
            case R.id.nav_admin_reply:
                intent = new Intent(Stocks2.this, AdminReply.class);
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
            startActivity(new Intent(Stocks2.this, MainActivity.class));
            finish();
        }
    }


}