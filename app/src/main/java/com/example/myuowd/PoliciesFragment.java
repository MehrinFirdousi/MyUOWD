package com.example.myuowd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PoliciesFragment extends Fragment {

    TextView title;
    ListView listView;

    public PoliciesFragment() {
        // Required empty public constructor
    }

    public static PoliciesFragment newInstance() {
        PoliciesFragment fragment = new PoliciesFragment();
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
        title.setText(R.string.policiesTag);
        listView = getView().findViewById(R.id.navOptionList);
        populateListView(listView);
        super.onViewCreated(view, savedInstanceState);
    }

    public void populateListView(ListView listView) {

        ArrayList<ListItemFile> fileList = new ArrayList<ListItemFile>(15);
        ListItemFileAdapter listAdapter = new ListItemFileAdapter(getActivity(), fileList);

        // only for testing the layout with dummy file names
        String listTitle = "Policy", listSubtitle = "Dept";
        ListItemFile file;
        for (int i = 1; i <= 15; i++)
        {
            file = new ListItemFile(listTitle + i, listSubtitle + i);
            fileList.add(file);
        }

        // attaching the adapter to the listview
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "list item " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}