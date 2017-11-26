package edu.cnm.deepdive.mytrainerandi.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.mytrainerandi.R;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.entity.Goal;
import edu.cnm.deepdive.mytrainerandi.entity.GoalHistory;
import edu.cnm.deepdive.mytrainerandi.entity.GoalLevel;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import org.apache.commons.io.IOUtils;


/**
 * ORMHelper framework provides object relational mapping (ORM) between Java classes and SQL
 * databases.  The main helper will create a database called mytrainerandi if it does not exist.
 * Also, it will create the MTAI tables needed for capturing log data and uploading raw exercise
 * data.
 */
public class OrmHelper extends OrmLiteSqliteOpenHelper {

  /**
   * Constant database name, version control and maintain context state.
   */
  private static final String DATABASE_NAME = "mytrainerandi.db";
  private static final int DATABASE_VERSION = 1;
  private final Context context;

  /**
   * Data Objects Daos: entity parameter types and their data types of Integer keys.
   */
  private Dao<Client, Integer> clientDao = null;
  private Dao<FitnessHistory, Integer> fitnessHistoryDao = null;
  private Dao<Exercise, Integer> exerciseDao = null;
  private Dao<Goal, Integer> goalDao = null;
  private Dao<GoalLevel, Integer> goallevelDao = null;
  private Dao<ExerciseByDay, Integer> dayscheduleDao = null;
  private Dao<ExerciseByDay, Integer> exerciseByDayDao = null;

  /**
   * Constructor used to pass to the super class the context for reading it in.
   */
  public OrmHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
  }

  //Client.class is a class object.
  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {

      /**
       * Create tables: attribute fields are generated from entity classes.
       * Call populateDatabase to manually initialise tables when needed.
       */
      TableUtils.createTable(connectionSource, Client.class);
      TableUtils.createTable(connectionSource, Exercise.class);
      TableUtils.createTable(connectionSource, FitnessHistory.class);
      TableUtils.createTable(connectionSource, Goal.class);
      TableUtils.createTable(connectionSource, GoalLevel.class);
      TableUtils.createTable(connectionSource, ExerciseByDay.class);
      TableUtils.createTable(connectionSource, GoalHistory.class);
      populateDatabase(database);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  //Version control upgrade is handled when table fields change.
  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
      int newVersion) {
  }

  //Any references to Dao is released by setting to null at close
  @Override
  public void close() {
    clientDao = null;
    super.close();
  }


  /**
   * Handle Client Dao block with synchronization to avoid race condition of more than one thread
   * running at a time. Throw SQL exception if it occurs.
   */
  public synchronized Dao<Client, Integer> getClientDao() throws SQLException {
    if (clientDao == null) {
      clientDao = getDao(Client.class);
    }
    return clientDao;
  }

  /**
   * ExerciseByDay synchronization Doa handling to prevent race condition.
   */
  public synchronized Dao<ExerciseByDay, Integer> getDayscheduleDao() throws SQLException {
    if (dayscheduleDao == null) {
      dayscheduleDao = getDao(ExerciseByDay.class);
    }
    return dayscheduleDao;
  }


  /**
   * Goal synchronization Doa handling to prevent race condition.
   */
  public synchronized Dao<Goal, Integer> getGoalDao() throws SQLException {
    if (goalDao == null) {
      goalDao = getDao(Goal.class);
    }
    return goalDao;
  }

  /**
   * Execise synchronization Doa handling to prevent race condition.
   */
  public synchronized Dao<Exercise, Integer> getExerciseDao() throws SQLException {
    if (exerciseDao == null) {
      exerciseDao = getDao(Exercise.class);
    }
    return exerciseDao;
  }

  /**
   * FitnessHistory synchronization Doa handling to prevent race condition.
   */
  public synchronized Dao<FitnessHistory, Integer> getFitnessHistoryDao() throws SQLException {
    if (fitnessHistoryDao == null) {
      fitnessHistoryDao = getDao(FitnessHistory.class);
    }
    return fitnessHistoryDao;
  }

  /**
   * ExerciseByDay synchronization Doa handling to prevent race condition.
   */
  public synchronized Dao<ExerciseByDay, Integer> getExerciseByDayDao() throws SQLException {
    if (exerciseByDayDao == null) {
      exerciseByDayDao = getDao(ExerciseByDay.class);
    }
    return exerciseByDayDao;
  }


  /**
   * Database table is EXERCISE is populated by raw resource main_exercise as a master list of
   * exercises to be used in Trainer3 selections.
   *
   * Database table FITNESS_HISTORY is populated with client information for demonstration of
   * graph.
   *
   * Sample code commented out.  Used for testing purposes.
   */

  private void populateDatabase(SQLiteDatabase database) throws SQLException {

    InputStream inputStream = context.getResources().openRawResource(R.raw.main_exercise);
    String queries = "";
    try {
      queries = IOUtils.toString(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (String query : queries.split(";")) {
      database.execSQL(query);
    }

    InputStream inputStreamClient = context.getResources().openRawResource(R.raw.client_stats);
    try {
      Log.i("Input client_Stats", "client Stats!!!");
      queries = IOUtils.toString(inputStreamClient);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (String query : queries.split(";")) {
      database.execSQL(query);
    }

//    //---------Sample population of Client4s clientName and height.-KEEP ---------//
//    //Currently populated from Client4 UI.
//    Client client = new Client();
//    client.setName("Tucker Stuart");
//    client.setHeight(64);
//    getClientDao().createIfNotExists(client);

//    //---------Sample population of Goals for spinner in client4-KEEP ---------//
//    //Currently populated from strings.xml goal-arrays.
//    Goal goalMM = new Goal();
//
//    goalMM.setName("Muscle Mass");
//
//    getGoalDao().create(goalMM);

//    //---------Sample population of ExerciseByDay Table-KEEP ---------//
//    //ExerciseByDay Table for daily plan of exercises set by trainer.
//    //Currently populating from trainer3 UI.
//
//    ExerciseByDay dayRow = new ExerciseByDay();
//
//    dayRow.setMuscle("Calves");
//    dayRow.setExercise("Lifts");
//    dayRow.setSets(2);
//    dayRow.setReps(2);
//    dayRow.setDayofweek(1);
//
//    getDayscheduleDao().create(dayRow);  //write row
//
//    dayRow = new ExerciseByDay();
//
//    dayRow.setMuscle("Hamstrings");
//    dayRow.setExercise("Bent Overs");
//    dayRow.setSets(4);
//    dayRow.setReps(12);
//    dayRow.setDayofweek(1);
//
//    getDayscheduleDao().create(dayRow);  //write row
//
//    //---------Sample population of Exercise Table-KEEP ---------//
//    //Trainers Exercise Table for master list of exercises.
//    //Currently populating from raw main_exercise.sql
//
//    Exercise allExercise = new Exercise();
//
//    allExercise.setExercisename("Clean Deadlift");
//    allExercise.setMuscle("Hamstring");
//    allExercise.setCircuit("lower");
//
//    getExerciseDao().create(allExercise);  //write row

  }

  /**
   * Allows other class components to receive the getHelper method behavior.
   */
  public interface OrmInteraction {

    OrmHelper getHelper();

  }
}
