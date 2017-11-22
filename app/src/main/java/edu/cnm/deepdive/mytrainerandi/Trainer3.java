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
import android.widget.Switch;
import android.widget.Toast;
import com.j256.ormlite.stmt.DeleteBuilder;
import edu.cnm.deepdive.mytrainerandi.adapters.Trainer3ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.util.List;

public class Trainer3 extends Fragment implements OnClickListener {

  private OrmHelper helper;
  private ListView exerciseListView;

  private Button btnLower;
  private Button btnUpper;
  private Button btnCardio;
  private Button btnAbs;


  private RadioButton rdMon;
  private RadioButton rdTue;
  private RadioButton rdWed;
  private RadioButton rdThur;
  private RadioButton rdFri;
  private RadioButton rdSat;
  private RadioButton rdSun;
  private RadioGroup radiogroup;

  /**
   * Inflate trainer3 view to display screen for use by trainer. Allows trainer to pick exercises
   * for client.
   */

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

//need parans for casting for this ORM interaction. Listen to recording.
    helper = ((OrmInteraction) getActivity()).getHelper();

    View trainerView = inflater.inflate(R.layout.trainer3, container, false);

    //Radio Button generates an Event Source-state change.
    btnAbs = trainerView.findViewById(R.id.radioabs);
    btnCardio = trainerView.findViewById(R.id.radiocardio);
    btnLower = trainerView.findViewById(R.id.radiolower);
    btnUpper = trainerView.findViewById(R.id.radioupper);

    btnAbs.setOnClickListener(this);
    btnCardio.setOnClickListener(this);
    btnLower.setOnClickListener(this);
    btnUpper.setOnClickListener(this);

    radiogroup = trainerView.findViewById(R.id.radiotrainergroup);
    exerciseListView = trainerView.findViewById(R.id.listViewTrainer3);

    //Add Save button Listener, creating view object in memory.
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

  //Pop up with which button was picked using Toast.
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(getActivity(), msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  private void refresh(String circuit) {
    List<Exercise> allexercise = null;
    try {
      allexercise = helper
          .getExerciseDao().queryForEq(CIRCUIT_COLNAME, circuit);
    } catch (SQLException e) {
      e.printStackTrace();
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

    //First checking if save button was pressed; looping through exercises rows to determine if box was checked.
    //loop through list items
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

            //Exercise is Trainers entity
            ExerciseByDay newtrainerpick = new ExerciseByDay();

            newtrainerpick.setSets(sets);
            newtrainerpick.setReps(reps);

            //set day of week..??date

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
            e.printStackTrace();

          }
        }
      }
    }

    if (view.getId() == R.id.btnResetTrainer) {

      int dayofweek = 7;

      switch (radiogroup.getCheckedRadioButtonId()) {
        case R.id.radioSun:
          dayofweek = 0;
          break;
        case R.id.radioMon:
          dayofweek = 1;
          break;
        case R.id.radioTue:
          dayofweek = 2;
          break;
        case R.id.radioWed:
          dayofweek = 3;
          break;
        case R.id.radioThu:
          dayofweek = 4;
          break;
        case R.id.radioFri:
          dayofweek = 5;
          break;
        case R.id.radioSat:
          dayofweek = 6;
          break;

      }

      AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
      alertDialog.setTitle("Reset: ");
      alertDialog.setMessage("Remove current day of workouts?");
      // Alert dialog button
      final int finalDayofweek = dayofweek;
      alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

              //delete elements from table in field by arg
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





