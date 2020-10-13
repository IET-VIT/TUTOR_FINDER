package com.vithack.tutorfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences loginPrefs;

    public static String POSITION;
    public static String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginPrefs = getSharedPreferences("Login", MODE_PRIVATE);
        POSITION = loginPrefs.getString("Type",null);
        ID = loginPrefs.getString("ID",null);
        Log.e("Info", ID + "Hi");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_news_feed, R.id.nav_post, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View navView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView nav_name_text = navView.findViewById(R.id.nav_name_text);
        TextView nav_mail_text = navView.findViewById(R.id.nav_mail_text);
        nav_name_text.setText(loginPrefs.getString("FullName", "Loading..."));
        nav_mail_text.setText(loginPrefs.getString("Username","Loading..."));

        TextView visit_site = findViewById(R.id.visit_site);
        visit_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://findtutor.infinityfreeapp.com/"));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_logout:
                SharedPreferences.Editor Ed = loginPrefs.edit();
                Ed.putString("Username", null);
                Ed.commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;

            case R.id.action_profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                return true;

            default:
                return false;
        }
    }
}