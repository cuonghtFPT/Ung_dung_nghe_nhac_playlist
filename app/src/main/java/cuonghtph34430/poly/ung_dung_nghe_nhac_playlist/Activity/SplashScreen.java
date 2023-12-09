package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Login.Log_in;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lotties;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("language", "vi");

        // Set the retrieved language as the app's locale
        Locale locale = new Locale(selectedLanguage);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_splash_screen);
        lotties = findViewById(R.id.lotties);
        logo = findViewById(R.id.logo);
        lotties.animate().setDuration(4000).setStartDelay(3);
        new Handler().postDelayed(() -> {
            // Start your main activity here
            Intent intent = new Intent(SplashScreen.this, Log_in.class);
            startActivity(intent);
            finish(); // Close the splash activity
        }, 5000);
    }
}