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

public class SearchListFragment extends Fragment {

    TextView title;
    ListView listView;
    String listTitle, listItemTitle, listItemSubtitle;

    public SearchListFragment(String listTitle) { this.listTitle = listTitle; }
    public SearchListFragment(String listTitle, String listItemTitle, String listItemSubtitle) {
        this.listTitle = listTitle;
        this.listItemTitle = listItemTitle;
        this.listItemSubtitle = listItemSubtitle;
    }

    public static SearchListFragment newInstance(String listTitle, String listItemTitle, String listItemSubtitle) {
        SearchListFragment fragment = new SearchListFragment(listTitle, listItemTitle, listItemSubtitle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        title = view.findViewById(R.id.fragmentTitle);
        title.setText(this.listTitle);
        listView = view.findViewById(R.id.navOptionList);
        if (listItemTitle != null)
            populateListView(listView);
        return view;
    }

    public void populateListView(ListView listView) {

        ArrayList<ListItemFile> fileList = new ArrayList<ListItemFile>(15);
        ListItemFileAdapter listAdapter = new ListItemFileAdapter(getActivity(), fileList);

        // only for testing the layout with dummy file names
        ListItemFile file;
        if (listItemSubtitle == "") // list item has no subtitle
            for (int i = 1; i <= 15; i++) {
                file = new ListItemFile(listItemTitle + i, listItemSubtitle);
                fileList.add(file);
            }
        else
            for (int i = 1; i <= 15; i++) {
                file = new ListItemFile(listItemTitle + i, listItemSubtitle + i);
                fileList.add(file);
            }

        // attaching the adapter to the listview
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "List item " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}