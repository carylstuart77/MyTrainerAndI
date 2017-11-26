package edu.cnm.deepdive.mytrainerandi;

import static edu.cnm.deepdive.mytrainerandi.entity.Exercise.CIRCUIT_COLNAME;
import static edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay.DAYOFWEEK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.j256.ormlite.stmt.DeleteBuilder;
import edu.cnm.deepdive.mytrainerandi.adapters.Trainer3ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.util.List;

/**
 * This fragment provides the trainer with a list of all exercises available in the database.  These
 * choices are categorized by groupings of upper, lower, cardio and core.  Once a group is selected
 * each exercise is available for selection and can be assigned to a day.
 */

public class Trainer3 extends Fragment implements OnClickListener {

  /**
   * Stores the value of orm helper to be used by this fragments queries.
   */
  private OrmHelper helper;
  /**
   * Stores the values exercise view.
   */
  private ListView exerciseListView;
  /**
   * Button selection for each exercise circuit group.
   */
  private Button btnLower;
  private Button btnUpper;
  private Button btnCardio;
  private Button btnCore;

  /**
   * Monday through Sunday Radio Buttons to be displayed.
   */
  private RadioButton rdMon;
  private RadioButton rdTue;
  private RadioButton rdWed;
  private RadioButton rdThur;
  private RadioButton rdFri;
  private RadioButton rdSat;
  private RadioButton rdSun;

  /**Group radio buttons so only one can be chosen at a time.*/
  private RadioGroup radiogroup;

  /**
   * Inflate trainer3 view to display screen for use by trainer. Allows trainer to pick exercises
   * for client by day of week.
   */

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    //Cast activity to helper for interaction. Listen to recording with Chris.
    helper = ((OrmInteraction) getActivity()).getHelper();

    View trainerView = inflater.inflate(R.layout.trainer3, container, false);

    //Radio Button generates an Event Source-state change.
    btnCore = trainerView.findViewById(R.id.radioabs);
    btnCardio = trainerView.findViewById(R.id.radiocardio);
    btnLower = trainerView.findViewById(R.id.radiolower);
    btnUpper = trainerView.findViewById(R.id.radioupper);

    btnCore.setOnClickListener(this);
    btnCardio.setOnClickListener(this);
    btnLower.setOnClickListener(this);
    btnUpper.setOnClickListener(this);

    radiogroup = trainerView.findViewById(R.id.radiotrainergroup);
    exerciseListView = trainerView.findViewById(R.id.listViewTrainer3);

    /**
     * Save will allow selection to be saved to db.  Reset will remove exercises to be removed from db.
     * Provide listeners for save and reset buttons to listen of an On click event.
     */
    Button savebutton = trainerView.findViewById(R.id.btnSaveTrainer);
    savebutton.setOnClickListener(this);

    Button resetbutton = trainerView.findViewById(R.id.btnResetTrainer);
    resetbutton.setOnClickListener(this);

    return trainerView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.trainer_plan);
  }

  /**
   * Message notifications called on by onClick method.
   */
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(getActivity(), msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  /**
   * Queries for circuit exercises and stores them in List<Exercise>. Throws runtime SQL exception
   * upon catch condition.
   */
  private void refresh(String circuit) {
    List<Exercise> allexercise = null;
    try {
      allexercise = helper
          .getExerciseDao().queryForEq(CIRCUIT_COLNAME, circuit);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    //Display exercises.
    Trainer3ListAdapter trainer3Adapter = new Trainer3ListAdapter(getActivity(),
        R.layout.trainer3_listview, allexercise);
    exerciseListView.setAdapter(trainer3Adapter);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.radioabs) {
      showTextNotification("Core");
      refresh("core");
    }
    if (view.getId() == R.id.radiocardio) {
      showTextNotification("Cardio");
      refresh("cardio");
    }
    if (view.getId() == R.id.radiolower) {
      showTextNotification("Lower");
      refresh("lower");
    }
    if (view.getId() == R.id.radioupper) {
      showTextNotification("Upper");
      refresh("upper");
    }

    /**
     * Checks if save button was selected then loops through rows to determine if checkbox was checked
     * If sets and reps were entered: trim, parase and write them to the database.
     * Throw runtime exception if SQL exception is caught.
     */
    if (view.getId() == R.id.btnSaveTrainer) {
      for (int i = 0; i < exerciseListView.getChildCount(); i++) {
        if (((CheckBox) exerciseListView.getChildAt(i).findViewById(R.id.edit_trainerpick))
            .isChecked()) {
          EditText mTrainerSets = exerciseListView.getChildAt(i)
              .findViewById(R.id.edit_trainersets);
          EditText mTrainerReps = exerciseListView.getChildAt(i)
              .findViewById(R.id.edit_trainerreps);

          String capturesets = mTrainerSets.getText().toString();
          String capturereps = mTrainerReps.getText().toString();

          try {

            int sets = 0;
            int reps = 0;

            if (!capturesets.trim().isEmpty()) {
              sets = Integer.parseInt(capturesets);
            }
            if (!capturereps.trim().isEmpty()) {
              reps = Integer.parseInt(capturereps);
            }

            ExerciseByDay newtrainerpick = new ExerciseByDay();

            newtrainerpick.setSets(sets);
            newtrainerpick.setReps(reps);

            //Write the day of week that was picked through the radio buttons.

            switch (radiogroup.getCheckedRadioButtonId()) {
              case R.id.radioSun:
                newtrainerpick.setDayofweek(0);
                break;
              case R.id.radioMon:
                newtrainerpick.setDayofweek(1);
                break;
              case R.id.radioTue:
                newtrainerpick.setDayofweek(2);
                break;
              case R.id.radioWed:
                newtrainerpick.setDayofweek(3);
                break;
              case R.id.radioThu:
                newtrainerpick.setDayofweek(4);
                break;
              case R.id.radioFri:
                newtrainerpick.setDayofweek(5);
                break;
              case R.id.radioSat:
                newtrainerpick.setDayofweek(6);
                break;

            }

            Exercise item = (Exercise) exerciseListView.getAdapter().getItem(i);
            newtrainerpick.setExercise(item);

            helper.getExerciseByDayDao().create(newtrainerpick);

            showTextNotification("SAVED IT!");

            //adapter already selected it shows up as selected.
            //helper.getExerciseByDayDao().update(newtrainerpick);

          } catch (SQLException e) {
            throw new RuntimeException(e);

          }
        }
      }
    }

    /**
     * The reset button provides the trainer with the option of removing previously day of week
     * exercises.
     */

    if (view.getId() == R.id.btnResetTrainer) {

      int dayofweek = 7;
      String aday = null;

      switch (radiogroup.getCheckedRadioButtonId()) {
        case R.id.radioSun:
          dayofweek = 0;
          aday = "Sunday";
          break;
        case R.id.radioMon:
          dayofweek = 1;
          aday = "Monday";
          break;
        case R.id.radioTue:
          dayofweek = 2;
          aday = "Tuesday";
          break;
        case R.id.radioWed:
          dayofweek = 3;
          aday = "Wednesday";
          break;
        case R.id.radioThu:
          dayofweek = 4;
          aday = "Thursday";
          break;
        case R.id.radioFri:
          dayofweek = 5;
          aday = "Friday";
          break;
        case R.id.radioSat:
          dayofweek = 6;
          aday = "Saturday";
          break;

      }

      /**
       * Alert trainer that resetting the day of week will remove those exercises and provides
       * an option to continue or to cancel.
       */
      AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
      alertDialog.setTitle("Reset: ");
      alertDialog.setMessage("Reset " + aday + " workouts?");
      // Alert dialog button
      final int finalDayofweek = dayofweek;
      alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

              /** Deletes elements from table by argument.
               * Throw runtime exception if SQL exception is caught.
               */
              DeleteBuilder<ExerciseByDay, Integer> deleteBuilder = null;
              try {
                deleteBuilder = helper.getDayscheduleDao().deleteBuilder();
                deleteBuilder.where().eq(DAYOFWEEK, finalDayofweek);
                deleteBuilder.delete();
              } catch (SQLException e) {
                throw new RuntimeException(e);
              }
              dialog.dismiss();// use dismiss to cancel alert dialog
            }
          });
      alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
          new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              //Left blank on purpose.
            }
          });
      alertDialog.show();
    }
  }
}





