package edu.cnm.deepdive.mytrainerandi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import java.util.ArrayList;
import java.util.List;

public class Trainer3ListAdapter extends ArrayAdapter<Exercise> {


  //hold onto list of exercises displayed and new set/reps.
//  private List<View> listViews = new ArrayList<>();


  public Trainer3ListAdapter(Context context, int resource,
      List<Exercise> objects) {
    super(context, resource, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());

    View view = inflater.inflate(R.layout.trainer3_listview, parent, false);


    //this is from a data source.
    TextView tcircuit = view.findViewById(R.id.trainer_circuit);
    TextView tmuscle = view.findViewById(R.id.trainer_muscle);
    TextView texercise = view.findViewById(R.id.trainer_exercise);

//?how does this work
    Exercise item = getItem(position);

    tcircuit.setText(item.getCircuit());
    tmuscle.setText(item.getMuscle());
    texercise.setText(item.getExercisename());


    return view;
  }

}
