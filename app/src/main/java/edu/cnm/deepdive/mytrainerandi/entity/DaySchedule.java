package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

@DatabaseTable(tableName = "DAYSCHEDULE")
public class DaySchedule {

  public static final String MUSCLE_CONSTANT = "MUSCLE";
  public static final String EXERCISE_CONSTANT = "EXERCISE";
  public static final String SETS_CONSTANT = "SET";
  public static final String REPS_CONSTANT = "REPS";
  @DatabaseField(columnName = "DAYSCHEDULE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  @DatabaseField(columnName = MUSCLE_CONSTANT, canBeNull = false)
  private String muscle;

  @DatabaseField(columnName = EXERCISE_CONSTANT, canBeNull = false)
  private String excercise;

  @DatabaseField(columnName = SETS_CONSTANT, canBeNull = false)
  private String set;

  @DatabaseField(columnName = REPS_CONSTANT, canBeNull = false)
  private String reps;

  public int getId() {
    return id;
  }

  public Timestamp getCreated() {
    return created;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMuscle() {
    return muscle;
  }

  public void setMuscle(String muscle) {
    this.muscle = muscle;
  }

  public String getExcercise() {
    return excercise;
  }

  public void setExcercise(String excercise) {
    this.excercise = excercise;
  }

  public String getSet() {
    return set;
  }

  public void setSet(String set) {
    this.set = set;
  }

  public String getReps() {
    return reps;
  }

  public void setReps(String reps) {
    this.reps = reps;
  }
}
