package com.example.janaticketking;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

public class UserAccountActivity extends AppCompatActivity {
    FragmentManager manager = getSupportFragmentManager();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_account:
                    manager.beginTransaction().replace(R.id.fragmentFrame, new AccountFragment()).commit();
                    return true;
                case R.id.navigation_changepassword:

                    return true;
                case R.id.navigation_privacy:

                    return true;
                case R.id.navigation_deleteaccount:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        manager.beginTransaction().replace(R.id.fragmentFrame, new AccountFragment()).commit();

    }

}
