package eng.soft.schoolfinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import eng.soft.schoolfinder.data.libs.DatabaseHelper;
import eng.soft.schoolfinder.data.libs.SchoolModel;
import eng.soft.schoolfinder.fragments.Fragment_Home;
import eng.soft.schoolfinder.fragments.Fragment_about;
import eng.soft.schoolfinder.fragments.Fragment_finder;
import eng.soft.schoolfinder.fragments.Fragment_k12;
import eng.soft.schoolfinder.fragments.Fragment_schools;
import eng.soft.schoolfinder.fragments.Fragment_tracks;
import eng.soft.schoolfinder.obj.Pref;
import eng.soft.schoolfinder.request.libs.MyRequest;
import eng.soft.schoolfinder.request.libs.MyRequestInterface;

public class Activity_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int selected;
    String schoolURL = MyRequestInterface.HOST_ADDR + "schoolfinder/data/schools.php";
    //Globally Declare Fragments
    Fragment_Home home_display;
    Fragment_k12 k12_display;
    Fragment_finder finder_display;
    Fragment_schools schools_display;
    Fragment_tracks tracks_display;
    Fragment_about about_display;
    FragmentManager mFragmentManager;
    String menuMode;
    Toolbar toolbar;
    SchoolModel schModel;

    JSONObject data;
    MyRequest mRequest;

    public Activity_main() {
        home_display = new Fragment_Home();
        k12_display = new Fragment_k12();
        finder_display = new Fragment_finder();
        schools_display = new Fragment_schools();
        tracks_display = new Fragment_tracks();
        about_display = new Fragment_about();
        mFragmentManager = getFragmentManager();
        menuMode = "normal";
        schModel = new SchoolModel(this);
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
                break;
            case 1: //Finder
                mFragmentTransaction.replace(R.id.frag_container, finder_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("Finder");
                break;
            case 2: //k-12
                mFragmentTransaction.replace(R.id.frag_container, k12_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("The K to 12");
                break;
            case 3: //schools
                mFragmentTransaction.replace(R.id.frag_container, schools_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("Schools");
                break;
            case 4: //tracks
                mFragmentTransaction.replace(R.id.frag_container, tracks_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("K-12 Tracks");
                break;
            case 5: //about
                mFragmentTransaction.replace(R.id.frag_container, about_display);
                mFragmentTransaction.commit();
                toolbar.setTitle("About");
                break;
        }

        if(selected == R.id.nav_schools) {
            menuMode = "school";
        } else {
            menuMode = "normal";
        }
        invalidateOptionsMenu();
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        if(menuMode.equals("normal")) {
            inflater.inflate(R.menu.activity_main, menu);
        }
        else if (menuMode.equals("school")) {
            inflater.inflate(R.menu.activity_main_school, menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case(R.id.action_login):
                Intent intent = new Intent(this, activity_login.class);
                startActivity(intent);
                break;
            case(R.id.action_signup):
                break;
            case(R.id.action_sync):
                syncSchool();
                Fragment_schools.schDetails.clear();
                Fragment_schools.schDetails.addAll(schModel.getAllSchool());
                Fragment_schools.schAdapter.notifyDataSetChanged();
                break;
        }
        return id == R.id.action_login || super.onOptionsItemSelected(item);
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
        } else if (id == R.id.nav_finder) {
            selected = 1;
            mFragmentTransaction.replace(R.id.frag_container, finder_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("Finder");
        } else if (id == R.id.nav_k12) {
            selected = 2;
            mFragmentTransaction.replace(R.id.frag_container, k12_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("The K to 12");
        } else if (id == R.id.nav_schools) {
            selected = 3;
            mFragmentTransaction.replace(R.id.frag_container, schools_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("Schools");
        } else if (id == R.id.nav_tracks) {
            selected = 4;
            mFragmentTransaction.replace(R.id.frag_container, tracks_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("K-12 Tracks");
        }  else if (id == R.id.nav_about) {
            selected = 5;
            mFragmentTransaction.replace(R.id.frag_container, about_display);
            mFragmentTransaction.commit();
            toolbar.setTitle("About");
        }

        if(id == R.id.nav_schools) {
            menuMode = "school";
        } else {
            menuMode = "normal";
        }
        invalidateOptionsMenu();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void syncSchool() {

        data = new JSONObject();
        try {
            try {
                data.put("hi", "hello");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mRequest = new MyRequest(this, new MyRequestInterface() {

                @Override
                public void onComplete(int status, String msg, JSONObject response) {
                    if (status == 1) {
                        System.out.println("AsyncTask Complete");
                        System.out.println("Response is : " + response.toString());

                        try {
                            ContentValues cv = new ContentValues();
                            for(int x = 0; x < response.getInt("total");x++) {
                                cv.put(DatabaseHelper.SCHOOL_ID, response.getJSONArray("schools").getJSONObject(x).getInt("school_id"));
                                cv.put(DatabaseHelper.SCHOOL_NAME, response.getJSONArray("schools").getJSONObject(x).getString("school_name"));
                                cv.put(DatabaseHelper.SCHOOL_TRACKS, response.getJSONArray("schools").getJSONObject(x).getString("school_tracks"));
                                cv.put(DatabaseHelper.SCHOOL_TYPE, response.getJSONArray("schools").getJSONObject(x).getString("school_type"));
                                cv.put(DatabaseHelper.SCHOOL_COURSES, response.getJSONArray("schools").getJSONObject(x).getString("school_courses"));
                                cv.put(DatabaseHelper.SCHOOL_ADDRESS, response.getJSONArray("schools").getJSONObject(x).getString("school_address"));
                                cv.put(DatabaseHelper.SCHOOL_CONTACT, response.getJSONArray("schools").getJSONObject(x).getString("school_contact"));
                                cv.put(DatabaseHelper.SCHOOL_WEBSITE, response.getJSONArray("schools").getJSONObject(x).getString("school_website"));
                                cv.put(DatabaseHelper.SCHOOL_READY, response.getJSONArray("schools").getJSONObject(x).getString("school_k12ready"));
                                schModel.addSchool(cv);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "There is something with the request. " + msg, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mRequest.execute(schoolURL, data.toString());
        }
        catch(Exception e) {
            System.out.print(e.toString());
        }
    }
}
