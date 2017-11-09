package edu.cnm.deepdive.mytrainerandi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

// Helper to SQLite to open up and work with sql lite db.
public class DatabaseHelper extends SQLiteOpenHelper {

  public static final String DATABASE_NAME = "LIST_CONTENT";
  public static final String DATABASE_TABLE = "LIST_TABLE";

  //Set the DB version; if you add changes like new dataset (columns) without deleting the old table,
  // you need to change the version number; it is an arbitrary number.
  public static final int DATABASE_VERSION = 1;

  public static final String ID_COLUMN = "_id";
  public static final String NAME_COLUMN = "name";
  public static final String GOAL_COLUMN = "goal";
  public static final String ENTRYDATE_COLUMN = "entrydate";
  public static final String WEIGHT_COLUMN = "weight";
  public static final String HEIGHT_COLUMN = "height";
  public static final String BMI_COLUMN = "bmi";

  //public static final String CONTENT_COLUMN = "content";
  // public static final String COLOR_COLUMN = "color";

//  private static final String SCRIPT_CREATE_DATABASE =
//      "CREATE TABLE " + DATABASE_TABLE + " ("
//          + ID_COLUMN + " integer primary key autoincrement, "
//          + CONTENT_COLUMN + " text not null,"
//          + COLOR_COLUMN + " text not null);";

  private static final String SCRIPT_CREATE_DATABASE =
               "CREATE TABLE " + DATABASE_TABLE + " ("
                   + ID_COLUMN + " integer primary key autoincrement, "
                   + NAME_COLUMN + " text NOT NULL,\n"
                   + GOAL_COLUMN + " text NOT NULL,\n"
                   + ENTRYDATE_COLUMN + " datetime NOT NULL,\n"
                   + WEIGHT_COLUMN + " double NOT NULL,\n"
                   + HEIGHT_COLUMN + " double NOT NULL,\n"
                   + BMI_COLUMN + " double NOT NULL\n"
                   + ");";

  //Constructor: what class you are in and where you will put it on computer disk
  public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME+".db, ", null, DATABASE_VERSION );

  }
  //execute sql script
  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(SCRIPT_CREATE_DATABASE);
    //FF is the red component; Red, Green, Blue
//    insert(sqLiteDatabase, "This Should be Red", "#FF0000");
//    insert(sqLiteDatabase, "This Should be Light Green", "#FFFF01");


    //insert(sqLiteDatabase, 123, 32, caryl,

//ID_COLUMN, _id);
//NAME_COLUMN, name);
//GOAL_COLUMN, goal);
//ENTRYDATE_COLUMN, entrydate);
//WEIGHT_COLUMN, weight);
//HEIGHT_COLUMN, height);
//BMI_COLUMN, bmi);

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
  //Will create a sql Insert statement; Only being inserted and not being deleted by hacker.
  public  long insert
    (SQLiteDatabase db, String _id, String name, String goal, String entrydate, String weight, String height, String bmi) {
    ContentValues contentValues = new ContentValues();

    contentValues.put(ID_COLUMN, _id);
    contentValues.put(NAME_COLUMN, name);
    contentValues.put(GOAL_COLUMN, goal);
    contentValues.put(ENTRYDATE_COLUMN, entrydate);
    contentValues.put(WEIGHT_COLUMN, weight);
    contentValues.put(HEIGHT_COLUMN, height);
    contentValues.put(BMI_COLUMN, bmi);

//    contentValues.put(COLOR_COLUMN, color);
    return db.insert(DATABASE_TABLE, null, contentValues);
  }
}
