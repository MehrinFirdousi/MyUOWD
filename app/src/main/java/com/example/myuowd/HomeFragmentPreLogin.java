package com.example.myuowd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeFragmentPreLogin extends Fragment implements View.OnClickListener {

    int[] buttonIds = { R.id.libraryButton, R.id.scholarshipButton, R.id.transportationButton, R.id.srcButton, R.id.residencesButton, R.id.socialMediaButton, R.id.eventsCalendarButton, R.id.onlineFormsButton};
    public HomeFragmentPreLogin() {
        // Required empty public constructor
    }

    public static HomeFragmentPreLogin newInstance() {
        HomeFragmentPreLogin fragment = new HomeFragmentPreLogin();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_prelogin, container, false);
        for (int id : buttonIds)
            view.findViewById(id).setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (v.getId()) {
            case R.id.libraryButton:
                ((MainActivityPreLogin)getActivity()).openFragment(LibraryFragment.newInstance(), tag);
                break;
            case R.id.scholarshipButton:
                ((MainActivityPreLogin)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.scholarship_url_public)), tag);
                break;
            case R.id.transportationButton:
                ((MainActivityPreLogin)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.transportation_url_public)), tag);
                break;
            case R.id.srcButton:
                ((MainActivityPreLogin)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.src_url)), tag);
                break;
            case R.id.residencesButton:
                ((MainActivityPreLogin)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.residences_url)), tag);
                break;
            case R.id.onlineFormsButton:
                ((MainActivityPreLogin)getActivity()).openFragment(SearchListFragment.newInstance("Online Forms", "Form", ""), tag);
                break;
        }
    }
}