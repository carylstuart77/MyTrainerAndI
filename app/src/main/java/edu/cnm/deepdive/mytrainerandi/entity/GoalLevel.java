package edu.cnm.deepdive.mytrainerandi.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "GOAL_LEVEL")
public class GoalLevel {

  @DatabaseField(columnName = "GOAL_LEVEL_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  //Can be stored in another table.  Foreign Key
  @DatabaseField(canBeNull = false, foreign = true)
  private Goal goal;

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
