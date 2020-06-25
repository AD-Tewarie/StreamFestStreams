package com.example.streamfeststreams;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.streamfeststreams.fragments.HomeFragment;
import com.example.streamfeststreams.fragments.ListFragment;
import com.example.streamfeststreams.fragments.PlaylistFragment;
import com.example.streamfeststreams.fragments.ProfileFragment;
import com.example.streamfeststreams.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListActivity extends AppCompatActivity {


        private HomeFragment homeFragment = new HomeFragment();
        private PlaylistFragment playlistFragment = new PlaylistFragment();
        private SearchFragment searchFragment = new SearchFragment();
        private ProfileFragment profileFragment = new ProfileFragment();
        private ListFragment listFragment = new ListFragment();

        private BottomNavigationView menuNav;

        SharedPreferences sharedPreferences;
        boolean isDarkModeOn;
        SharedPreferences.Editor editor;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            menuNav = findViewById(R.id.menu);

            setFragment (listFragment);
            menuNav.setSelectedItemId(R.id.playlist_home);
            menuNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.isChecked()){
                        return true;
                    }else {
                        switch (item.getItemId()){

                            case R.id.playlist_home:
                                setFragment(playlistFragment);
                                getSupportActionBar().setTitle("Playlist");
                                return true;

                            case R.id.search_home:
                                setFragment(searchFragment);
                                getSupportActionBar().setTitle("Search");
                                return true;

                            case R.id.profile_home:
                                setFragment(profileFragment);
                                getSupportActionBar().setTitle("Profile");
                                return true;

                            default:
                                setFragment(homeFragment);
                                getSupportActionBar().setTitle("Home");
                                return true;


                        }
                    }
                }
            });

            sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

            if (isDarkModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.menu_top is a reference to an xml file named menu_top.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.day_night_mode) {
            if (isDarkModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("isDarkModeOn", false);
                editor.apply();

            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("isDarkModeOn", true);
                editor.apply();
            }
        }
        setFragment(homeFragment);
        menuNav.setSelectedItemId(R.id.menu_home);
        getSupportActionBar().setTitle("Home");
        return super.onOptionsItemSelected(item);
    }

        private void setFragment(Fragment fragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }
    }


