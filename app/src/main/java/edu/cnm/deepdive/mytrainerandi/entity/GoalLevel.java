package edu.cnm.deepdive.mytrainerandi.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Configure GOAL_LEVEL class to be persisted db table.
 */
@DatabaseTable(tableName = "GOAL_LEVEL")
public class GoalLevel {

  /**
   * The name field is configured as the primary key for the database table by using the generatedId
   * = true annotation field.
   */

  @DatabaseField(columnName = "GOAL_LEVEL_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  //Can be stored in another table.  Foreign Key
  @DatabaseField(canBeNull = false, foreign = true)
  private Goal goal;

  //** Getter and Setters for GOAL_LEVEL table fields. */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Goal getGoal() {
    return goal;
  }

  public void setGoal(Goal goal) {
    this.goal = goal;
  }

  public int getId() {
    return id;
  }
}
