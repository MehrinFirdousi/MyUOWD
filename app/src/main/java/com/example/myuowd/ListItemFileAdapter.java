package com.example.myuowd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListItemFileAdapter extends ArrayAdapter<ListItemFile> {

    public ListItemFileAdapter(@NonNull Context context, @NonNull ArrayList<ListItemFile> files) {
        super(context, 0, files);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListItemFile file = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_pdf_item, parent, false);

        TextView titleView = convertView.findViewById(R.id.listItemTitle);
        TextView subtitleView = convertView.findViewById(R.id.listItemSub);

        titleView.setText(file.title);
        subtitleView.setText(file.subtitle);

        return convertView;
    }
}
