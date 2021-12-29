package com.example.myuowd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DegreePlannersFragment extends Fragment {

    TextView title;
    public DegreePlannersFragment() {
        // Required empty public constructor
    }

    public static DegreePlannersFragment newInstance() {
        DegreePlannersFragment fragment = new DegreePlannersFragment();
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
        return inflater.inflate(R.layout.fragment_nav_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        title = getView().findViewById(R.id.fragmentTitle);
        title.setText("Degree Planners");
        super.onViewCreated(view, savedInstanceState);
    }
}