package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Map2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout;

    TextView txtShow;

    EditText edittext_source,edittext_destination;
    Button button_track;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar);

        mAuth = FirebaseAuth.getInstance();

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.place, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Map2.this,MainActivity.class));

            }
        });

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_map2);

        edittext_source = findViewById(R.id.edittext_source);
        edittext_source.setVisibility(View.GONE);
        edittext_destination = findViewById(R.id.edittext_destination);
        button_track = findViewById(R.id.button_track);

        button_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource =  edittext_source.getText().toString().trim();
                String sDestination =  edittext_destination.getText().toString().trim();

                DisplayTrack(sSource,sDestination);
            }
        });

        /*txtShow = findViewById(R.id.txtShow);
        txtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Map.this, CurLocation.class));
            }
        });*/

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
                Intent intent = new Intent(Map2.this, Home2.class);
                startActivity(intent);
                break;
            case R.id.nav_profile2:
                intent = new Intent(Map2.this, Profile2.class);
                startActivity(intent);
                break;
            case R.id.nav_forum2:
                intent = new Intent(Map2.this, Forum2.class);
                startActivity(intent);
                break;
            case R.id.nav_map2:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_stocks2:
                intent = new Intent(Map2.this, Stocks2.class);
                startActivity(intent);
                break;
            case R.id.nav_report2:
                intent = new Intent(Map2.this, Report2.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us2:
                intent = new Intent(Map2.this, AboutUs2.class);
                startActivity(intent);
                break;
            case R.id.nav_admin_reply:
                intent = new Intent(Map2.this, AdminReply.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        edittext_destination.setText(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void DisplayTrack(String sSource, String sDestination) {

        try{
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ sSource +"/"+ sDestination);

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setPackage("com.google.android.apps.maps");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Map2.this, MainActivity.class));
            finish();
        }
    }

}