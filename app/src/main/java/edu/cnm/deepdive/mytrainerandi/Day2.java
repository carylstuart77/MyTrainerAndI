package edu.cnm.deepdive.mytrainerandi;

import static edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay.DAYOFWEEK;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.mytrainerandi.adapters.ScheduleDay2ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;

public class Day2 extends Fragment {

  Button btn;
  int year_x, month_x, day_x;
  static final int DIALOG_ID = 0;
  private View inflate;
  private OrmHelper helper;

  {
    //Setup for current year, month and day.
    final Calendar cal = Calendar.getInstance();
    year_x = cal.get(Calendar.YEAR);
    month_x = cal.get(Calendar.MONTH);
    day_x = cal.get(Calendar.DAY_OF_MONTH);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    //LayoutInflater is one of the Android System Services that is responsible
    // for taking your XML files that define a layout, and converting them into View objects.

    inflate = inflater.inflate(R.layout.day2, container, false);

    showDialogOnButtonClick();

    helper = ((OrmHelper.OrmInteraction) getActivity()).getHelper();
    return inflate;
  }

  public void showDialogOnButtonClick() {
    btn = (Button) inflate.findViewById(R.id.dateButton);

    btn.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View v) {
            DatePickerDialog datePickerDiaolog = new DatePickerDialog(getActivity(), dpickerListner,
                year_x, month_x, day_x);
            datePickerDiaolog.show();

          }
        }
    );
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle("Day's Schedule");
  }

  private DatePickerDialog.OnDateSetListener dpickerListner
      = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      year_x = year;
      month_x = monthOfYear + 1;  //+ 1 sets default to currentyear
      day_x = dayOfMonth;
      Toast.makeText(getActivity(),year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();

      Calendar calendar = Calendar.getInstance();
      calendar.set(year, monthOfYear, dayOfMonth);
      int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
      refresh(dayofweek);

    }
  };

  private void refresh(int dayofweek) {
    List<ExerciseByDay> dayschedule = null;
    try {
      dayschedule = helper
          .getDayscheduleDao().queryForEq(DAYOFWEEK, dayofweek);
    } catch (SQLException e) {
      e.printStackTrace();
    }

//Display exercises.
    ScheduleDay2ListAdapter workoutAdapter = new ScheduleDay2ListAdapter(getActivity(), R.layout.schedule_item, dayschedule);
    ListView workoutListView = (ListView) inflate.findViewById(R.id.listViewDay);
    workoutListView.setAdapter(workoutAdapter);

  }

}
