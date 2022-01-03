package com.example.myuowd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

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
        for (int id : buttonIds)
            view.findViewById(id).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (v.getId()) {
            case R.id.solsButton:
                openWebsite(R.string.sols_url);
                break;
            case R.id.emailButton:
                openWebsite(R.string.email_url);
                break;
            case R.id.moodleButton:
                openWebsite(R.string.moodle_url);
                break;
            case R.id.academicCalendarButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.academic_calendar_url)), tag);
                break;
            case R.id.staffDirectoryButton:
                ((MainActivity)getActivity()).openFragment(new SearchListFragment("Staff Directory"), tag);
                break;
            case R.id.formsButton:
                ((MainActivity)getActivity()).openFragment(SearchListFragment.newInstance("Forms", "Form", "Department"), tag);
                break;
            case R.id.policiesButton:
                ((MainActivity)getActivity()).openFragment(SearchListFragment.newInstance("Policies", "Policy", "Department"), tag);
                break;
            case R.id.degreePlannerButton:
                ((MainActivity)getActivity()).openFragment(SearchListFragment.newInstance("Degree Planners", "Session", "Faculty"), tag);
                break;
            case R.id.timetablesButton:
                ((MainActivity)getActivity()).openFragment(SearchListFragment.newInstance("Timetables", "File", ""), tag);
                break;
            case R.id.libraryButton:
                ((MainActivity)getActivity()).openFragment(LibraryFragment.newInstance(), tag);
                break;
            case R.id.graduationButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.graduation_url)), tag);
                break;
            case R.id.scholarshipButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.scholarship_url)), tag);
                break;
            case R.id.careerDevButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.career_development_url)), tag);
                break;
            case R.id.transportationButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.transportation_url)), tag);
                break;
            case R.id.careersPortalButton:
                openWebsite(R.string.career_portal_url);
                break;
            case R.id.registrarsButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.registrars_url)), tag);
                break;
            case R.id.srcButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.src_url)), tag);
                break;
            case R.id.uowdxButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.uowdx_url)), tag);
                break;
            case R.id.residencesButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.residences_url)), tag);
                break;
            case R.id.studentLifeButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.student_life_url)), tag);
                break;
            case R.id.studentVisaButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.student_visa_url)), tag);
                break;
            case R.id.financeButton:
                ((MainActivity)getActivity()).openFragment(WebPageFragment.newInstance(getString(R.string.finance_url)), tag);
                break;
            case R.id.onlineFormsButton:
                ((MainActivity)getActivity()).openFragment(SearchListFragment.newInstance("Online Forms", "Form", ""), tag);
                break;
            case R.id.studiosityButton:
                openWebsite(R.string.studiosity_url);
                break;
            case R.id.onlineBookStoreButton:
                openWebsite(R.string.online_book_store_url);
                break;
        }
    }

    // switches from the app to a browser and opens the URL
    public void openWebsite(int websiteUrlId) {
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(websiteUrlId)));
        startActivity(viewIntent);
    }
}