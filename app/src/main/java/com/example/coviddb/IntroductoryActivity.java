package com.example.coviddb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView logo, appName, splashImg;
    TextView introQuote;
    LottieAnimationView lottieAnimationView;
    private static final int NUM_PAGES = 2;
    private ViewPager viewPager;
    private ScreenSlidePageAdapter pageAdapter;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        logo = findViewById(R.id.logo);
        introQuote = findViewById(R.id.introQuote);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        viewPager = findViewById(R.id.pager);
        pageAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        anim = AnimationUtils.loadAnimation(this,R.anim.o_b_anim);
        viewPager.startAnimation(anim);
        splashImg.animate().translationY(-2000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        introQuote.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);


    }

    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    OnBoardingFragement1 tab1 = new OnBoardingFragement1();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;

            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}