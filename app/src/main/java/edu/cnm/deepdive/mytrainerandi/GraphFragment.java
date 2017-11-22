package edu.cnm.deepdive.mytrainerandi;


import static edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory.CLIENT_ID;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.SecondScale;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {

  private static final String CLIENT_ID_KEY = "client_id";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    //
    View root = inflater.inflate(R.layout.fragment_graph, container, false);
    List<FitnessHistory> clienthistory = null;
    try {
      Bundle bundle = this.getArguments();
      if (bundle != null) {

        //we know this fragment was created by and activity; implements ORM interaction interface; cast to FitnessHistory
        //TYPE CASTING. java object typecasting one object reference can be type cast into another object reference.
        // The cast can be to its own class type or to one of its subclass or superclass types or interfaces.
        clienthistory = ((OrmInteraction) getActivity())
            .getHelper().getFitnessHistoryDao()
            //get argument
            .queryForEq(CLIENT_ID, getArguments().getInt(CLIENT_ID_KEY));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    double x, y;
    x = 0;

    //Number of records.
    DataPoint[] vargraphwt = new DataPoint[clienthistory.size()];
    DataPoint[] vargraphbmi = new DataPoint[clienthistory.size()];
    DataPoint[] vargraphfat = new DataPoint[clienthistory.size()];
    //bmi, fat and wt
    //get for wt
    //get for bmi
    //get for fat
    //    int num_data_pts = 10;
    for (int i = 0; i < vargraphwt.length; i++) {
      FitnessHistory history = clienthistory.get(i);
      vargraphwt[i] = new DataPoint(history.getCreated(),
          history.getWeight());
      vargraphbmi[i] = new DataPoint(history.getCreated(),
          history.getBmi());
      vargraphfat[i] = new DataPoint(history.getCreated(),
          history.getFat());
    }

    //number of rows in fitness history. Should be same for BMI, FAT and Body Percentage.
    LineGraphSeries<DataPoint> serieswt = new LineGraphSeries<>(vargraphwt);
    LineGraphSeries<DataPoint> seriesbmi = new LineGraphSeries<>(vargraphbmi);
    LineGraphSeries<DataPoint> seriesfat = new LineGraphSeries<>(vargraphfat);

    serieswt.setColor(Color.RED);
    seriesbmi.setColor(Color.BLUE);
    seriesfat.setColor(Color.GREEN);

    serieswt.setTitle("Weight");
    seriesbmi.setTitle("BMI");
    seriesfat.setTitle("Fat");

    DateFormat format = new SimpleDateFormat("M/d");

    GraphView view = ((GraphView) root.findViewById(R.id.fitness_history_graph));
    view.getGridLabelRenderer().setHumanRounding(false);
    view.getLegendRenderer().setVisible(true);

    view.getGridLabelRenderer()
        .setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), format));
//cast xml to GraphView
    view.addSeries(serieswt);
    SecondScale rightAxis = view.getSecondScale();
    rightAxis.setVerticalAxisTitle("BMI");
    rightAxis.setMinY(0);
    rightAxis.setMaxY(40);
    rightAxis.addSeries(seriesbmi);
    // view.addSeries(seriesfat);

    return root;
  }

}
