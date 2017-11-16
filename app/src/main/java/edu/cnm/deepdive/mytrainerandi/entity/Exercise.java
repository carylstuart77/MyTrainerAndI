package edu.cnm.deepdive.mytrainerandi.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EXERCISE")
public class Exercise {

  public static final String CIRCUIT_COLNAME = "CIRCUIT";
  public static final String MUSCLE_COLNAME = "MUSCLE";
  public static final String EXERCISE_COLNAME = "EXERCISENAME";


  @DatabaseField(columnName = "EXERCISE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = CIRCUIT_COLNAME/*, canBeNull = false*/)
  private String circuit;

  @DatabaseField(columnName = MUSCLE_COLNAME/*, canBeNull = false*/)
  private String muscle;

  @DatabaseField(columnName = EXERCISE_COLNAME/*, canBeNull = false*/)
  private String exercisename;

  public int getId() {
    return id;
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

  @Override
  public String toString() {
    return exercisename;
  }
}
