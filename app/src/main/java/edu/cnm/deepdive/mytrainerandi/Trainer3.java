package edu.cnm.deepdive.mytrainerandi;

import static com.j256.ormlite.android.apptools.OpenHelperManager.helper;
import static edu.cnm.deepdive.mytrainerandi.entity.Exercise.CIRCUIT_COLNAME;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Toast;
import edu.cnm.deepdive.mytrainerandi.adapters.Trainer3ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import java.sql.SQLException;
import java.util.List;

public class Trainer3 extends Fragment implements OnCheckedChangeListener {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View trainerView = inflater.inflate(R.layout.trainer3, container, false);

    //CheckBox generates an Event Source-state change.
    CheckBox cbAbs = trainerView.findViewById(R.id.cb_abs);
    CheckBox cbCardio = trainerView.findViewById(R.id.cb_cardio);
    CheckBox cbLower = trainerView.findViewById(R.id.cb_lower);
    CheckBox cbUpper = trainerView.findViewById(R.id.cb_upper);

    //Checkbox Event Listener is notified when an event occurs.
    cbAbs.setOnCheckedChangeListener(this);
    cbCardio.setOnCheckedChangeListener(this);
    cbLower.setOnCheckedChangeListener(this);
    cbUpper.setOnCheckedChangeListener(this);
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
      if (compoundButton == cbAbs) {
        showTextNotification("Abs");
      }
      if (compoundButton == cbCardio) {
        showTextNotification("Cardio");
      }
      if (compoundButton == cbLower) {
        showTextNotification("Lower");
      }
      if (compoundButton == cbUpper) {
        showTextNotification("Upper");
      }
      refresh(xx);
    }
  }

  //Pop up with which check box was picked using Toast.
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  private void refresh(int trainView) {
    List<Exercise> allexercise = null;
    try {
      allexercise = helper
          .getExerciseDao().queryForEq(CIRCUIT_COLNAME, circuit);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    //Display exercises.
    Trainer3ListAdapter trainer3Adapter = new Trainer3ListAdapter(getActivity(),
        R.layout.trainer3_listview, dayschedule);
    ListView trainer3View = (ListView) inflate.findViewById(R.id.listViewTrainer3);
    trainer3View.setAdapter(trainer3Adapter);
  }
}

