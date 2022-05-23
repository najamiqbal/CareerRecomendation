package com.fyp.careerrecomendation.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.fragment.FeedBackFragment;
import com.fyp.careerrecomendation.fragment.ConselorsListFragment;
import com.fyp.careerrecomendation.fragment.ContactUsFragment;
import com.fyp.careerrecomendation.fragment.MyQueriesFragment;
import com.fyp.careerrecomendation.fragment.UserHomeFragment;

import com.fyp.careerrecomendation.fragment.UserProfile;
import com.fyp.careerrecomendation.models.UserModelClass;
import com.fyp.careerrecomendation.utils.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    TextView ownername, ownermail,became_counselor;
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
        ownername = navigationView.getHeaderView(0).findViewById(R.id.username);
        ownermail = navigationView.getHeaderView(0).findViewById(R.id.useremail);
        became_counselor = navigationView.findViewById(R.id.become_conselor);
        became_counselor.setOnClickListener(view -> {

            startActivity(new Intent(this,CounselorRegistrationForm.class));

        });

        final UserModelClass userModelClass = SharedPrefManager.getInstance(MainActivity.this).getUser();
        if (userModelClass != null) {
            ownername.setText(userModelClass.getUser_name());
            ownermail.setText(userModelClass.getUser_email());
        }
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
        if (id == R.id.nav_consultants_list) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new ConselorsListFragment()).addToBackStack("fragment").commit();

        }
        else if (id == R.id.nav_profile) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new UserProfile()).addToBackStack("fragment").commit();

        }
        else if (id == R.id.nav_my_queries) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new MyQueriesFragment()).addToBackStack("fragment").commit();

        }

        else if (id == R.id.nav_admin_dashboard) {

            String url = "https://devapis.tk/career-recommendation/login.php";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        }
        else if (id == R.id.nav_feedback) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new FeedBackFragment()).addToBackStack("fragment").commit();

        }else if (id == R.id.nav_contact) {

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.user_main_frame,
                    new ContactUsFragment()).addToBackStack("fragment").commit();

        }
        else if (id == R.id.nav_logout) {
            SharedPrefManager.getInstance(MainActivity.this).logOut();
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