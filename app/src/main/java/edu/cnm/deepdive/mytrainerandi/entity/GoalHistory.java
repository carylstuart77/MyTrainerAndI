package edu.cnm.deepdive.mytrainerandi.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Configure GOAL_HISTORY class to be persisted db table.
 */
@DatabaseTable(tableName = "GOAL_HISTORY")
public class GoalHistory {

  /** Create constant values in order to capture value of muscle, exercisename, circuit,
   * sets, reps and lbs. */

  public static final String HMUSCLE_COLNAME = "HISTORY_MUSCLE";
  public static final String HEXNAME_COLNAME = "HISTORY_EXERCISE_NAME";
  public static final String HCIRCUIT_COLNAME = "HISTORY_CIRCUIT";
  public static final String HSETS_COLNAME = "HISTORY_SETS";
  public static final String HREPS_COLNAME = "HISTORY_REPS";
  public static final String HLBS_COLNAME = "HISTORY_LBS";

  /**  Create db fields for GOAL_HISTORY table.*/
  @DatabaseField(columnName = HEXNAME_COLNAME /*, canBeNull = false*/)
  private String hexname;

  @DatabaseField(columnName = HCIRCUIT_COLNAME /*, canBeNull = false*/)
  private String hcircuit;

  @DatabaseField(columnName = HMUSCLE_COLNAME /*, canBeNull = false*/)
  private String hmuscle;

  @DatabaseField(columnName = HSETS_COLNAME /*, canBeNull = false*/)
  private String hsets;

  @DatabaseField(columnName = HREPS_COLNAME /*, canBeNull = false*/)
  private String hreps;

  @DatabaseField(columnName = HLBS_COLNAME /*, canBeNull = false*/)
  private String hlbs;

  //** Getter and Setters for GOAL_HISTORY table fields. */

  public String getHexname() {
    return hexname;
  }

  public String getHcircuit() {
    return hcircuit;
  }

  public String getHmuscle() {
    return hmuscle;
  }

  public String getHsets() {
    return hsets;
  }

  public String getHreps() {
    return hreps;
  }

  public String getHlbs() {
    return hlbs;
  }
}
