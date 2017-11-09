package edu.cnm.deepdive.mytrainerandi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Day2 extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    //LayoutInflater is one of the Android System Services that is responsible
    // for taking your XML files that define a layout, and converting them into View objects.

    View inflate = inflater.inflate(R.layout.day2, container, false);

    String[] exercises = {"Biceps", "Triceps", "Chest", "Back"};
    ListAdapter workoutAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
        exercises);
    ListView workoutListView = (ListView) inflate.findViewById(R.id.listViewDay);
    workoutListView.setAdapter(workoutAdapter);

    return inflate;

  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getActivity().setTitle("Day's Schedule");


  }



}
