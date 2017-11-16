package edu.cnm.deepdive.mytrainerandi;

import static edu.cnm.deepdive.mytrainerandi.entity.Exercise.CIRCUIT_COLNAME;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import edu.cnm.deepdive.mytrainerandi.adapters.Trainer3ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.util.List;

public class Trainer3 extends Fragment implements OnCheckedChangeListener {

  private RadioButton radioAbs;
  private RadioButton radioCardio;
  private RadioButton radioLower;
  private RadioButton radioUpper;
  private OrmHelper helper;
  private ListView exerciseListView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

//need parans for casting for this ORM interaction. Listen to recording.
    helper = ((OrmInteraction)getActivity()).getHelper();


    View trainerView = inflater.inflate(R.layout.trainer3, container, false);

    //CheckBox generates an Event Source-state change.
    radioAbs = trainerView.findViewById(R.id.radioabs);
    radioCardio = trainerView.findViewById(R.id.radiocardio);
    radioLower = trainerView.findViewById(R.id.radiolower);
    radioUpper = trainerView.findViewById(R.id.radioupper);

    //Checkbox Event Listener is notified when an event occurs.
    radioAbs.setOnCheckedChangeListener(this);
    radioCardio.setOnCheckedChangeListener(this);
    radioLower.setOnCheckedChangeListener(this);
    radioUpper.setOnCheckedChangeListener(this);

    exerciseListView = trainerView.findViewById(R.id.listViewTrainer3);
    return trainerView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().setTitle(R.string.trainer_plan);
  }

  //CompoundButton: A button with two states, checked and unchecked. When the button is pressed or clicked,
  // the state changes automatically.

  @Override
  public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
    //swith on compoundButton
    //check if they check more than one--online they suggest to use radio button cuz you can group and only allow one.?
    //call refresh mehto

    if (isChecked) {
      if (compoundButton == radioAbs) {
        showTextNotification("Abs");
        refresh("upper");
      }
      if (compoundButton == radioCardio) {
        showTextNotification("Cardio");
        refresh("upper");
      }
      if (compoundButton == radioLower) {
        showTextNotification("Lower");
      }
      if (compoundButton == radioUpper) {
        showTextNotification("Upper");
      }

    }
  }

  //Pop up with which check box was picked using Toast.
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(getActivity(), msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  private void refresh(String circuit) {
    List<Exercise> allexercise = null;
    try {
      allexercise = helper
          .getExerciseDao().queryForEq(CIRCUIT_COLNAME, circuit);  //change to radio
    } catch (SQLException e) {
      e.printStackTrace();
    }

    //Display exercises.
    Trainer3ListAdapter trainer3Adapter = new Trainer3ListAdapter(getActivity(),
        R.layout.trainer3_listview, allexercise);
    exerciseListView.setAdapter(trainer3Adapter);
  }
}

