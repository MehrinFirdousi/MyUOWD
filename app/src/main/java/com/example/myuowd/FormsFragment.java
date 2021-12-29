package com.example.myuowd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FormsFragment extends Fragment {

    TextView title;
    public FormsFragment() {
        // Required empty public constructor
    }

    public static FormsFragment newInstance() {
        FormsFragment fragment = new FormsFragment();
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
        title.setText("Forms");
        super.onViewCreated(view, savedInstanceState);
    }
}