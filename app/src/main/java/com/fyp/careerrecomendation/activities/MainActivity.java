package com.fyp.careerrecomendation.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.fragment.AboutUsFragment;
import com.fyp.careerrecomendation.fragment.ContactUsFragment;
import com.fyp.careerrecomendation.fragment.UserHomeFragment;

import com.fyp.careerrecomendation.fragment.UserProfile;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    TextView ownername, ownermail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new UserHomeFragment()).commit();
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

       /*     getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new PatientHomeFragment()).commit();*/

        }
        else if (id == R.id.nav_profile) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new UserProfile()).addToBackStack("fragment").commit();

        }
        else if (id == R.id.nav_about) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new AboutUsFragment()).addToBackStack("fragment").commit();

        }else if (id == R.id.nav_contact) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new ContactUsFragment()).addToBackStack("fragment").commit();

        }
        else if (id == R.id.nav_logout) {

            startActivity(new Intent(this, LoginSignUpActivity.class));
            this.finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}