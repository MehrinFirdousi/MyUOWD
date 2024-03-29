package com.example.myuowd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ImageView mainNavButton, userButton, backButton, fragNavButton;
    NavigationView navigationView;
    MaterialButton logoutButton, resetPasswordButton;
    AlertDialog.Builder alertBuilder;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar1);

        drawer = findViewById(R.id.navdrawer_layout);
        alertBuilder = new AlertDialog.Builder(this);

        /*  Toolbar layout changes when switching between fragments and the main activity.
         *  So this listener is to reset the listeners for the toolbar options whenever the toolbar gets recreated.
         */
        toolbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                // set listeners for main toolbar options
                if ((mainNavButton = findViewById(R.id.mainNavButton)) != null) {

                    // main navigation drawer button
                    mainNavButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            drawer.openDrawer(GravityCompat.START);
                        }
                    });

                    // user profile button
                    userButton = findViewById(R.id.userAccountButton);
                    userButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openFragment(UserProfileFragment.newInstance(), "User Profile");
                        }
                    });
                }
                // set listeners for secondary toolbar options when any fragment is open
                else {

                    // back button for open fragments
                    backButton = findViewById(R.id.fragBackButton);
                    backButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });

                    // navigation drawer button for open fragments
                    fragNavButton = findViewById(R.id.fragNavButton);
                    fragNavButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            drawer.openDrawer(GravityCompat.START);
                        }
                    });
                }
            }
        });

        // setting navigation view options listeners
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); // uses onNavigationItemSelected(MenuItem) overridden function

        // enabling bottom tab navigation
        tabLayout = findViewById(R.id.bottom_nav_bar);
        viewPager = findViewById(R.id.viewPager);
        int count = tabLayout.getTabCount();

        final PageAdapter adapter = new PageAdapter(this, count, true);
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

        // handling logout
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                logoutButton = findViewById(R.id.logoutButton);
                logoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertBuilder
                                .setMessage(R.string.logout_dialog_message)
                                .setTitle(R.string.logout_dialog_title)
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        logout();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    }
                });
                resetPasswordButton = findViewById(R.id.resetPassButton);
                resetPasswordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFragment(WebPageFragment.newInstance(getString(R.string.reset_password_url)), "Reset Password");
                        drawer.closeDrawer(GravityCompat.START);
                    }
                });
            }
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {}
            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    } // end of onCreate

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else if (getSupportFragmentManager().getBackStackEntryCount() == 1) { // backstack reached base (main activity)
            // changing the toolbar back to original layout without back button
            super.onBackPressed();
            getSupportActionBar().setCustomView(R.layout.toolbar1);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String itemTag = (String)item.getTitle();
        switch (item.getItemId()) {
            case R.id.nav_timetable:
                openFragment(SearchListFragment.newInstance("Timetables", "File", ""), itemTag);
                break;
            case R.id.nav_academic_cal:
                openFragment(WebPageFragment.newInstance(getString(R.string.academic_calendar_url)), itemTag);
                break;
            case R.id.nav_forms:
                openFragment(SearchListFragment.newInstance("Forms", "Form", "Department"), itemTag);
                break;
            case R.id.nav_policies:
                openFragment(SearchListFragment.newInstance("Policies", "Policy", "Department"), itemTag);
                break;
            case R.id.nav_degreeplanner:
                openFragment(SearchListFragment.newInstance("Degree Planners", "Session", "Faculty"), itemTag);
                break;
            case R.id.nav_staffdirectory:
                openFragment(new SearchListFragment("Staff Directory"), itemTag);
                break;
            case R.id.nav_subjectsoffered:
                openFragment(SearchListFragment.newInstance("Timetables", "File", ""), itemTag);
                break;
            case R.id.nav_feedback:
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.feedback_url))); // to open the URL in a browser
                startActivity(viewIntent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFragment(Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int currentFragmentIndex = fragmentManager.getBackStackEntryCount() - 1;
        if (currentFragmentIndex >= 0) { // at least one fragment is visible
            String currentFragmentTag = fragmentManager.getBackStackEntryAt(currentFragmentIndex).getName();
            if (fragmentTag.equals(currentFragmentTag))
                return;
        }
        Fragment f = fragmentManager.findFragmentByTag(fragmentTag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right); // adding animations using userTransaction object
        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.add(R.id.fragmentContainer, fragment, fragmentTag).commit();

        // changing toolbar layout to contain back button and navigation drawer button
        getSupportActionBar().setCustomView(R.layout.toolbar2);
    }

    public void logout() {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        sp.edit().putBoolean("logged", false).apply();
        Intent intent = new Intent(this, MainActivityPreLogin.class);
        startActivity(intent);
        finish();
    }
}
