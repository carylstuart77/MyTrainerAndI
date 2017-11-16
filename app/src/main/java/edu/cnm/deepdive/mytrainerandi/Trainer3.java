package edu.cnm.deepdive.mytrainerandi;

import static edu.cnm.deepdive.mytrainerandi.entity.Exercise.CIRCUIT_COLNAME;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import edu.cnm.deepdive.mytrainerandi.adapters.Trainer3ListAdapter;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.util.List;

public class Trainer3 extends Fragment implements OnCheckedChangeListener {

  private RadioGroup radioGroup;
  private OrmHelper helper;
  private ListView exerciseListView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

//need parans for casting for this ORM interaction. Listen to recording.
    helper = ((OrmInteraction)getActivity()).getHelper();

    View trainerView = inflater.inflate(R.layout.trainer3, container, false);

    //Radio Button generates an Event Source-state change.
    radioGroup = trainerView.findViewById(R.id.radioGroup);

    //Radio Group Event Listener is notified when an event occurs.
    radioGroup.setOnCheckedChangeListener(this);

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
  public void onCheckedChanged(RadioGroup group, int checkedId) {
      //used to trouble shoot.
      Log.v("Trainer 3", "id" + checkedId);

      if (checkedId == R.id.radioabs) {
        showTextNotification("Abs");
        refresh("abs");
      }
      if (checkedId == R.id.radiocardio) {
        showTextNotification("Cardio");
        refresh("cardio");
      }
      if (checkedId == R.id.radiolower) {
        showTextNotification("Lower");
        refresh("lower");
      }
      if (checkedId == R.id.radioupper) {
        showTextNotification("Upper");
        refresh("upper");
      }

    }

  //Pop up with which radio button was picked using Toast.
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

}

