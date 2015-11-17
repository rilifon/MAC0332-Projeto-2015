package eda.budgetchef;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, PageSelectedListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Log.w("MyActivity", "Testing");

    if (findViewById(R.id.page_content) != null) {
      if (savedInstanceState != null)
        return;

      RecommendedPage recPage = new RecommendedPage();
      recPage.setArguments(getIntent().getExtras());

      getFragmentManager().beginTransaction().add(R.id.page_content, recPage).commit();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
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
    if (id == R.id.top_create) {
      goToCreate(item);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.

    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    Fragment nextPage = null;

    Log.w("Checking MenuItem", "Item: " + item);

    switch(item.getItemId()) {
      case R.id.nav_fridge:
        nextPage = new FridgePage();
        break;
      case R.id.nav_search:
        nextPage = new SearchPage();
        break;
      case R.id.nav_create:
        nextPage = new CreatePage();
        break;
      case R.id.nav_recommended:
        nextPage = new RecommendedPage();
        break;
      case R.id.nav_favorites:
        nextPage = new FavoritesPage();
        break;
      case R.id.nav_profile:
        nextPage = new ProfilePage();
        break;
      case R.id.nav_settings:
        nextPage = new SettingsPage();
        break;
    }

    if (nextPage == null) {
      Log.w("MainActivity", "Error 404: Page not found.");
      return false;
    }

    transaction.replace(R.id.page_content, nextPage);
    transaction.addToBackStack(null);
    transaction.commit();

    item.setChecked(true);
    setTitle(item.getTitle());

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /** Action CreateRecipe on AppBar. */
  public void goToCreate(MenuItem item) {
    Log.w("App Bar", "Go to Create Recipe Page.");
  }

  /** Listens to interaction on each Fragment (Page). */
  public void onPageInteraction(String id) {
    Log.w("MainActivity", "ID: " + id);
  }
}
