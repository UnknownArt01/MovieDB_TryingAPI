package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.moviedb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {
     BottomNavigationView bottomNavigationView;
     NavHostFragment navHostFragment;
     Toolbar nan_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        nan_toolbar = findViewById(R.id.nan_toolbar);
        setSupportActionBar(nan_toolbar);
        bottomNavigationView = findViewById(R.id.botNav_mainMenu);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_frag_mainMenu);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlayingFragment, R.id.upcomingFragment).build();
        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this, navHostFragment.getNavController(), appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

}