package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import java.util.Date;

/**
 * Configure ExerciseByDayHistory class to be persisted db table.
 */

public class ExerciseByDayHistory {

  /**
   * The name field is configured as the primary key for the database table by using the
   * generatedId = true annotation field.
   */
  @DatabaseField(columnName = "DAYSCHEDULE_ID", generatedId = true)
  private int id;

  /**
   * Create db fields for Client table to hold the actual sets, reps, weight and date.
   */

  @DatabaseField(columnName = "ACTUAL_SETS", canBeNull = false)
  private int actualSets;

  @DatabaseField(columnName = "ACTUAL_REPS", canBeNull = false)
  private int actualReps;

  @DatabaseField(columnName = "ACTUAL_WEIGHT", canBeNull = false)
  private int actualWeight;

  @DatabaseField(columnName = "ACTUAL_DATE", canBeNull = false)
  private Date actualDate;


  /** Getter and Setters for ExerciseByDayHistory table fields which consists of:
   *  ID, date, sets, reps and weight. */

  public int getId() {
    return id;
  }

  public int getActualSets() {
    return actualSets;
  }

  public void setActualSets(int actualSets) {
    this.actualSets = actualSets;
  }

  public int getActualReps() {
    return actualReps;
  }

  public void setActualReps(int actualReps) {
    this.actualReps = actualReps;
  }

  public int getActualWeight() {
    return actualWeight;
  }

  public void setActualWeight(int actualWeight) {
    this.actualWeight = actualWeight;
  }

  public Date getActualDate() {
    return actualDate;
  }

  public void setActualDate(Date actualDate) {
    this.actualDate = actualDate;
  }
}
