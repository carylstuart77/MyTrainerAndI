package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

@DatabaseTable(tableName = "DAYSCHEDULE")
public class DaySchedule {

  @DatabaseField(columnName = "DAYSCHEDULE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  @DatabaseField(columnName = "MUSCLE", canBeNull = false)
  private String muscle;

  @DatabaseField(columnName = "EXERCISE", canBeNull = false)
  private String excercise;

  @DatabaseField(columnName = "SET", canBeNull = false)
  private String set;

  @DatabaseField(columnName = "REPS", canBeNull = false)
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
