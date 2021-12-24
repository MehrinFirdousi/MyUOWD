package com.example.myuowd;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {

    int totalTabs;

    public PageAdapter(FragmentActivity fm, int totalTabs)
    {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                NewsFragment newsFragment = new NewsFragment();
                //ScrollingFragment newsFragment = new ScrollingFragment();
                return newsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
