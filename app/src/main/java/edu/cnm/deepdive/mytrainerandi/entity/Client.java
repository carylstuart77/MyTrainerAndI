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

  @DatabaseField(columnName = "HEIGHT", canBeNull = false)
  private int height;

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
