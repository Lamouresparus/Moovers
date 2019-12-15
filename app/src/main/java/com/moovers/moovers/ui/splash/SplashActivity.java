package com.moovers.moovers.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moovers.moovers.R;
import com.moovers.moovers.ui.auth.AuthenticationActivity;
import com.moovers.moovers.ui.category.CategoryActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null){
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, AuthenticationActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
