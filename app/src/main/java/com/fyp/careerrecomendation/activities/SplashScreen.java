package com.fyp.careerrecomendation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fyp.careerrecomendation.models.UserModelClass;
import com.fyp.careerrecomendation.utils.SharedPrefManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Handle the splash screen transition.
        UserModelClass userModelClass = SharedPrefManager.getInstance(this).getUser();
        if (userModelClass != null) {

                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

        } else {
            Intent intent = new Intent(this, LoginSignUpActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
