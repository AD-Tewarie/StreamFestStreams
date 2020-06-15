package com.example.streamfeststreams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.streamfeststreams.fragments.HomeFragment;
import com.example.streamfeststreams.fragments.PlaylistFragment;
import com.example.streamfeststreams.fragments.ProfileFragment;
import com.example.streamfeststreams.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private PlaylistFragment playlistFragment = new PlaylistFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView menuNav;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        menuNav = findViewById(R.id.menu);

        setFragment (homeFragment);
        menuNav.setSelectedItemId(R.id.menu_home);
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
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
    }
}
