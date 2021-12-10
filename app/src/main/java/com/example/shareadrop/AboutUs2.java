package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AboutUs2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button button_logout;

    TextView about_text;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us2);

        mAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AboutUs2.this,MainActivity.class));

            }
        });

        about_text = (TextView) findViewById(R.id.about_text);


        about_text.setText("The Share a drop was conceived in 2021 by a group of students. The initial drivers came out of frustration at a lack of support for donor milk provision and countless stories of parents who wanted to access donor milk for their babies.\n\n" +
                "Scores of families where breastfeeding was impossible because of the mother facing illness, or being absent altogether, could not dream of accessing donor milk except in very rare circumstances. The vicious circle of disinvestment and consequent lack of research had to be broken.\n\n" +
                "The Share a drop helps provide milk to families in the community aiming to make use of the 2000 litres of milk that could not be used for hospitals will be used by over 100 families, and all milk was provided under the oversight of a healthcare professional. All babies have thrived, and in the cases where the donor milk was used to support the mother to establish her own supply.\n\n" +
                "Alongside the provision of milk, the milk bank team is enabling a raft of research, including ground-breaking work into the composition of human milk over the course of natural term lactation. This is only the start of a range of research projects, working with collaborators in the Jose B. Lingad Memorial Hospital and further afield.\n\n");


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_about_us2);
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
                Intent intent = new Intent(AboutUs2.this, Home2.class);
                startActivity(intent);
                break;
            case R.id.nav_profile2:
                intent = new Intent(AboutUs2.this, Profile2.class);
                startActivity(intent);
                break;
            case R.id.nav_forum2:
                intent = new Intent(AboutUs2.this, Forum2.class);
                startActivity(intent);
                break;
            case R.id.nav_map2:
                intent = new Intent(AboutUs2.this, Map2.class);
                startActivity(intent);
                break;
            case R.id.nav_stocks2:
                intent = new Intent(AboutUs2.this, Stocks2.class);
                startActivity(intent);
                break;
            case R.id.nav_report2:
                intent = new Intent(AboutUs2.this, Report2.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us2:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_admin_reply:
                intent = new Intent(AboutUs2.this, AdminReply.class);
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
            startActivity(new Intent(AboutUs2.this, MainActivity.class));
            finish();
        }
    }
}