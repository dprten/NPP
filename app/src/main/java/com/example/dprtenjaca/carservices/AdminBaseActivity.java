package com.example.dprtenjaca.carservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AdminBaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String NAV_MENU_BUNDLE_KEY = "NAV_MENU_ITEM";

    NavigationView navigationView;

    Toolbar toolbar;

    //stack for selected item index in navigation drawer. Used for situation when back button is pressed and
    //previous item in navigation drawer needs to be selected.
    static List<Integer> navDrawerStack = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int navMenuItem = 0;
        if(getIntent().getExtras() != null) {
            navMenuItem = getIntent().getExtras().getInt(NAV_MENU_BUNDLE_KEY);
        }

        setCheckedMenuItem(navMenuItem);
    }

    private void setCheckedMenuItem(int navMenuItem) {
        navDrawerStack.add(navMenuItem);
        navigationView.getMenu().getItem(navMenuItem).setChecked(true);
    }

    protected void setToolbarBack() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

        navDrawerStack.remove(navDrawerStack.size() - 1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        int index = navDrawerStack.get(navDrawerStack.size() - 1);
        navigationView.getMenu().getItem(index).setChecked(true);
        super.onResume();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_admin_users) {
            Intent usersIntent = new Intent(getApplicationContext(), AdminUsersActivity.class);
            usersIntent.putExtra(NAV_MENU_BUNDLE_KEY, 0);
            startActivity(usersIntent);
        } else if (id == R.id.nav_admin_car_services) {
            Intent carServicesIntent = new Intent(getApplicationContext(), AdminCarServicesActivity.class);
            carServicesIntent.putExtra(NAV_MENU_BUNDLE_KEY, 1);
            startActivity(carServicesIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
