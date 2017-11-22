package edu.cnm.deepdive.mytrainerandi;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Client4 extends Fragment implements Button.OnClickListener {

  private TextView mClientName;
  private TextView mClientHeight;
  private TextView mClientWeight;
  private TextView mClientBMI;
  private TextView mClientFat;
  private Client mClient;    //field of type client

  double wt_max = 300;
  double wt_min = 80;
  double bmi_max = 33.0;
  double bmi_min = 17.0;
  int fat_max = 35;
  int fat_min = 15;

  public static final String CLIENT_ID_KEY = "client_id";

  /**
   * This will update both Client and Fitness History Tables
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.client4, container, false);
    //Refresh screen after client name and height is entered to make them unselectable.
    /*Client4 fragment = new Client4();
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    ft.replace(R.id.content_main, fragment);
    ft.commit();*/



    mClientName = inflate.findViewById(R.id.editClientName);
    mClientHeight = inflate.findViewById(R.id.editClientHeight);
    mClientWeight = inflate.findViewById(R.id.editClientWeight);
    mClientBMI = inflate.findViewById(R.id.editClientBMI);
    mClientFat = inflate.findViewById(R.id.editClientFat);

    try {
      Dao<Client, Integer> dao = ((OrmInteraction) getActivity()).getHelper().getClientDao();
      mClient = dao.queryForFirst(dao.queryBuilder().prepare());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    //Use setError to inform user that each field is required.
    EditText firstName = (EditText) inflate.findViewById(R.id.editClientName);
    //Alert if empty removed.
    //  if( firstName.getText().toString().length() == 0 )
    //      firstName.setError( "Name is required." );

    EditText clientHeight = (EditText) inflate.findViewById(R.id.editClientHeight);
    //Alert if empty removed.
    // if (clientHeight.getText().toString().length() == 0)
    //    clientHeight.setError("Height is required.");

    EditText clientWeight = (EditText) inflate.findViewById(R.id.editClientWeight);
    if (clientWeight.getText().toString().length() == 0) {
      clientWeight.setError("Weight is required.");
    }

    EditText clientBmi = (EditText) inflate.findViewById(R.id.editClientBMI);
    if (clientBmi.getText().toString().length() == 0) {
      clientBmi.setError("BMI is required.");
    }

    EditText clientFat = (EditText) inflate.findViewById(R.id.editClientFat);
    if (clientFat.getText().toString().length() == 0) {
      clientFat.setError("Fat percentage is required.");
    }

    if (mClient != null) {
      firstName.setText(mClient.getName());
      clientHeight.setText(Integer.toString(mClient.getHeight()));
      firstName.setEnabled(false);
      clientHeight.setEnabled(false);
    }

    //?Ask Chris about spinner value.
    // mClientGoal = inflate.findViewById(R.id.spinnerGoal);
//    Spinner spinner = (Spinner) inflate.findViewById(R.id.spinnerGoal);
//    String size = spinner.getSelectedItem().toString(); // Small, Medium, Large
//
//    int spinner_pos = spinner.getSelectedItemPosition();
//    String[] size_values = getResources().getStringArray(R.array.goal_arrays);
//    String.valueOf(size_values[spinner_pos]); // 12, 16, 20

    //Save Client Data
    Button savebutton = inflate.findViewById(R.id.btnSaveClient);
    savebutton.setOnClickListener(this);

    Button viewbutton = inflate.findViewById(R.id.btnViewClient);
    viewbutton.setOnClickListener(this);
    return inflate;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getActivity().setTitle("Client Statistics");
  }

  //Pop up with which button was picked using Toast.
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(getActivity(), msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onClick(View view) {
    //add validation
    //add data
    switch (view.getId()) {
      case R.id.btnSaveClient:
        try {

          //FitnessHistory Table
          FitnessHistory newfitnesshistory = new FitnessHistory();
          newfitnesshistory.setWeight(Double.parseDouble(mClientWeight.getText().toString()));
          newfitnesshistory.setBmi(Double.parseDouble(mClientBMI.getText().toString()));
          newfitnesshistory.setFat(Double.parseDouble(mClientFat.getText().toString()));

          //Validate Weight numbers
          if (newfitnesshistory.getWeight() > wt_max || newfitnesshistory.getWeight() < wt_min) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Alert Weight: ");
            alertDialog.setMessage("Enter Weight Range between: 80 and 300 lbs.");
            // Alert dialog button
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();// use dismiss to cancel alert dialog
                  }
                });
            alertDialog.show();

            return;
          }

          // Validate BMI numbers
          if (newfitnesshistory.getBmi() > bmi_max || newfitnesshistory.getBmi() < bmi_min) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Alert BMI: ");
            alertDialog.setMessage("Enter BMI Range: 17 and 33.");
            // Alert dialog button
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();// use dismiss to cancel alert dialog
                  }
                });
            alertDialog.show();

            return;
          }

          // Validate Fat Percentage
          if (newfitnesshistory.getFat() > fat_max || newfitnesshistory.getFat() < fat_min) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Alert Fat: ");
            alertDialog.setMessage("Fat Percentage: 15 and 35.");
            // Alert dialog button
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();// use dismiss to cancel alert dialog
                  }
                });
            alertDialog.show();

            return;
          }

          if (mClient == null) {
            Client newclient = new Client();
            newclient.setName(mClientName.getText().toString());
            newclient.setHeight(Integer.parseInt(mClientHeight.getText().toString()));
            ((OrmInteraction) getActivity()).getHelper().getClientDao().create(newclient);
            mClient = newclient;
          }
          // Create in the database.
          newfitnesshistory.setClient(mClient);
          ((OrmInteraction) getActivity()).getHelper().getFitnessHistoryDao()
              .create(newfitnesshistory);

          if (view.getId() == R.id.btnSaveClient) {
            showTextNotification("SAVED IT!");
          }

          break;
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

      case R.id.btnViewClient:
        Log.i("In Button View", "View Client");
        //Refresh screen after client name and height is entered to make them unselectable.
//        Client4 fragment = new Client4();
//        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.content_main, fragment);
//        ft.commit();

        /**
         * Display fitness History in graph form.
         */
        //View Button--Add bundle of client id
        GraphFragment fragmentgraph = new GraphFragment();
        //create bundle object
        Bundle bundle = new Bundle();
        //type put method of bundle; add arguments to bundle. key-value
        bundle.putInt(CLIENT_ID_KEY, mClient.getId());
        //Attach arguments bundle to fragment
        fragmentgraph.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, fragmentgraph);
        ft.addToBackStack("back to client").commit();
        break;
    }


  }

}
