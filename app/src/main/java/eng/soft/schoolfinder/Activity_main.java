package eng.soft.schoolfinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import eng.soft.schoolfinder.fragments.Fragment_Home;
import eng.soft.schoolfinder.fragments.Fragment_about;
import eng.soft.schoolfinder.fragments.Fragment_finder;
import eng.soft.schoolfinder.fragments.Fragment_k12;
import eng.soft.schoolfinder.fragments.Fragment_schools;
import eng.soft.schoolfinder.fragments.Fragment_tracks;
import eng.soft.schoolfinder.obj.Pref;

public class Activity_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Globally Declare Fragments
    Fragment_Home home_display;
    Fragment_k12 k12_display;
    Fragment_finder finder_display;
    Fragment_schools schools_display;
    Fragment_tracks tracks_display;
    Fragment_about about_display;
    FragmentManager mFragmentManager;

    public static int selected;

    Toolbar toolbar;

    public Activity_main() {
        home_display = new Fragment_Home();
        k12_display = new Fragment_k12();
        finder_display = new Fragment_finder();
        schools_display = new Fragment_schools();
        tracks_display = new Fragment_tracks();
        about_display = new Fragment_about();
        mFragmentManager = getFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        FragmentTransaction initTransaction = mFragmentManager.beginTransaction();
        initTransaction.replace(R.id.frag_container, home_display);
        initTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        selected = Pref.readFromPreferences(this,Pref.KEY_SELECTED_OPTION,0);

        switch (selected) {
            case 0: //Home
                mFragmentTransaction.replace(R.id.frag_container, home_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("Home");
                getSupportActionBar().show();
                break;
            case 1: //Finder
                mFragmentTransaction.replace(R.id.frag_container, finder_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("Finder");
                getSupportActionBar().hide();
                break;
            case 2: //k-12
                mFragmentTransaction.replace(R.id.frag_container, k12_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("The K to 12");
                getSupportActionBar().show();
                break;
            case 3: //schools
                mFragmentTransaction.replace(R.id.frag_container, schools_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("Schools");
                getSupportActionBar().show();
                break;
            case 4: //tracks
                mFragmentTransaction.replace(R.id.frag_container, tracks_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("K-12 Tracks");
                getSupportActionBar().show();
                break;
            case 5: //about
                mFragmentTransaction.replace(R.id.frag_container,about_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("About");
                getSupportActionBar().show();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Pref.saveToPreferences(this,Pref.KEY_SELECTED_OPTION,selected);
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
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        if (id == R.id.nav_home) {
            selected = 0;
            mFragmentTransaction.replace(R.id.frag_container, home_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("Home");
            getSupportActionBar().show();
        } else if (id == R.id.nav_finder) {
            selected = 1;
            mFragmentTransaction.replace(R.id.frag_container, finder_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("Finder");
            getSupportActionBar().hide();
        } else if (id == R.id.nav_k12) {
            selected = 2;
            mFragmentTransaction.replace(R.id.frag_container, k12_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("The K to 12");
            getSupportActionBar().show();
        } else if (id == R.id.nav_schools) {
            selected = 3;
            mFragmentTransaction.replace(R.id.frag_container, schools_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("Schools");
            getSupportActionBar().show();
        } else if (id == R.id.nav_tracks) {
            selected = 4;
            mFragmentTransaction.replace(R.id.frag_container, tracks_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("K-12 Tracks");
            getSupportActionBar().show();
        } else if (id == R.id.nav_about) {
            selected = 5;
            mFragmentTransaction.replace(R.id.frag_container, about_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("About");
            getSupportActionBar().show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
