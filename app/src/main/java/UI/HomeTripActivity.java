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

import com.example.SaveSharedPreference;
import com.example.trippalnner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import UI.history.HistoryFragment;


public class HomeTripActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null)
        {
            Intent goToLogin = new Intent(HomeTripActivity.this, LoginActivity.class);
            startActivity(goToLogin);

        }
        if(mAuth.getCurrentUser()!=null)
        {
//            Toast.makeText(HomeTripActivity.this,mAuth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();

        }
    }

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
        navHeader = navigationView.getHeaderView(0);

        email = navHeader.findViewById(R.id.nav_header_email);
        email.setText(cUser.getEmail());
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HistoryFragment()).commit();

                break;
            case R.id.nav_backup:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BackupFragment()).commit();

                break;
            
            case R.id.nav_async:
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(cUser.getUid());
                ref.keepSynced(true);
                break;
            case R.id.nav_logout:
                SaveSharedPreference.setFlag(HomeTripActivity.this,0);
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeTripActivity.this, LoginActivity.class);
                startActivity(intent);
                HomeTripActivity.this.finish();
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
