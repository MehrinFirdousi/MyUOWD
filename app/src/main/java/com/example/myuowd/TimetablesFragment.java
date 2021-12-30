package com.example.myuowd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimetablesFragment extends Fragment {

    TextView title;
    ListView listView;

    public TimetablesFragment() {
        // Required empty public constructor
    }

    public static TimetablesFragment newInstance() {
        TimetablesFragment fragment = new TimetablesFragment();
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
        title.setText(R.string.timetablesTag);
        listView = getView().findViewById(R.id.navOptionList);
        populateListView(listView);
        super.onViewCreated(view, savedInstanceState);
    }

    public void populateListView(ListView listView) {

        String[] fileNames = {"File1", "File2", "File3", "File4", "File5", "File6", "File7", "File8"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.list_pdf_item, R.id.listItemTitle, fileNames);

        // attaching the adapter to the listview
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "list item " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}