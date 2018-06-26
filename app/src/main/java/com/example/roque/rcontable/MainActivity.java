package com.example.roque.rcontable;

import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roque.rcontable.fragment.GastosFragment;
import com.example.roque.rcontable.fragment.InicioFragment;
import com.example.roque.rcontable.fragment.PrestamosFragment;
import com.example.roque.rcontable.interfaces.OnBackPressedListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GastosFragment.OnFragmentInteractionListener, InicioFragment.OnFragmentInteractionListener, PrestamosFragment.OnFragmentInteractionListener {


    private static final int INTERVALO = 3000; //2 segundos para salir
    private long tiempoPrimerClick;

    Fragment miFragment;

    DrawerLayout drawer;
    NavigationView navigationView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new InicioFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean isFragment = false;

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            miFragment = new InicioFragment();
            isFragment=true;
        } else if (id == R.id.nav_gastos) {
            miFragment = new GastosFragment();
            isFragment=true;

        } else if (id == R.id.nav_prestamo) {
            miFragment = new PrestamosFragment();
            isFragment=true;

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if(isFragment==true){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, miFragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
            //super.onBackPressed();
            finish();
            return;
        }else {
            Toast.makeText(getApplicationContext(), "Presionar dos veces para salir", Toast.LENGTH_SHORT).show();
            // cargarwebservice();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

