package edu.cnm.deepdive.mytrainerandi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import java.util.List;

public class ScheduleDay2ListAdapter extends ArrayAdapter<ExerciseByDay> {


  public ScheduleDay2ListAdapter(Context context, int resource,
      List<ExerciseByDay> objects) {
    super(context, resource, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());

    View view = inflater.inflate(R.layout.schedule_item, parent, false);

    TextView smuscle = view.findViewById(R.id.schedule_muscle);
    TextView sexercise = view.findViewById(R.id.schedule_exercise);
    TextView sreps = view.findViewById(R.id.edit_reps);
    TextView ssets = view.findViewById(R.id.edit_sets);
    TextView lbs = view.findViewById(R.id.edit_lbs);

    ExerciseByDay item = getItem(position);
    smuscle.setText(item.getMuscle());
    sexercise.setText(item.getExercise());
    sreps.setText(item.getReps());
    ssets.setText(item.getSet());
    return view;
  }

}
