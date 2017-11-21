package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

@DatabaseTable(tableName = "FITNESS_HISTORY")
public class FitnessHistory {

  @DatabaseField(columnName = "FITNESS_HISTORY_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "WEIGHT", canBeNull = false)
  private double weight;

  @DatabaseField(columnName = "BMI", canBeNull = false)
  private double bmi;

  @DatabaseField(columnName = "FAT"/*, canBeNull = false*/)
  private double fat;

  @DatabaseField(columnName = "GOAL"/*, canBeNull = false*/)
  private String goal;

  public int getId() {
    return id;
  }

  public Timestamp getCreated() {
    return created;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getBmi() {
    return bmi;
  }

  public void setBmi(double bmi) {
    this.bmi = bmi;
  }

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  public double getFat() {
    return fat;
  }

  public void setFat(double fat) {
    this.fat = fat;
  }


}

