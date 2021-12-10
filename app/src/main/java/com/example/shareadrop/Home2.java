package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;

public class Home2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private ViewPager mSlideView;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;

    private TextView[] mdots;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    public String userID = user.getUid();


    androidx.recyclerview.widget.RecyclerView recyclerView;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();
    private Object RecyclerView;



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button button_logout;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar);

        mSlideView = (ViewPager) findViewById(R.id.slideView);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);
        mSlideView.setAdapter(sliderAdapter);

        addDotsIndicator( 0);




        mSlideView.addOnPageChangeListener(viewListener);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home2.this,MainActivity.class));

            }
        });

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home2);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/R9mYuYH1t8M\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vXOnJ0DT_xI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-JjFzjrZRBM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Df9tg25i5V8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3wwzUSC5RU4\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);



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
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_profile2:
                Intent intent = new Intent(Home2.this, Profile2.class);
                startActivity(intent);
                break;
            case R.id.nav_forum2:
                intent = new Intent(Home2.this, stockadd.class);
                startActivity(intent);
                break;
            case R.id.nav_map2:
                intent = new Intent(Home2.this, Map2.class);
                startActivity(intent);
                break;
            case R.id.nav_stocks2:
                intent = new Intent(Home2.this, Stocks2.class);
                startActivity(intent);
                break;
            case R.id.nav_report2:
                intent = new Intent(Home2.this, Report2.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us2:
                intent = new Intent(Home2.this, AboutUs2.class);
                startActivity(intent);
                break;
            case R.id.nav_admin_reply:
                intent = new Intent(Home2.this, AdminReply.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public  void  addDotsIndicator(int position){


        mdots = new TextView[13];
        mDotLayout.removeAllViews();
        for(int i = 0; i < mdots.length; i++)
        {
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.gray));

            mDotLayout.addView(mdots[i]);
        }
        if(mdots.length > 0 )
        {
            mdots[position].setTextColor(getResources().getColor(R.color.teal_200));
        }
    }

    ViewPager.OnPageChangeListener viewListener =  new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(Home2.this, MainActivity.class));
            finish();
        }
    }
}