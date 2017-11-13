package edu.cnm.deepdive.mytrainerandi.adapters;

import static edu.cnm.deepdive.mytrainerandi.entity.DaySchedule.EXERCISE_CONSTANT;
import static edu.cnm.deepdive.mytrainerandi.entity.DaySchedule.MUSCLE_CONSTANT;
import static edu.cnm.deepdive.mytrainerandi.entity.DaySchedule.REPS_CONSTANT;
import static edu.cnm.deepdive.mytrainerandi.entity.DaySchedule.SETS_CONSTANT;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OrmLiteCursorAdapter;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.DaySchedule;

public class ScheduleDay2ListAdapter extends OrmLiteCursorAdapter<DaySchedule, View > {

  public ScheduleDay2ListAdapter(Context context) {
    super(context);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
    LayoutInflater inflater = LayoutInflater.from(context);

    return inflater.inflate(R.layout.schedule_item, viewGroup, false);
  }

  @Override
  public void bindView(View view, Context context, DaySchedule item) {
    TextView smuscle = view.findViewById(R.id.schedule_muscle);
    TextView sexercise = view.findViewById(R.id.schedule_exercise);
    TextView sreps = view.findViewById(R.id.schedule_reps);
    TextView ssets = view.findViewById(R.id.schedule_sets);

    smuscle.setText(item.getMuscle());
    sexercise.setText(item.getExcercise());
    sreps.setText(item.getReps());
    ssets.setText(item.getSet());
  }

}
