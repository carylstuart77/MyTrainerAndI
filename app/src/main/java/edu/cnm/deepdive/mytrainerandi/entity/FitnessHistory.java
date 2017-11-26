package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

/**
 * Configure FITNESS_HISTORY class to be persisted db table.
 */
@DatabaseTable(tableName = "FITNESS_HISTORY")
public class FitnessHistory {

  /**
   * The name field is configured as the primary key for the database table by using the generatedId
   * = true annotation field.
   */
  public static final String CLIENT_ID = "CLIENT_ID";

  @DatabaseField(columnName = "FITNESS_HISTORY_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = CLIENT_ID, foreign = true, foreignAutoRefresh = true, index = true, canBeNull = false)
  private Client client;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyyy-MM-dd", canBeNull = false, readOnly = true)
  private Timestamp created;

  /**
   * Create db fields for FITNESS_HISTORY table.
   */
  @DatabaseField(columnName = "WEIGHT", canBeNull = false)
  private double weight;

  @DatabaseField(columnName = "BMI", canBeNull = false)
  private double bmi;

  @DatabaseField(columnName = "FAT"/*, canBeNull = false*/)
  private double fat;

  @DatabaseField(columnName = "GOAL"/*, canBeNull = false*/)
  private String goal;

  //** Getter and Setters for FITNESS_HISTORY table fields. */

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

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}

