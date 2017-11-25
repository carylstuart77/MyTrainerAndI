package edu.cnm.deepdive.mytrainerandi;

import static edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay.DAYOFWEEK;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import edu.cnm.deepdive.mytrainerandi.adapters.ScheduleDay2ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * This fragment displays the selected days exercises assigned by the Trainer.
 *
 * It is within this screen that the datepicker is used to select the days workout.
 */

public class Day2 extends Fragment implements OnClickListener {

  Button btn;
  int year_x, month_x, day_x;

  /**
   * Constant value of DIALOG_ID
   */
  static final int DIALOG_ID = 0;

  private View inflate;
  private OrmHelper helper;
  /**
   * Saves the content of the bundle received from the workout list
   */
  private ListView workoutListView;

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
    Log.d("In Day2:", "In Day2: OnCreateView");

    //LayoutInflater is one of the Android System Services that is responsible
    // for taking your XML files that define a layout, and converting them into View objects.

    inflate = inflater.inflate(R.layout.day2, container, false);

    showDialogOnButtonClick();

    //Add Save button Listener, creating view object in memory.
    Button savebutton = inflate.findViewById(R.id.btnDay2Save);
    savebutton.setOnClickListener(this);

    helper = ((OrmHelper.OrmInteraction) getActivity()).getHelper();
    return inflate;
  }

  /**
   * Run calendar dialog and call listener to determine the day of week after selection.
   */

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
    getActivity().setTitle(R.string.motivation_message);
  }

  /**
   * Determine which day of the week was picked. Displays message reminding user which day they had
   * picked.
   */

  private DatePickerDialog.OnDateSetListener dpickerListner
      = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      year_x = year;
      month_x = monthOfYear;
      day_x = dayOfMonth;
      //Toast.makeText(getActivity(),year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();/

      Calendar calendar = Calendar.getInstance();
      calendar.set(year, monthOfYear, dayOfMonth);
      int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

      if (dayofweek == 0) {
        showTextNotification("Picked Sunday");
      }
      if (dayofweek == 1) {
        showTextNotification("Picked Monday");
      }
      if (dayofweek == 2) {
        showTextNotification("Picked Tuesday");
      }
      if (dayofweek == 3) {
        showTextNotification("Picked Wednesday");
      }
      if (dayofweek == 4) {
        showTextNotification("Picked Thursday");
      }
      if (dayofweek == 5) {
        showTextNotification("Picked Friday");
      }
      if (dayofweek == 6) {
        showTextNotification("Picked Saturday");
      }
      //Toast.makeText(getActivity(), "Day of week " + dayofweek, Toast.LENGTH_LONG).show();

      refresh(dayofweek);
    }
  };


  /**
   * Message notifications called on by onClick method.
   */
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(getActivity(), msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  /**
   * Database is queried for the day of week exercises and the rows are displayed in a list.
   */
  private void refresh(int dayofweek) {
    List<ExerciseByDay> dayschedule = null;
    try {
      dayschedule = helper
          .getDayscheduleDao().queryForEq(DAYOFWEEK, dayofweek);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // Adapter takes what is in DB and prepares data for display.
    ScheduleDay2ListAdapter workoutAdapter = new ScheduleDay2ListAdapter(getActivity(),
        R.layout.schedule_item, dayschedule);
    //From day2.xml list
    workoutListView = (ListView) inflate.findViewById(R.id.listViewDay);
    //Display data in schedule_item rows
    workoutListView.setAdapter(workoutAdapter);
  }


  @Override
  public void onClick(View view) {

    Log.i("OnClick Save Button", "BEGIN in Save button!");

    /** Returns the number of children in the group in order to update reps, sets and lbs. */
    for (int i = 0; i < workoutListView.getChildCount(); i++) {
      ExerciseByDay workoutbyday = (ExerciseByDay) workoutListView.getAdapter().getItem(i);
      //getChildAt Returns the view at the specified position in the group.
      workoutbyday.setReps(Integer.parseInt(
          ((EditText) workoutListView.getChildAt(i).findViewById(R.id.edit_reps)).getText()
              .toString()));
      workoutbyday.setSets(Integer.parseInt(
          ((EditText) workoutListView.getChildAt(i).findViewById(R.id.edit_sets)).getText()
              .toString()));
      workoutbyday.setLbs(Integer.parseInt(
          ((EditText) workoutListView.getChildAt(i).findViewById(R.id.edit_lbs)).getText()
              .toString()));

      try {
        helper.getDayscheduleDao().update(workoutbyday);
        showTextNotification("SAVED IT!");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }
  }
}
