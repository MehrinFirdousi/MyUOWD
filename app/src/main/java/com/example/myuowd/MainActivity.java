package com.example.myuowd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ImageView navIcon, userIcon;
    FrameLayout userProfileContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // navigation drawer
        navIcon = findViewById(R.id.mainNavButton);
        drawer = findViewById(R.id.navdrawer_layout);
        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        // user profile
        userIcon = findViewById(R.id.userAccountButton);
        userProfileContainer = (FrameLayout) findViewById(R.id.userProfileContainer);

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "User icon clicked", Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().add(R.id.userProfile, new UserProfileFragment()).commit();
                UserProfileFragment userProfileFragment = UserProfileFragment.newInstance();
                FragmentManager userFragmentManager = getSupportFragmentManager();
                FragmentTransaction userTransaction = userFragmentManager.beginTransaction();
                userTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right); // adding animations using userTransaction object
                userTransaction.addToBackStack(null);
                userTransaction.add(R.id.userProfileContainer, userProfileFragment).commit();
            }
        });

        // bottom tab navigation
        tabLayout = findViewById(R.id.bottom_nav_bar);
        viewPager = findViewById(R.id.viewPager);
        int count = tabLayout.getTabCount();

        final PageAdapter adapter = new PageAdapter(this, count);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
            new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    if (position == 0)
                    {
                        tab.setIcon(R.drawable.ic_home_24);
                        tab.setText("Home");
                    }
                    else
                    {
                        tab.setIcon(R.drawable.ic_news);
                        tab.setText("News Feed");
                    }
                }
            }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}

                /*if(tabLayout.getSelectedTabPosition() == 0)
                {
                    Toast.makeText(MainActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                }
                else if(tabLayout.getSelectedTabPosition() == 1)
                {
                    Toast.makeText(MainActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                }*/