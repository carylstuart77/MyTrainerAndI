package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EXERCISE")
public class Exercise {

  @DatabaseField(columnName = "EXERCISE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CIRCUIT"/*, canBeNull = false*/)
  private String circuit;

  @DatabaseField(columnName = "MUSCLE"/*, canBeNull = false*/)
  private String muscle;

  @DatabaseField(columnName = "EXERCISENAME"/*, canBeNull = false*/)
  private String exercisename;

  public int getId() {
    return id;
  }

  public String getName() {
    return circuit;
  }

  public String getCircuit() {
    return circuit;
  }

  public void setCircuit(String circuit) {
    this.circuit = circuit;
  }

  public String getMuscle() {
    return muscle;
  }

  public void setMuscle(String muscle) {
    this.muscle = muscle;
  }

  public String getExercisename() {
    return exercisename;
  }

  public void setExercisename(String exercisename) {
    this.exercisename = exercisename;
  }
}
