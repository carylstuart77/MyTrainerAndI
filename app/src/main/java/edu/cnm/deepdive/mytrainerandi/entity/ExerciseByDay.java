package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

@DatabaseTable(tableName = "DAYSCHEDULE")
public class ExerciseByDay {

  public static final String EXERCISE_CONSTANT = "EXERCISE";
  public static final String SETS_CONSTANT = "SET";
  public static final String REPS_CONSTANT = "REPS";
  public static final String DAYOFWEEK = "DAY_OF_WEEK" ;
  public static final String LBS_LIFTED = "LBS_LIFTED";

  @DatabaseField(columnName = "DAYSCHEDULE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

//Find field in foreign table (exercise)
  @DatabaseField(columnName = EXERCISE_CONSTANT, canBeNull = false, foreign = true, foreignAutoRefresh = true)
  private Exercise exercise;

  @DatabaseField(columnName = SETS_CONSTANT, canBeNull = false)
  private int sets;

  @DatabaseField(columnName = REPS_CONSTANT, canBeNull = false)
  private int reps;

  @DatabaseField(columnName = LBS_LIFTED, canBeNull = true)
  private int lbs;

  @DatabaseField(columnName = DAYOFWEEK, canBeNull = false)
  private int dayofweek;
//Random ID
  public int getId() {
    return id;
  }
//TimeStamp
  public Timestamp getCreated() {
    return created;
  }

  //Exercise
  public Exercise getExercise() {
    return exercise;
  }

  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }

  //Day of week
  public int getDayofweek() {
    return dayofweek;
  }

  public void setDayofweek(int dayofweek) {
    this.dayofweek = dayofweek;
  }

  //Sets
  public int getSets() {
    return sets;
  }

  public void setSets(int sets) {
    this.sets = sets;
  }

  //Reps
  public int getReps() {
    return reps;
  }

  public void setReps(int reps) {
    this.reps = reps;
  }

  // Lifted Pounds LBS
  public int getLbs() {
    return lbs;
  }

  public void setLbs(int lbs) {
    this.lbs = lbs;
  }
}
