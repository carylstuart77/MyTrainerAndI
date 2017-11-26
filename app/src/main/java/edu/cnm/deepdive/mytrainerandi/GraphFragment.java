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
 * Fragment is called from Client4.java to display graphed data from FITNESS_HISTORY table. Table
 * data consists of: Weight, BMI and Fat percentage for a single client ID.
 */
public class GraphFragment extends Fragment {

  /**
   * Retrieves and saves ID of client
   */
  private static final String CLIENT_ID_KEY = "client_id";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.fragment_graph, container, false);
    List<FitnessHistory> clienthistory = null;
    try {
      Bundle bundle = this.getArguments();
      if (bundle != null) {

        // Java object typecasting one object reference can be type cast into another object reference.
        // The cast can be to its own class type or to one of its subclass or superclass types or interfaces.

        /**
         * Implement ORM interaction interface getFitnessHistoryDao.  Client ID is passed as an argument
         * to pull history information for client using ID key.
         */
        clienthistory = ((OrmInteraction) getActivity())
            .getHelper().getFitnessHistoryDao()
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

    //Loop through rows and pull weight, bmi and fat percentage.
    for (int i = 0; i < vargraphwt.length; i++) {
      FitnessHistory history = clienthistory.get(i);
      vargraphwt[i] = new DataPoint(history.getCreated(),
          history.getWeight());
      vargraphbmi[i] = new DataPoint(history.getCreated(),
          history.getBmi());
      vargraphfat[i] = new DataPoint(history.getCreated(),
          history.getFat());
    }

    //number of rows in fitness history. Will be the same for BMI, FAT and Body Percentage.
    LineGraphSeries<DataPoint> serieswt = new LineGraphSeries<>(vargraphwt);
    LineGraphSeries<DataPoint> seriesbmi = new LineGraphSeries<>(vargraphbmi);
    LineGraphSeries<DataPoint> seriesfat = new LineGraphSeries<>(vargraphfat);

    //Graph Colors
    serieswt.setColor(Color.RED);
    seriesbmi.setColor(Color.BLUE);
    seriesfat.setColor(Color.GREEN);

    serieswt.setDrawDataPoints(true);
    seriesbmi.setDrawDataPoints(true);
    seriesfat.setDrawDataPoints(true);

    //Graph Line Thickness
    serieswt.setThickness(8);
    seriesbmi.setThickness(8);
    seriesfat.setThickness(8);

    //Graph Legend
    serieswt.setTitle("Weight");
    seriesbmi.setTitle("BMI");
    seriesfat.setTitle("Fat");

    //X access date format
    DateFormat format = new SimpleDateFormat("M/d");

    // Setup view for graph from fragment_graph.xml
    GraphView view = ((GraphView) root.findViewById(R.id.fitness_history_graph));
    view.getGridLabelRenderer().setHumanRounding(false);
    view.getLegendRenderer().setVisible(true);

    view.getGridLabelRenderer()
        .setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), format));

    // Add weight and fat
    view.addSeries(serieswt);
    view.addSeries(seriesfat);

    /**
     * Setup second scale to the right for BMI numbers.
     * Second scale MinY to 0 and MaxY to 40.
     */
    SecondScale rightAxis = view.getSecondScale();

    rightAxis.setVerticalAxisTitle("BMI");
    rightAxis.setVerticalAxisTitleColor(Color.BLUE);
    rightAxis.setMinY(0);
    rightAxis.setMaxY(40);
    rightAxis.addSeries(seriesbmi);

    return root;
  }
}
