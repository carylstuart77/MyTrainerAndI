package edu.cnm.deepdive.mytrainerandi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import java.sql.SQLException;

public class Client4 extends Fragment implements Button.OnClickListener{

  private OrmHelper helper;
  private TextView mClientName;
  private TextView mClientWeight;
  private TextView mClientHeight;
  private TextView mClientBMI;
  private TextView mClientGoal;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.client4, container, false);

    mClientName = inflate.findViewById(R.id.editClientName);
    mClientWeight = inflate.findViewById(R.id.editClientWeight);
    mClientHeight = inflate.findViewById(R.id.editClientHeight);
    mClientBMI = inflate.findViewById(R.id.editClientBMI);
    //mClientGoal = inflate.findViewById(R.id.spinnerGoal);

//    Spinner spinner = (Spinner) inflate.findViewById(R.id.spinnerGoal);
//    String size = spinner.getSelectedItem().toString(); // Small, Medium, Large
//
//    int spinner_pos = spinner.getSelectedItemPosition();
//    String[] size_values = getResources().getStringArray(R.array.goal_arrays);
//    String.valueOf(size_values[spinner_pos]); // 12, 16, 20


    //Add Client Data
    Button addbutton = inflate.findViewById(R.id.buttonAddClient);
    addbutton.setOnClickListener(this);
   //View Button
    Button viewbutton = inflate.findViewById(R.id.buttonViewClient);
    viewbutton.setOnClickListener(this);
    return inflate;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getActivity().setTitle("Client Statistics");
  }

  @Override
  public void onClick(View view) {
    //add validation
    //add data
    try {
      Client newclient = new Client();
      newclient.setName(mClientName.getText().toString());
      //newclient.setGoal(spinnerGoal);
      //newclient.setName("Caryl Baca");
      //newclient.setGoal("Tone Muscle");
      //newclient.setLevel("L1");
      helper.getClientDao().create(newclient);

      FitnessHistory newfitnesshistory = new FitnessHistory();
      newfitnesshistory.setWeight(Double.parseDouble(mClientWeight.getText().toString()));
      newfitnesshistory.setHeight(Integer.parseInt(mClientHeight.getText().toString()));
      newfitnesshistory.setBmi(Double.parseDouble(mClientBMI.getText().toString()));
      helper.getFitnessHistoryDao().create(newfitnesshistory);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
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

  @Override
  public void onStart() {
    super.onStart();
    getHelper();
  }

  @Override
  public void onStop() {
    super.onStop();
    releaseHelper();
  }
}
