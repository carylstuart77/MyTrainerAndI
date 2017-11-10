package edu.cnm.deepdive.mytrainerandi.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

//Object Relational Mapping (ORM DB)

@DatabaseTable(tableName = "CLIENT")
public class Client {

  @DatabaseField(columnName = "CLIENT_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

//  @DatabaseField(columnName = "GOAL", canBeNull = false)
//  private String goal;
  @DatabaseField(columnName = "GOAL")
   private String goal;

  @DatabaseField(columnName = "LEVEL"/*, canBeNull = false*/)
  private String level;

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

  public void setGoal(String goal) {
    this.goal = goal;
  }

  public void getGoal(String goal) {
    this.goal = goal;
  }


  public void setLevel(String level ) {
    this.level = level;
  }

  public void getLevel(String level ) {
    this.level = level;
  }

  //returns the information
//  @Override
//  public String toString() {
//    Map<String, Object> map = new HashMap<>();
//    map.put("id", id);
//    map.put("name", name);
//    map.put("created", created);
//    return map.toString();
//  }
}
