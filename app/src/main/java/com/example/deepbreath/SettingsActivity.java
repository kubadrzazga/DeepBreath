package com.example.deepbreath;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    public static TextView dataSettings;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent1 = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(SettingsActivity.this, MapsActivity.class);
                    startActivity(intent2);


                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationSettings);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dataSettings = (TextView) findViewById(R.id.textView3);



    }
}
