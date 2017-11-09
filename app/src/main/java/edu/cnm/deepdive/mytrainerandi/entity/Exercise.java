package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EXERCISE")
public class Exercise {

  @DatabaseField(columnName = "EXERCISE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }
}
