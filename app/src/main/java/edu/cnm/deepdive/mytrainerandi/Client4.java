package edu.cnm.deepdive.mytrainerandi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.j256.ormlite.dao.Dao;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;


/**
 * This fragment allows clients to capture their body statistics, such as height, weight, BMI and
 * body fat.  Over time they can see their progress by selecting the graph view.
 */
public class Client4 extends Fragment implements Button.OnClickListener {

  /**
   * Text view fields
   */
  private TextView mClientName;
  private TextView mClientHeight;
  private TextView mClientWeight;
  private TextView mClientBMI;
  private TextView mClientFat;
  private Client mClient;    //field of type client

  /**
   * Max and Min values to be checked upon entry.
   */
  double wt_max = 300;
  double wt_min = 80;

  double bmi_max = 35.0;
  double bmi_min = 15.0;

  int fat_max = 35;
  int fat_min = 15;

  /**
   * Constant value id key
   */
  public static final String CLIENT_ID_KEY = "client_id";
  /**
   * Spinner for client goal
   */
  private Spinner mClientGoal;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.client4, container, false);

    mClientName = inflate.findViewById(R.id.editClientName);
    mClientHeight = inflate.findViewById(R.id.editClientHeight);
    mClientWeight = inflate.findViewById(R.id.editClientWeight);
    mClientBMI = inflate.findViewById(R.id.editClientBMI);
    mClientFat = inflate.findViewById(R.id.editClientFat);

    try {
      Dao<Client, Integer> dao = ((OrmInteraction) getActivity()).getHelper().getClientDao();
      mClient = dao.queryForFirst(dao.queryBuilder().prepare());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    /**
     * Check if user name and height has been entered.  If not allow input to db.
     * If user exists then gray out client and height fields.
     * Provide serError messages to insure field input.
     */

    EditText firstName = (EditText) inflate.findViewById(R.id.editClientName);
    //Alert if name field is empty.
    if (firstName.getText().toString().length() != 0) {
      firstName.setError("Name is required.");
    }

    EditText clientHeight = (EditText) inflate.findViewById(R.id.editClientHeight);
    //Alert if height field is empty.
    if (clientHeight.getText().toString().length() != 0) {
      clientHeight.setError("Height is required.");
    }

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
    /** If client or height information exists, disable fields.*/
    if (mClient != null) {
      firstName.setText(mClient.getName());
      clientHeight.setText(Integer.toString(mClient.getHeight()));
      firstName.setEnabled(false);
      clientHeight.setEnabled(false);
    }

    mClientGoal = inflate.findViewById(R.id.spinnerGoal);

    //Save Client Data
    Button savebutton = inflate.findViewById(R.id.btnSaveClient);
    savebutton.setOnClickListener(this);
    // Option to view client history by graph.
    Button graphbutton = inflate.findViewById(R.id.btnViewClient);
    graphbutton.setOnClickListener(this);
    return inflate;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getActivity().setTitle("Client Statistics");
  }

  /**
   * Message notifications called on by onClick method.
   */
  public void showTextNotification(String msgToDisplay) {
    Toast.makeText(getActivity(), msgToDisplay, Toast.LENGTH_SHORT).show();
  }

  /**
   * Save will update both Client and Fitness History Tables.
   */
  @Override
  public void onClick(View view) {

    switch (view.getId()) {
      case R.id.btnSaveClient:
        try {

          //FitnessHistory Table updates
          FitnessHistory newfitnesshistory = new FitnessHistory();
//?Ask Chris
//          if (newfitnesshistory.getWeight() == Double.MIN_VALUE &&
//              newfitnesshistory.getBmi() == Double.MIN_VALUE &&
//              newfitnesshistory.getFat() == Double.MIN_VALUE) {
            newfitnesshistory.setWeight(Double.parseDouble(mClientWeight.getText().toString()));
            newfitnesshistory.setBmi(Double.parseDouble(mClientBMI.getText().toString()));
            newfitnesshistory.setFat(Double.parseDouble(mClientFat.getText().toString()));
            newfitnesshistory.setGoal(mClientGoal.getSelectedItem().toString());
//          } else {
//            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
//            alertDialog.setTitle("Missing Field Entry ");
//            alertDialog.setMessage("Enter valid data.");
//            return;
//          }

          //Validate Weight number range
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

          // Validate BMI number range
          if (newfitnesshistory.getBmi() > bmi_max || newfitnesshistory.getBmi() < bmi_min) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Alert BMI: ");
            alertDialog.setMessage("Enter BMI Range: 15 and 35.");
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

          // Validate Fat Percentage range
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

          /** Create client information in fitness history to be used in graph.*/
          newfitnesshistory.setClient(mClient);
          ((OrmInteraction) getActivity()).getHelper().getFitnessHistoryDao()
              .create(newfitnesshistory);

          if (view.getId() == R.id.btnSaveClient) {
            showTextNotification("SAVED IT!");
          }

          //Refresh screen after client name and height is entered to make them unselectable.
          Client4 fragment = new Client4();
          FragmentTransaction ft = getFragmentManager().beginTransaction();
          ft.replace(R.id.content_main, fragment);
          ft.commit();

          break;
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
        /**
         * Display fitness History in graph form.
         * Key-Value of bundle for type put.
         * Attaching argument bundle to graph fragment and begin graph fragment.
         */
      case R.id.btnViewClient:
        //Log.i("In Button View", "View Client");   //Used to log to monitor for trouble shooting.
        //Log.isLoggable ("viewclient", + mClient.getId());

        //?Ask chris
        if (mClient.getId() == 1) {
          //View Button--Add bundle of client id
          GraphFragment fragmentgraph = new GraphFragment();
          //create bundle object
          Bundle bundle = new Bundle();
          //type put method of bundle; add arguments to bundle: key-value
          bundle.putInt(CLIENT_ID_KEY, mClient.getId());

          //Attach arguments bundle to fragment
          fragmentgraph.setArguments(bundle);
          FragmentTransaction ft = getFragmentManager().beginTransaction();
          ft.replace(R.id.content_main, fragmentgraph);
          //use back arrow to go back to client4 from graph.
          ft.addToBackStack("back to client")
              .commit();
          break;
        } else {

          showTextNotification("Please enter in client information.");
        }

    }
  }
}
