package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Forum extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout;

    private Button sendBtn;
    private ListView listView;
    private ArrayAdapter msgAdapter;

    private EditText msgET;
    private String userID;
    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Groupchat");

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forum);

        mAuth = FirebaseAuth.getInstance();



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String time = simpleDateFormat.format(calendar.getTime());


        final TextView senderET = (TextView) findViewById(R.id.senderET);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String firstname = userProfile.firstname;
                    senderET.setText(firstname);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        senderET.setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Forum.this,MainActivity.class));

            }
        });

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_forum);

        sendBtn = findViewById(R.id.sendBtn);
        msgET = findViewById(R.id.msgET);
        listView = findViewById(R.id.list1);

        ArrayList<String> msgList =  new ArrayList<String>();

        msgAdapter = new ArrayAdapter<String>(this,R.layout.listitem,msgList);

        listView.setAdapter(msgAdapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sender = senderET.getText().toString();
                String msg = msgET.getText().toString();


                if(msg.length()>0 && sender.length()>0)
                {
                    root.push().setValue(sender +":  "+msg +" " + "\n\n"+ time);

                    msgET.setText("");
                }
                else {

                }




            }
        });

        loadMsg();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(Forum.this, Home.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                intent = new Intent(Forum.this, Profile.class);
                startActivity(intent);
                break;
            case R.id.nav_forum:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_map:
                intent = new Intent(Forum.this, Map.class);
                startActivity(intent);
                break;
            case R.id.nav_report:
                intent = new Intent(Forum.this, Report.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us:
                intent = new Intent(Forum.this, AboutUs.class);
                startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
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
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Forum.this, MainActivity.class));
            finish();
        }
    }
}