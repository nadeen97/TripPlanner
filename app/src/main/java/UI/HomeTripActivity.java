package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.trippalnner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import UI.history.HistoryFragment;
import UI.home.HomeFragment;

public class HomeTripActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {



    private DrawerLayout drawerLayout;
    TextView email;
    View navHeader;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseUser cUser=mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        /*navHeader = navigationView.getHeaderView(0);

        email = navHeader.findViewById(R.id.emailText);
        email.setText(cUser.getEmail());*/
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState== null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AddTripActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:

               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new HistoryFragment()).commit();
                break;
                case R.id.nav_backup:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new BackupFragment()).commit();
                break;
                case R.id.nav_async:
                Toast.makeText(this,"async",Toast.LENGTH_LONG).show();
                break;
                case R.id.nav_logout:
                Toast.makeText(this,"logout",Toast.LENGTH_LONG).show();

                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {//will close
            super.onBackPressed();


        }


}}