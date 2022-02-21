package com.fyp.careerrecomendation.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.fragment.LoginFragment;

public class LoginSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup_activity);
        LoginFragment loginFragment=new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, loginFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        super.onBackPressed();
    }
}
