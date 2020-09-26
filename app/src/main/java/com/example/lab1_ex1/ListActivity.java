package com.example.lab1_ex1;

import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends ListFragment {

    ListView list;

    private String[] activities;
    static final Class[] classArray = new Class[] { AIActivity.class, VRActivity.class };

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container, false);
        activities = getResources().getStringArray(R.array.list_activities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, activities);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(getActivity(), classArray[position]));
    }

}
