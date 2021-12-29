package com.example.myuowd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ImageView mainNavButton, userButton, backButton, fragNavButton;
    FrameLayout userProfileContainer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar1);

        toolbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                drawer = findViewById(R.id.navdrawer_layout);

                // when no fragments are open
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    // main navigation drawer
                    mainNavButton = findViewById(R.id.mainNavButton);
                    mainNavButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            drawer.openDrawer(GravityCompat.START);
                        }
                    });

                    // user profile
                    userButton = findViewById(R.id.userAccountButton);
                    userProfileContainer = (FrameLayout) findViewById(R.id.fragmentContainer);

                    userButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openFragment(UserProfileFragment.newInstance());
                        }
                    });
                }
                // any fragment is open
                else {
                    backButton = findViewById(R.id.fragBackButton);
                    backButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                    fragNavButton = findViewById(R.id.fragNavButton);
                    fragNavButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            drawer.openDrawer(Gravity.LEFT);
                        }
                    });
                }
            }
        });

        // navigation view options
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        else if (getSupportFragmentManager().getBackStackEntryCount() == 1) // reached main activity layout
        {
            // changing the toolbar back to original layout without back button
            getSupportActionBar().setCustomView(R.layout.toolbar1);
            super.onBackPressed();
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_timetable:
                openFragment(TimetablesFragment.newInstance());
                break;
            case R.id.nav_academic_cal:
                openFragment(TimetablesFragment.newInstance());
                break;
            case R.id.nav_forms:
                openFragment(FormsFragment.newInstance());
                break;
            case R.id.nav_policies:
                openFragment(PoliciesFragment.newInstance());
                break;
            case R.id.nav_degreeplanner:
                openFragment(DegreePlannersFragment.newInstance());
                break;
            case R.id.nav_staffdirectory:
                openFragment(StaffDirectoryFragment.newInstance());
                break;
            case R.id.nav_eventcalendar:
                openFragment(TimetablesFragment.newInstance());
                break;
            case R.id.nav_subjectsoffered:
                openFragment(TimetablesFragment.newInstance());
                break;
            case R.id.nav_feedback:
                openFragment(TimetablesFragment.newInstance());
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right); // adding animations using userTransaction object
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragmentContainer, fragment).commit();

        // changing toolbar layout to contain back button instead
        getSupportActionBar().setCustomView(R.layout.toolbar2);
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