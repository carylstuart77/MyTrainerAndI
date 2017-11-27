package edu.cnm.deepdive.mytrainerandi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import java.util.List;

/**
 * Day 2 adapter to prepare view of rows for the days scheduled exercises. Adapter object acts as a
 * bridge between an AdapterView and the underlying data for that view.
 */

public class ScheduleDay2ListAdapter extends ArrayAdapter<ExerciseByDay> {

  /**
   * Convert the array of exercise for the day into scrollable view.
   */
  public ScheduleDay2ListAdapter(Context context, int resource,
      List<ExerciseByDay> objects) {
    super(context, resource, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());

    View view = inflater.inflate(R.layout.schedule_item, parent, false);

    /**   Interface text elements for muscle, exercise, sets, reps and lbs. */
    TextView smuscle = view.findViewById(R.id.schedule_muscle);
    TextView sexercise = view.findViewById(R.id.schedule_exercise);
    TextView ssets = view.findViewById(R.id.edit_sets);
    TextView sreps = view.findViewById(R.id.edit_reps);
    TextView slbs = view.findViewById(R.id.edit_lbs);

    ExerciseByDay item = getItem(position);
    /** sets and returns view with muscle exercise, sets, reps and lbs. */
    smuscle.setText(item.getExercise().getMuscle());
    //pulling from object no longer a string
    sexercise.setText(item.getExercise().getExercisename());
    ssets.setText(Integer.toString(item.getSets()));
    sreps.setText(Integer.toString(item.getReps()));
    slbs.setText(Integer.toString(item.getLbs()));

    return view;
  }

}
