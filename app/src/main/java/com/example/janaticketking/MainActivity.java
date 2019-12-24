package com.example.janaticketking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.viewpagerindicator.CirclePageIndicator;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText editText;
    DrawerLayout drawer;
    NavigationView navigationView;
    FloatingActionButton fab;
    RecyclerView upcomingEventRecyclerView;
    ViewPager viewPager;
    ImageView postEvent;


    ArrayList<EventModel> eventModels = new ArrayList<>();
    ArrayList<EventModel> slidingModels = new ArrayList<>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindActivity();

        SharedPreferences prefs = getSharedPreferences(Constant.SHAREDPREF, MODE_PRIVATE);
        String restoredText = prefs.getString(Constant.SHAREDPREFNAME, null);

        if (restoredText != null) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_login);
            TextView displayUserName, userNameBar;
            displayUserName = (TextView) findViewById(R.id.displayUserName);
            displayUserName.setText("Hello, " + restoredText);


        } else {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_notlogin);
            TextView displayUserName;
            displayUserName = (TextView) findViewById(R.id.displayUserName);
            displayUserName.setText("Ticket King");

        }

        initUpcomingEvents();
        initSlidingImage();
    }


    private void initSlidingImage() {
        mPager = (ViewPager) findViewById(R.id.viewPagerPager);
        mPager.setAdapter(new SlidingImageAdaptor(eventModels, this));
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

        slidingModels.add(new EventModel("title", "description", "http://ticketking.com.au/wp-content/uploads/2019/05/banner-73-1728x622.jpg"));
        slidingModels.add(new EventModel("title", "description", "http://ticketking.com.au/wp-content/uploads/2019/05/banner-72-2528x910.jpg"));

        SlidingImageAdaptor slidingImageAdaptor = new SlidingImageAdaptor(slidingModels, this);
        viewPager.setAdapter(slidingImageAdaptor);
    }


    private void initUpcomingEvents() {
        eventModels.add(new EventModel("Music", "Music fest", "https://cdn.pastemagazine.com/www/articles/Okeechobee%20Fest%202017%20Lineup%20Main.jpg"));
        eventModels.add(new EventModel("Sports", "Sports tournament", "https://www.bls.gov/spotlight/2017/sports-and-exercise/images/cover_image.jpg"));
        eventModels.add(new EventModel("Movie", "New movie tickets", "https://boygeniusreport.files.wordpress.com/2016/03/movies-tiles.jpg?quality=98&strip=all"));
        eventModels.add(new EventModel("Education", "Education offers", "https://www.teledataict.com/media/2017/08/internet-and-education-900x600.jpg"));
        eventModels.add(new EventModel("Parties", "Parties offers ", "https://www.parklandsresort.com.au/wp-content/uploads/parties-image.jpg"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        EventAdaptor eventAdaptor = new EventAdaptor(eventModels, this);

        upcomingEventRecyclerView.setAdapter(eventAdaptor);
        upcomingEventRecyclerView.setLayoutManager(layoutManager);
    }

    private void bindActivity() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.searchView);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_sort_black_24dp);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPagerPager);
        upcomingEventRecyclerView = (RecyclerView) findViewById(R.id.upcomingEventRecyclerView);
        postEvent = (ImageView) findViewById(R.id.postEvent);


    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_events) {
            startActivity(new Intent(MainActivity.this, CategoryActivity.class));


        } else if (id == R.id.nav_mytickets) {
            startActivity(new Intent(MainActivity.this, UserAccountActivity.class));


        } else if (id == R.id.nav_login) {
            startActivity(new Intent(MainActivity.this, LogInActivity.class));

        } else if (id == R.id.nav_accountsetting) {

        } else if (id == R.id.nav_signout) {
            singOut();

        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void singOut() {
        SharedPreferences prefs = getSharedPreferences(Constant.SHAREDPREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        finish();

        Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Log out successful", Toast.LENGTH_SHORT).show();

    }


}
