package edu.cnm.deepdive.mytrainerandi.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

/**
 * Configure Client class to be persisted db table.
 */

@DatabaseTable(tableName = "CLIENT")
public class Client {

  /**
   * The name field is configured as the primary key for the database table by using the
   * generatedId = true annotation field.
   */
  @DatabaseField(columnName = "CLIENT_ID", generatedId = true)
  private int id;

  /**  Create db fields for Client table.*/
  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyyy-MM-dd", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  @DatabaseField(columnName = "HEIGHT", canBeNull = false)
  private int height;

  //** Getter and Setters for Client table fields. */
  public int getId() {
    return id;
  }

  public Timestamp getCreated() {
    return created;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

}
