package com.example.shareadrop;

import androidx.annotation.NonNull;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Report extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout, button_report, button_FalseInfo, button_Violence, button_Harassment, button_Scam, button_Sexual, button_Spam;
    EditText editTextReport, report_text;


    private String userID;
    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Report");

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_report);
        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        userID = user.getUid();
        final TextView txtSender = (TextView) findViewById(R.id.txtSender);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String firstname = userProfile.firstname;
                    txtSender.setText(firstname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txtSender.setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        editTextReport = findViewById(R.id.editTextReport);
        report_text = findViewById(R.id.report_text);
        report_text.setVisibility(View.GONE);

        button_FalseInfo = findViewById(R.id.button_FalseInfo);
        button_FalseInfo.setOnClickListener(this);
        button_Violence = findViewById(R.id.button_Violence);
        button_Violence.setOnClickListener(this);
        button_Harassment = findViewById(R.id.button_Harassment);
        button_Harassment.setOnClickListener(this);
        button_Scam = findViewById(R.id.button_Scam);
        button_Scam.setOnClickListener(this);
        button_Sexual = findViewById(R.id.button_Sexual);
        button_Sexual.setOnClickListener(this);
        button_Spam = findViewById(R.id.button_Spam);
        button_Spam.setOnClickListener(this);


        button_report = findViewById(R.id.button_report);
        button_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TextReport = editTextReport.getText().toString().trim();
                String ReportText = report_text.getText().toString().trim();
                String sender = txtSender.getText().toString();
                root.child(userID).setValue(sender+": "+ TextReport +" (" + ReportText+")");

                if (TextReport.isEmpty()) {
                    editTextReport.setError("Please provide a report Statement");
                    editTextReport.requestFocus();
                    return;
                }
                if (ReportText.isEmpty()) {
                    Toast.makeText(Report.this, "Please select a button which applies to your report!", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(Report.this, "Your report has been submitted!", Toast.LENGTH_LONG).show();
                editTextReport.setText("");
                report_text.setText("");
            }
        });

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Report.this,MainActivity.class));

            }
        });


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_report);
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
    public void onClick(View view) {

        String ReportText = report_text.getText().toString().trim();
        switch (view.getId()) {
            case R.id.button_FalseInfo:
                report_text.setText(ReportText+"FALSE INFORMATION - ");
                button_FalseInfo.setEnabled(false);
                button_FalseInfo.setBackgroundColor(Color.parseColor("#9E9493"));
                break;
            case R.id.button_Violence:
                report_text.setText(ReportText+"VIOLENCE - ");
                button_Violence.setEnabled(false);
                button_Violence.setBackgroundColor(Color.parseColor("#9E9493"));
                break;
            case R.id.button_Harassment:
                report_text.setText(ReportText+"HARASSMENT - ");
                button_Harassment.setEnabled(false);
                button_Harassment.setBackgroundColor(Color.parseColor("#9E9493"));
                break;
            case R.id.button_Scam:
                report_text.setText(ReportText+"SCAM/MISLEADING -");
                button_Scam.setEnabled(false);
                button_Scam.setBackgroundColor(Color.parseColor("#9E9493"));
                break;
            case R.id.button_Sexual:
                report_text.setText(ReportText+"SEXUAL BEHAVIOUR - ");
                button_Sexual.setEnabled(false);
                button_Sexual.setBackgroundColor(Color.parseColor("#9E9493"));
                break;
            case R.id.button_Spam:
                report_text.setText(ReportText+"SPAM - ");
                button_Spam.setEnabled(false);
                button_Spam.setBackgroundColor(Color.parseColor("#9E9493"));
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(Report.this, Home.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                intent = new Intent(Report.this, Profile.class);
                startActivity(intent);
                break;
            case R.id.nav_forum:
                intent = new Intent(Report.this, Forum.class);
                startActivity(intent);
                break;
            case R.id.nav_map:
                intent = new Intent(Report.this, Map.class);
                startActivity(intent);
                break;
            case R.id.nav_report:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_about_us:
                intent = new Intent(Report.this, AboutUs.class);
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
            startActivity(new Intent(Report.this, MainActivity.class));
            finish();
        }
    }


}