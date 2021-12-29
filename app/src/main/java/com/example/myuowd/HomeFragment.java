package com.example.myuowd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class HomeFragment extends Fragment{

    private final View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (getView().getId()) {
                case R.id.academicCalendarButton:
                    Toast.makeText(getContext(), "academic calendar button clicked", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).openFragment(TimetablesFragment.newInstance());
                case R.id.staffDirectoryButton:
                    ((MainActivity)getActivity()).openFragment(StaffDirectoryFragment.newInstance());
            }
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.academicCalendarButton).setOnClickListener(buttonListener);
        view.findViewById(R.id.staffDirectoryButton).setOnClickListener(buttonListener);
        return view;
    }

    /*@Override
    public void onClick(View v) {
        switch (getView().getId()) {
            case R.id.academicCalendarButton:
                Toast.makeText(getActivity(), "academic calendar button clicked", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).openFragment(TimetablesFragment.newInstance());
            case R.id.staffDirectoryButton:
                ((MainActivity)getActivity()).openFragment(StaffDirectoryFragment.newInstance());
        }
    }*/
}