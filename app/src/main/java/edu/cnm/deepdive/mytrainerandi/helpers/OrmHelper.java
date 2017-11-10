package edu.cnm.deepdive.mytrainerandi.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.mytrainerandi.entity.Client;
import edu.cnm.deepdive.mytrainerandi.entity.Exercise;
import edu.cnm.deepdive.mytrainerandi.entity.FitnessHistory;
import edu.cnm.deepdive.mytrainerandi.entity.Goal;
import edu.cnm.deepdive.mytrainerandi.entity.GoalLevel;
import java.sql.SQLException;

public class OrmHelper extends OrmLiteSqliteOpenHelper{

  private static final String DATABASE_NAME = "mytrainerandi.db";
  private static final int DATABASE_VERSION = 1;

  //params are object type; cannot use primitives
  private Dao<Client, Integer> clientDao = null;
  private Dao<FitnessHistory, Integer> fitnessHistoryDao = null;
  private Dao<Exercise, Integer> exerciseDao = null;
  private Dao<Goal, Integer> goalDao = null;
  private Dao<GoalLevel, Integer> goallevelDao = null;

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
//      populateDatabase();
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
    if (clientDao == null){
      clientDao = getDao(Client.class);
    }
    return clientDao;
  }

  //handle Dao; If there are more than one thread it will synchronize
  public synchronized Dao<Exercise, Integer> getExerciseDao() throws SQLException {
    if (exerciseDao == null){
      exerciseDao = getDao(Exercise.class);
    }
    return exerciseDao;
  }

  public synchronized Dao<FitnessHistory, Integer> getFitnessHistoryDao() throws SQLException {
    if (fitnessHistoryDao == null){
      fitnessHistoryDao = getDao(FitnessHistory.class);
    }
    return fitnessHistoryDao;
  }

  //one record saved to database.

//  private void populateDatabase() throws SQLException {
//    Client client = new Client();
//    client.setName("Charlie Smith");
//    client.setGoal("Muscle Mass");;
//    client.setLevel("2");
//    getClientDao().create(client);
//  }
}
