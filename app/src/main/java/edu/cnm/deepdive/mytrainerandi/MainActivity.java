package edu.cnm.deepdive.mytrainerandi;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper;
import edu.cnm.deepdive.mytrainerandi.helpers.OrmHelper.OrmInteraction;

/**
 * Activity implementing navigation view on Android devices.  Data fields are implemented by ORM
 * interaction sql queries.
 *
 * @version 1.0, 2017-11-24
 * @author: Caryl Stuart
 * @contributors: Nick Bennett and Chris Hughes
 */

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, OrmInteraction {

  public DatabaseHelper dbHelper;
  private ListView databaseListView;
  private SQLiteDatabase sqLiteDatabase;
  private OrmHelper helper = null;

  EditText editTextName;
  Spinner spinnerGoal;

  /**
   * onCreate is called when the activity is starting. This is where most initialization will
   * happen. Inflate the main navigation tool bar for main drawer selections.
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

    displaySelectedScreen(R.id.nav_home);

    spinnerGoal = (Spinner) findViewById(R.id.spinnerGoal);
  }

  @Override
  protected void onStart() {
    super.onStart();
    getHelper();
  }

  @Override
  protected void onStop() {
    releaseHelper();
    super.onStop();
  }

  /**
   * Creating an instance of the OrmHelper in preparation for database use. Synchronizing threads to
   * block additional threads until the thread inside the block exits.
   */
  public synchronized OrmHelper getHelper() {
    if (helper == null) {
      helper = OpenHelperManager.getHelper(this, OrmHelper.class);
    }
    return helper;
  }

  /**
   * Release helper is called to shutdown Open helper class. This prevents memory leaks by setting
   * helper to null when not in use.
   */
  public synchronized void releaseHelper() {
    if (helper != null) {
      OpenHelperManager.releaseHelper();
      helper = null;
    }
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

  //LayoutInflater is one of the Android System Services that is responsible
  // for taking your XML files that define a layout, and converting them into View objects.

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  // Handle action bar item clicks here. The action bar will
  // automatically handle clicks on the Home/Up button, so long
  // as you specify a parent activity in AndroidManifest.xml.

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * Main navigation selection bar which includes pages for: welcome, daily workouts, main trainer
   * list of exercises to pick from and the client log with graphing. After nav bar selection is
   * picked, replace content_main with the selection and run new screen fragment.
   */
  private void displaySelectedScreen(int id) {

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
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
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
