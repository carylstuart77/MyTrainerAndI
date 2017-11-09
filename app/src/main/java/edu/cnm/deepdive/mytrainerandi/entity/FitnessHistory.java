package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "FITNESS_HISTORY")
public class FitnessHistory {

  @DatabaseField(columnName = "FITNESS_HISTORY_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "HEIGHT", canBeNull = false)
  private int height;

  @DatabaseField(columnName = "WEIGHT", canBeNull = false)
  private double weight;

  @DatabaseField(columnName = "BMI", canBeNull = false)
  private double bmi;

  @DatabaseField(columnName = "INJURY", canBeNull = false)
  private String injury;

  @DatabaseField(columnName = "FAT", canBeNull = false)
  private double fat;

  public int getId() {
    return id;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
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

  public String getInjury() {
    return injury;
  }

  public void setInjury(String injury) {
    this.injury = injury;
  }

  public double getFat() {
    return fat;
  }

  public void setFat(double fat) {
    this.fat = fat;
  }
}

