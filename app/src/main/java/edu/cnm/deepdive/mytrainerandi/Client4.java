package edu.cnm.deepdive.mytrainerandi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import java.sql.SQLException;

public class Client4 extends Fragment implements Button.OnClickListener{

  private OrmHelper helper;
  private TextView mClientName;
  private TextView mClientHeight;
  private TextView mClientWeight;
  private TextView mClientBMI;
  private TextView mClientFat;
  //?ask Chris
//  private spinnervalue;

  /**
   * This will update both Client and Fitness History Tables
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.client4, container, false);

    mClientName = inflate.findViewById(R.id.editClientName);
    mClientHeight = inflate.findViewById(R.id.editClientHeight);
    mClientWeight = inflate.findViewById(R.id.editClientWeight);
    mClientBMI = inflate.findViewById(R.id.editClientBMI);
    mClientFat = inflate.findViewById(R.id.editClientFat);

    //?Ask Chris about spinner value.
    // mClientGoal = inflate.findViewById(R.id.spinnerGoal);
//    Spinner spinner = (Spinner) inflate.findViewById(R.id.spinnerGoal);
//    String size = spinner.getSelectedItem().toString(); // Small, Medium, Large
//
//    int spinner_pos = spinner.getSelectedItemPosition();
//    String[] size_values = getResources().getStringArray(R.array.goal_arrays);
//    String.valueOf(size_values[spinner_pos]); // 12, 16, 20


    //Add Client Data
    Button addbutton = inflate.findViewById(R.id.buttonAddClient);
    addbutton.setOnClickListener(this);

    //?ASk Chris how to display data from db.
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
      newclient.setHeight(Integer.parseInt(mClientHeight.getText().toString()));
      helper.getClientDao().create(newclient);

      //FitnessHistory Table
      FitnessHistory newfitnesshistory = new FitnessHistory();
      newfitnesshistory.setWeight(Double.parseDouble(mClientWeight.getText().toString()));
      newfitnesshistory.setBmi(Double.parseDouble(mClientBMI.getText().toString()));
      newfitnesshistory.setFat(Double.parseDouble(mClientFat.getText().toString()));
      //?Ask Chris about Spinner field
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
