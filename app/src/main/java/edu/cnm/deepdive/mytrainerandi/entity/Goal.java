package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "GOAL")
public class Goal {

  @DatabaseField(columnName = "GOAL_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

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
