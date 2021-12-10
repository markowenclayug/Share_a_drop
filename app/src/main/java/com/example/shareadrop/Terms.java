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
import com.google.firebase.database.core.Repo;

public class Terms extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout;

    TextView about_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        about_text = (TextView) findViewById(R.id.about_text);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Terms.this,MainActivity.class));

            }
        });


        about_text.setText("Terms & Conditions \n\n" +
                "Updated at 2021-11-02 \n\n" +
                "General Terms \n\n" +
                "By accessing and placing an order with Share A Drop: An App-Based Breast-Feeding Information and Sharing Platform, you confirm that you are in agreement with and bound by the terms of service contained in the Terms & Conditions outlined below. These terms apply to the entire website and any email or other type of communication between you and Share A Drop: An App-Based Breast-Feeding Information and Sharing Platform. \n\n" +
                "Under no circumstances shall Share A Drop: An App-Based Breast-Feeding Information and Sharing Platform team be liable for any direct, indirect, special, incidental or consequential damages, including, but not limited to, loss of data or profit, arising out of the use, or the inability to use, the materials on this site, even if Share A Drop: An App-Based Breast-Feeding Information and Sharing Platform team or an authorized representative has been advised of the possibility of such damages. If your use of materials from this site results in the need for servicing, repair or correction of equipment or data, you assume any costs thereof. \n\n" +
                "Share A Drop: An App-Based Breast-Feeding Information and Sharing Platform will not be responsible for any outcome that may occur during the course of usage of our resources. We reserve the rights to change prices and revise the resources usage policy in any moment.\n\n");


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_about_us);
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
            case R.id.nav_home:
                Intent intent = new Intent(Terms.this, Home.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                intent = new Intent(Terms.this, Profile.class);
                startActivity(intent);
                break;
            case R.id.nav_forum:
                intent = new Intent(Terms.this, Forum.class);
                startActivity(intent);
                break;
            case R.id.nav_map:
                intent = new Intent(Terms.this, Map.class);
                startActivity(intent);
                break;
            case R.id.nav_report:
                intent = new Intent(Terms.this, Report.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us:
                intent = new Intent(Terms.this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                intent = new Intent(Terms.this, Settings.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}