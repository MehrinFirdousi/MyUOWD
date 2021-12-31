package com.example.myuowd;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {

    int totalTabs;
    boolean isLoggedIn;

    public PageAdapter(FragmentActivity fm, int totalTabs, boolean isLoggedIn)
    {
        super(fm);
        this.totalTabs = totalTabs;
        this.isLoggedIn = isLoggedIn;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0:
                if (isLoggedIn)
                    return HomeFragment.newInstance();
                else
                    return HomeFragmentPreLogin.newInstance();
            case 1:
                return NewsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
