package edu.cnm.deepdive.mytrainerandi;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.PreparedQuery;
import edu.cnm.deepdive.mytrainerandi.adapters.ScheduleDay2ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.Exercises;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import java.sql.SQLException;
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

    getHelper();

    List<Exercises> exercises = null;
    try {
      exercises = helper
          .getExerciseDao().queryForAll();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    PreparedQuery query = null;
    try {
      query = helper.getDayscheduleDao().queryBuilder().prepare();
    } catch (SQLException e) {
      e.printStackTrace();
    }

//Display exercises.
    ScheduleDay2ListAdapter workoutAdapter = new ScheduleDay2ListAdapter(getActivity());
    ListView workoutListView = (ListView) inflate.findViewById(R.id.listViewDay);
    workoutAdapter.setPreparedQuery(query);
    workoutListView.setAdapter(workoutAdapter);

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
      month_x = monthOfYear + 1;
      day_x = dayOfMonth;
      Toast.makeText(getActivity(),year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();

    }
  };

  //creates an instance of the ormhelper
  public synchronized OrmHelper getHelper() {
    if (helper == null) {
      helper = OpenHelperManager.getHelper(getActivity(), OrmHelper.class);
    }
    return helper;
  }

  //Try to prevent memory leaks by setting helper to null when not in use.
  public synchronized void releaseHelper() {
    if (helper != null) {
      OpenHelperManager.releaseHelper();
      helper = null;
    }
  }
//as view is dissapearing
  @Override
  public void onStop() {
    releaseHelper();
    super.onStop();
  }
}
