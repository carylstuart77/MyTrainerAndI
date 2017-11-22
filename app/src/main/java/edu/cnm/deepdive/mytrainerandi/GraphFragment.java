package edu.cnm.deepdive.mytrainerandi;


import static edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory.CLIENT_ID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
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

    //TODO query fitness history by client id.
    //for (int i;)
    //x and y coordinate.
    DataPoint[] vargraphwt = new DataPoint[clienthistory.size()];
    DataPoint[] vargraphbmi = new DataPoint[clienthistory.size()];
    DataPoint[] vargraphfat = new DataPoint[clienthistory.size()];  
    //bmi, fat and wt
    LineGraphSeries<DataPoint> serieswt = new LineGraphSeries<>(vargraphwt);
    LineGraphSeries<DataPoint> seriesbmi = new LineGraphSeries<>(vargraphbmi);
    LineGraphSeries<DataPoint> seriesfat = new LineGraphSeries<>(vargraphfat);

    ((GraphView) root.findViewById(R.id.fitness_history_graph)).addSeries(serieswt);

    return root;
  }

}
