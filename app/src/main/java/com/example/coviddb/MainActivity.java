package com.example.coviddb;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{
    Button statisticsButton;
    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_ACCOUNT = 2;
    private static final int POS_STATISTICS = 3;
    private static final int POS_ABOUT_US = 4;
    private static final int POS_SETTINGS = 5;
    private static final int POS_LOGOUT = 7;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    private Button button2,button,button4;

    private SlidingRootNav slidingRootNav;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        statisticsButton = findViewById(R.id.button5);
        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HelplineActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NotesActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PostReviewActivity.class);
                startActivity(intent);
            }
        });
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
//                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();
        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE).setChecked(true),
                createItemFor(POS_DASHBOARD),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_STATISTICS),
                createItemFor(POS_ABOUT_US),
                createItemFor(POS_SETTINGS),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);
        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }




    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.purple_500))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.purple_500))
                .withSelectedTextTint(color(R.color.purple_500));
    }
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(position == POS_DASHBOARD){
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            transaction.replace(R.id.container,dashBoardFragment);
        }
        else if(position == POS_ACCOUNT){
            AccountFragment accountFragment = new AccountFragment();
            transaction.replace(R.id.container,accountFragment);
        }
        else if(position == POS_STATISTICS){
            StatisticsFragment statisticsFragment = new StatisticsFragment();
            transaction.replace(R.id.container,statisticsFragment);
        }
        else if(position == POS_ABOUT_US){
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            transaction.replace(R.id.container,aboutUsFragment);
        }
        else if(position == POS_SETTINGS){
            SettingsFragment settingsFragment = new SettingsFragment();
            transaction.replace(R.id.container,settingsFragment);
        }
        else if(position == POS_LOGOUT){
          finish();
        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
}