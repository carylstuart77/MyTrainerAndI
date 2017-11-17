package edu.cnm.deepdive.mytrainerandi;

import static edu.cnm.deepdive.mytrainerandi.entity.Exercise.CIRCUIT_COLNAME;

import android.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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

public class Trainer3 extends Fragment implements OnClickListener {

  private RadioGroup radioGroup;
  private OrmHelper helper;
  private ListView exerciseListView;
  private Button btnAbs;
  private Button btnCardio;
  private Button btnLower;
  private Button btnUpper;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

//need parans for casting for this ORM interaction. Listen to recording.
    helper = ((OrmInteraction)getActivity()).getHelper();

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

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.radioabs) {
      showTextNotification("Abs");
      refresh("abs");
    }
    if (view.getId()== R.id.radiocardio) {
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
  }
}

