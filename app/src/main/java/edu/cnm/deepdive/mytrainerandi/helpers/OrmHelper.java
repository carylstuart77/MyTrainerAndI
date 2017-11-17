package edu.cnm.deepdive.mytrainerandi.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.ExerciseByDay;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.entity.Goal;
import edu.cnm.deepdive.mytrainerandi.entity.GoalHistory;
import edu.cnm.deepdive.mytrainerandi.entity.GoalLevel;
import java.sql.SQLException;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "mytrainerandi.db";
  private static final int DATABASE_VERSION = 1;

  //params are object type; cannot use primitives
  private Dao<Client, Integer> clientDao = null;
  private Dao<FitnessHistory, Integer> fitnessHistoryDao = null;
  private Dao<Exercise, Integer> exerciseDao = null;
  private Dao<Goal, Integer> goalDao = null;
  private Dao<GoalLevel, Integer> goallevelDao = null;
  private Dao<ExerciseByDay, Integer> dayscheduleDao = null;


  public OrmHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  //Client.class is a class object.
  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {
      //Creates the tables of class; classes are entities; class fields are attributes.
      TableUtils.createTable(connectionSource, Client.class);
      TableUtils.createTable(connectionSource, Exercise.class);
      TableUtils.createTable(connectionSource, FitnessHistory.class);
      TableUtils.createTable(connectionSource, Goal.class);
      TableUtils.createTable(connectionSource, GoalLevel.class);
      TableUtils.createTable(connectionSource, ExerciseByDay.class);
      TableUtils.createTable(connectionSource, GoalHistory.class);
      populateDatabase();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  //this will handle an upgrade like adding a field.
  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
      int newVersion) {

  }

  //any references to Dao is released by setting to null at close
  @Override
  public void close() {
    clientDao = null;
    super.close();
  }

  //handle Dao; If there are more than one thread it will synchronize
  public synchronized Dao<Client, Integer> getClientDao() throws SQLException {
    if (clientDao == null) {
      clientDao = getDao(Client.class);
    }
    return clientDao;
  }

  public synchronized Dao<ExerciseByDay, Integer> getDayscheduleDao() throws SQLException {
    if (dayscheduleDao == null) {
      dayscheduleDao = getDao(ExerciseByDay.class);
    }
    return dayscheduleDao;
  }

  //handle Dao; If there are more than one thread it will synchronize
  public synchronized Dao<Goal, Integer> getGoalDao() throws SQLException {
    if (goalDao == null) {
      goalDao = getDao(Goal.class);
    }
    return goalDao;
  }

  //handle Dao; If there are more than one thread it will synchronize
  public synchronized Dao<Exercise, Integer> getExerciseDao() throws SQLException {
    if (exerciseDao == null) {
      exerciseDao = getDao(Exercise.class);
    }
    return exerciseDao;
  }

  public synchronized Dao<FitnessHistory, Integer> getFitnessHistoryDao() throws SQLException {
    if (fitnessHistoryDao == null) {
      fitnessHistoryDao = getDao(FitnessHistory.class);
    }
    return fitnessHistoryDao;
  }

  //------------------------------------------
  //Populate tables

  private void populateDatabase() throws SQLException {


    Goal goalMM = new Goal();
    Goal goalWL = new Goal();
    Goal goalWG = new Goal();
    Goal goalTM = new Goal();
    Goal goalIH = new Goal();

    goalMM.setName("Muscle Mass");
    goalWL.setName("Weight Loss");
    goalWG.setName("Weight Gain");
    goalTM.setName("Tone Musle");
    goalIH.setName("Injury Active Recovery");

    getGoalDao().create(goalMM);
    getGoalDao().create(goalWL);
    getGoalDao().create(goalWG);
    getGoalDao().create(goalTM);
    getGoalDao().create(goalIH);

    //------------------------------------------
    //ExerciseByDay Table in entity folder.

    ExerciseByDay dayRow = new ExerciseByDay();

    dayRow.setMuscle("Calves");
    dayRow.setExercise("Lifts");
    dayRow.setSet("4");
    dayRow.setReps("12");
    dayRow.setDayofweek(1);

    getDayscheduleDao().create(dayRow);  //write row

    dayRow = new ExerciseByDay();

    dayRow.setMuscle("Hamstrings");
    dayRow.setExercise("Bent Overs");
    dayRow.setSet("4");
    dayRow.setReps("12");
    dayRow.setDayofweek(1);

    getDayscheduleDao().create(dayRow);  //write row

    dayRow = new ExerciseByDay();

    dayRow.setMuscle("Quads");
    dayRow.setExercise("Lunges");
    dayRow.setSet("4");
    dayRow.setReps("12");
    dayRow.setDayofweek(1);

    getDayscheduleDao().create(dayRow);  //write row

    dayRow = new ExerciseByDay();
    dayRow.setMuscle("Chest");
    dayRow.setExercise("Flyes");
    dayRow.setSet("4");
    dayRow.setReps("12");
    dayRow.setDayofweek(2);

    getDayscheduleDao().create(dayRow);  //write row

    dayRow = new ExerciseByDay();
    dayRow.setMuscle("Shoulders");
    dayRow.setExercise("Shoulder Pushes");
    dayRow.setSet("4");
    dayRow.setReps("12");
    dayRow.setDayofweek(3);

    getDayscheduleDao().create(dayRow);  //write row

    //------------------------------------------
    //Exercise Table in entity folder for ALL exercises.
//
    Exercise allExercise = new Exercise();

    allExercise.setExercisename("Clean Deadlift");
    allExercise.setMuscle("Hamstring");
    allExercise.setCircuit("lower");

    getExerciseDao().create(allExercise);  //write row

    allExercise = new Exercise();

    allExercise.setExercisename("Romanian Deadlift");
    allExercise.setMuscle("Hamstring");
    allExercise.setCircuit("lower");

    getExerciseDao().create(allExercise);  //write row

    allExercise = new Exercise();

    allExercise.setExercisename("Kettlebell Deadlift");
    allExercise.setMuscle("Hamstring");
    allExercise.setCircuit("lower");

    getExerciseDao().create(allExercise);  //write row

    allExercise = new Exercise();

    allExercise.setExercisename("Lying Leg Curls");
    allExercise.setMuscle("Calves");
    allExercise.setCircuit("lower");

    getExerciseDao().create(allExercise);  //write row

    allExercise = new Exercise();

    allExercise.setExercisename("Chest Fly");
    allExercise.setMuscle("Chest");
    allExercise.setCircuit("upper");

    getExerciseDao().create(allExercise);  //write row

    allExercise = new Exercise();

    allExercise.setExercisename("Stepper 30 minutes");
    allExercise.setMuscle("Heart");
    allExercise.setCircuit("cardio");

    getExerciseDao().create(allExercise);  //write row

    allExercise = new Exercise();

    allExercise.setExercisename("Plank 1 minute");
    allExercise.setMuscle("Abs");
    allExercise.setCircuit("core");

    getExerciseDao().create(allExercise);  //write row
  }

  public interface OrmInteraction {
    OrmHelper getHelper();

  }
}
