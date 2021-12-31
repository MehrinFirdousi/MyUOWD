package com.example.myuowd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment implements View.OnClickListener {

    int[] buttonIds = {R.id.solsButton, R.id.emailButton, R.id.moodleButton, R.id.academicCalendarButton, R.id.busRoutesButton, R.id.staffDirectoryButton, R.id.formsButton, R.id.degreePlannerButton, R.id.myTimetableButton, R.id.policiesButton, R.id.campusMapButton, R.id.timetablesButton, R.id.libraryButton, R.id.graduationButton, R.id.scholarshipButton, R.id.careerDevButton, R.id.transportationButton, R.id.careersPortalButton, R.id.registrarsButton, R.id.srcButton, R.id.uowdxButton, R.id.residencesButton, R.id.studentLifeButton, R.id.studentVisaButton, R.id.financeButton, R.id.socialMediaButton, R.id.eventsCalendarButton, R.id.onlineFormsButton, R.id.studiosityButton, R.id.onlineBookStoreButton};
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        for (int i = 0; i < 30; i++)
            view.findViewById(buttonIds[i]).setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (v.getId()) {
            case R.id.academicCalendarButton:
                ((MainActivity)getActivity()).openFragment(TimetablesFragment.newInstance(), tag);
                break;
            case R.id.staffDirectoryButton:
                ((MainActivity)getActivity()).openFragment(StaffDirectoryFragment.newInstance(), tag);
                break;
            case R.id.formsButton:
                ((MainActivity)getActivity()).openFragment(FormsFragment.newInstance(), tag);
                break;
            case R.id.policiesButton:
                ((MainActivity)getActivity()).openFragment(PoliciesFragment.newInstance(), tag);
                break;
            case R.id.degreePlannerButton:
                ((MainActivity)getActivity()).openFragment(DegreePlannersFragment.newInstance(), tag);
                break;
            case R.id.timetablesButton:
                ((MainActivity)getActivity()).openFragment(TimetablesFragment.newInstance(), tag);
                break;
        }
    }
}