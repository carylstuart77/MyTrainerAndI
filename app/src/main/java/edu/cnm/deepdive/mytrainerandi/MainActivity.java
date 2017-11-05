package edu.cnm.deepdive.mytrainerandi;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Fragment;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  //Generate unique id and date to be kept in database for traceability.
  public class ClientSetup {

    private UUID mId;
    private Date mDate;

    public ClientSetup() {
      mId = UUID.randomUUID();
      mDate = new Date();
    }

    // Getter for Unique ID
    public UUID getId() {
      return mId;
    }

    // Getters and Setters for Date
    public Date getDate() {
      return mDate;
    }

    public void setDate(Date date) {
      mDate = date;
    }
  }


  //SQLite setup
  public static final String DATABASE_NAME = "mydatabase";
  SQLiteDatabase mDatabase;

  EditText editTextName;
  Spinner spinnerGoal;

  /**
   * On Create
   */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    /**
     * SQLite Database
     */
    mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
    createTable();
    spinnerGoal = (Spinner) findViewById(R.id.spinnerGoal);
  }

  private void createTable() {
    String sql = "CREATE TABLE client (\n"
        + "    mId INTEGER NOT NULL CONSTRAINT client_pk PRIMARY KEY AUTOINCREMENT,\n"
        + "    name varchar(200) NOT NULL,\n"
        + "    goal varchar(200) NOT NULL,\n"
        + "    entrydate datetime NOT NULL,\n"
        + "    weight double NOT NULL,\n"
        + "    height double NOT NULL,\n"
        + "    bmi double NOT NULL\n"
        + ");";
    mDatabase.execSQL(sql);
  }


  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void displaySelectedScreen(int id) {
    //FragmentManager fragment = getSupportFragmentManager();

    Fragment fragment = null;
    id = getIntent().getIntExtra("position", id);

    switch (id) {
      case R.id.nav_home:
        fragment = new Home1();
        break;
      case R.id.nav_day:
        fragment = new Day2();
        break;
      case R.id.nav_trainer:
        fragment = new Trainer3();
        break;
      case R.id.nav_client:
        fragment = new Client4();
        break;
    }
    if (fragment != null) {
      android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
      ft.replace(R.id.content_main, fragment);
      ft.commit();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    displaySelectedScreen(id);

    return true;
  }
}
