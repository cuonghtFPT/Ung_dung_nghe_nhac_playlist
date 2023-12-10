package cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.BlankFragment;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.BlankFragment2;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.BlankFragment3;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.Fragment_All_song;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.Fragment_Lich_Su;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.Fragment_The_Loai;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.Fragment_featured_songs;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.Fragment_nhan_feedback;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.Fragment_person;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.fragment_contact;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.fragment_new_songs;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Fragment.fragment_popular_songs;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.Model.BaiHat;
import cuonghtph34430.poly.ung_dung_nghe_nhac_playlist.R;

public class MainActivity extends AppCompatActivity implements BlankFragment2.OnSongClickListener {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toobar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawlayout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackgroundResource(R.drawable.gradient_green_transparent);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        if(isAdmin){
            bottomNavigationView.getMenu().findItem(R.id.history).setVisible(false);
        }else {
            bottomNavigationView.getMenu().findItem(R.id.history).setVisible(true);
        }
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.qlHome) {
                fragment = new BlankFragment();
            } else if (item.getItemId() == R.id.qlAllSong) {
                fragment = new Fragment_All_song();
            } else if (item.getItemId() == R.id.qlFeaturedSong) {
                fragment = new Fragment_featured_songs();
            } else if (item.getItemId() == R.id.qlPopularSong) {
                fragment = new fragment_popular_songs();
            } else if (item.getItemId() == R.id.qlNewSong) {
                fragment = new fragment_new_songs();
            } else if (item.getItemId() == R.id.qlTheLoai) {
                fragment = new Fragment_The_Loai();
            } else if (item.getItemId() == R.id.qlBangXepHang) {
                fragment = new BlankFragment3();
            } else if (item.getItemId() == R.id.qlContact) {
                fragment = new fragment_contact();
            } else if (item.getItemId() == R.id.qlFeedback) {
                fragment = new Fragment_nhan_feedback();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            toolbar.setTitle(item.getTitle());
            return false;
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                fragment = new BlankFragment();
                if (item.getItemId() == R.id.home) {
                    fragment = new BlankFragment();
                } else if (item.getItemId() == R.id.history) {
                    fragment = new Fragment_Lich_Su();
                } else if(item.getItemId() == R.id.search){
                    BlankFragment2 fragment2 = new BlankFragment2();
                    fragment2.setOnSongClickListener(MainActivity.this); // Set listener
                    fragment = fragment2;
                }else {
                    fragment = new Fragment_person();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                );
                transaction.replace(R.id.frameLayout, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onSongClick(BaiHat selectedSong) {
        Intent intent = new Intent(this, Choinhac.class);
        intent.putExtra("nhac", selectedSong);
        startActivity(intent);
    }
}