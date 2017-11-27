package edu.cnm.deepdive.mytrainerandi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import java.util.List;


/**
 * Trainer3 adapter to prepare view of rows for the days scheduled exercises. Adapter object acts as
 * a bridge between an AdapterView and the underlying data for that view.
 */
public class Trainer3ListAdapter extends ArrayAdapter<Exercise> {

  /**
   * Convert the array of the master list of exercise into scrollable view for selection by
   * trainer.
   */
  public Trainer3ListAdapter(Context context, int resource,
      List<Exercise> objects) {
    super(context, resource, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());

    View view = inflater.inflate(R.layout.trainer3_listview, parent, false);

    /**   Interface text elements for circuit, muscle and exercise. */
    TextView tcircuit = view.findViewById(R.id.trainer_circuit);
    TextView tmuscle = view.findViewById(R.id.trainer_muscle);
    TextView texercise = view.findViewById(R.id.trainer_exercise);

    Exercise item = getItem(position);
    /** sets and returns view with circuit, muscle and exercisename. */
    tcircuit.setText(item.getCircuit());
    tmuscle.setText(item.getMuscle());
    texercise.setText(item.getExercisename());

    return view;
  }

}
