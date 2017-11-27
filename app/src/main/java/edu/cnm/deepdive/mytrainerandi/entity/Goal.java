package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Configure GOAL class to be persisted db table.
 */

@DatabaseTable(tableName = "GOAL")
public class Goal {

  /**
   * The name field is configured as the primary key for the database table by using the generatedId
   * = true annotation field.
   */
  @DatabaseField(columnName = "GOAL_ID", generatedId = true)
  private int id;
  /**
   * Create db fields for GOAL table.
   */
  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  //** Getter and Setters for Goal table fields to be used from client4 goal selection. */
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
