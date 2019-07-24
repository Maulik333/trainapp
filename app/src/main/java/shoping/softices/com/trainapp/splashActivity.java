package shoping.softices.com.trainapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int secondsDelayed = 5;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent;
                if (isUserLoggedIn()) {
                    intent = new Intent(splashActivity.this, NavigationDrawerActivity.class);
                } else {
                    intent = new Intent(splashActivity.this, SigninActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, secondsDelayed * 1000);
    }
    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean("is_user_login", false);
    }
}
