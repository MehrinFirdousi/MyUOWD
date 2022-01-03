package com.example.myuowd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        TextView studentName = view.findViewById(R.id.studentName);
        TextView studentId = view.findViewById(R.id.studentId);
        TextView courseLevel = view.findViewById(R.id.courseLevel);
        TextView courseName = view.findViewById(R.id.courseName);
        ImageView studentBarcode = view.findViewById(R.id.studentBarcode);

        studentName.setText(R.string.studentName);
        studentId.setText(R.string.studentId);
        courseLevel.setText(R.string.courseLevel);
        courseName.setText(R.string.courseName);
        studentBarcode.setImageResource(R.drawable.barcode);

        return view;
    }
}