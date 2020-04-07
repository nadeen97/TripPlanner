package UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trippalnner.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Calendar;

import Code.Database;
import Code.DateTimePickers;
import Code.Distance;
import Code.MapLauncher;
import Code.PlaceApi;
import Adpters.PlacesAutoSuggestAdapter;
import Code.Toasting;
import POJOs.Trip;
import reminder.AlermReciever;
import reminder.AuthenticationCheckService;


public class AddTripActivity extends AppCompatActivity  {

    private final String APIKey = "AIzaSyCudlTIHtQyuZ7-6l7Gz9-Nb_0P8Ehyjdc";
    private TextView startDate, startTime, textViewAddNotes, textViewSupposedDirections, textViewAddTrip,
            textViewTripName, textViewFrom, textViewStartDate, textViewTo, textViewStartTime, textViewDescription;

    //private TextView textViewDuration, textViewDistance, txtDuration, txtDistance;
    private EditText tripName;
    private EditText description;
    private AutoCompleteTextView startLocation;
    private AutoCompleteTextView destination;
    private Button btnAddNote;
    private Button btnAddTrip;
    private Button btnMaps;
    private Spinner spinnerRepeat;
    private Spinner spinnerRound;
    private DatePickerDialog datePickerDialog;
    private String repeatStringValue;
    private String roundStringValue;
    private PlaceApi placeApi;
    private Button btnMenu;
    private Boolean isOpen = false;
    private Animation fabOpen, fabClosed;
    private Database database;
    private Trip addedTrip;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_addt_trip);
           uid = getIntent().getStringExtra("uid");
        database = new Database();


        //Misc views
        textViewTripName = findViewById(R.id.textViewTripName);
        textViewFrom = findViewById(R.id.textViewStartLocation);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewStartTime = findViewById(R.id.textViewStartTime);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewTo = findViewById(R.id.textViewTo);
        description = findViewById(R.id.description);
        tripName = findViewById(R.id.tripName);
/*
        textViewDuration = findViewById(R.id.textViewDuration);
        textViewDistance = findViewById(R.id.textViewDistance);
        txtDuration = findViewById(R.id.txtDuration);
        txtDistance = findViewById(R.id.txtDistance);


 */

//------------------------------------------------------------


        textViewTripName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Log.d("Debug", "onClick: trip name");
                tripName.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(AddTripActivity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(tripName, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        textViewFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocation.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(AddTripActivity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(startLocation, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        textViewTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(AddTripActivity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(destination, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        textViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(AddTripActivity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(description, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        textViewStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startDate.requestFocus();
                startDate.performClick();
            }
        });

        textViewStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // textViewStartTime.requestFocus();
                startTime.performClick();
            }
        });


        //--------------------------------------------------------------

        //Animation XML files

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClosed = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        //Text views in FAM
        textViewAddNotes = findViewById(R.id.textViewAddNotes);
        textViewAddTrip = findViewById(R.id.textViewAddTrip);
        textViewSupposedDirections = findViewById(R.id.textViewSupposedDirections);

        //FAM button
        btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {

/*
                    txtDistance.setAnimation(fabClosed);
                    textViewDistance.setAnimation(fabClosed);
                    txtDuration.setAnimation(fabClosed);
                    textViewDuration.setAnimation(fabClosed);

                    txtDistance.setVisibility(View.INVISIBLE);
                    textViewDistance.setVisibility(View.INVISIBLE);
                    txtDuration.setVisibility(View.INVISIBLE);
                    textViewDuration.setVisibility(View.INVISIBLE);


 */

                    btnAddTrip.setAnimation(fabClosed);
                    btnAddTrip.setVisibility(View.INVISIBLE);

                    btnMaps.setAnimation(fabClosed);
                    btnMaps.setVisibility(View.INVISIBLE);

                    btnAddNote.setAnimation(fabClosed);
                    btnAddNote.setVisibility(View.INVISIBLE);

                    textViewAddNotes.setAnimation(fabClosed);
                    textViewAddNotes.setVisibility(View.INVISIBLE);

                    textViewAddTrip.setAnimation(fabClosed);
                    textViewAddTrip.setVisibility(View.INVISIBLE);

                    textViewSupposedDirections.setAnimation(fabClosed);
                    textViewSupposedDirections.setVisibility(View.INVISIBLE);


                    isOpen = false;
                } else {


                    btnAddTrip.setAnimation(fabOpen);
                    btnAddTrip.setVisibility(View.VISIBLE);

                    btnMaps.setAnimation(fabOpen);
                    btnMaps.setVisibility(View.VISIBLE);

                    btnAddNote.setAnimation(fabOpen);
                    btnAddNote.setVisibility(View.VISIBLE);

                    textViewAddNotes.setAnimation(fabOpen);
                    textViewAddNotes.setVisibility(View.VISIBLE);

                    textViewAddTrip.setAnimation(fabOpen);
                    textViewAddTrip.setVisibility(View.VISIBLE);

                    textViewSupposedDirections.setAnimation(fabOpen);
                    textViewSupposedDirections.setVisibility(View.VISIBLE);


                    isOpen = true;
                }
            }
        });

        //Maps
        btnMaps = findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!startLocation.getText().toString().isEmpty() && !startDate.getText().toString().isEmpty() && !destination.getText().toString().isEmpty()) {

                    //launchMaps(startLocation.getText().toString(), destination.getText().toString());

                    MapLauncher.launchMapsWithStartLocation(AddTripActivity.this, startLocation.getText().toString(), destination.getText().toString());
                }
            }
        });


        //Auto completion, Google places API

        placeApi = new PlaceApi();

       // placeApi.getCurrentLocation(this);


        if (!Places.isInitialized()) {
            // Initialize the SDK
            Places.initialize(getApplicationContext(), APIKey);
        }
        // Create a new Places client instance
        PlacesClient placesClient = Places.createClient(this);

        startLocation = findViewById(R.id.startLocation);
        destination = findViewById(R.id.destination);

        startLocation.setAdapter(new PlacesAutoSuggestAdapter(this, android.R.layout.simple_list_item_1));
        destination.setAdapter(new PlacesAutoSuggestAdapter(this, android.R.layout.simple_list_item_1));


        //Date & time pickers

        startDate = findViewById(R.id.startDate);
        startTime = findViewById(R.id.startTime);
        final DateTimePickers datePicker = new DateTimePickers(this);

        datePicker.getDate(startDate);
        datePicker.getTime(startTime);


        //Add notes activity

        btnAddNote = findViewById(R.id.addNotes);
        btnAddNote.setEnabled(false);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(AddTripActivity.this, NotesActivity.class);
                i.putExtra("nodeID", database.getRecordId());
               // Log.d("Debug", database.getRecordId());
                startActivity(i);
            }
        });

        //Spinners

        spinnerRepeat = findViewById(R.id.spinnerRepeat);
        spinnerRound = findViewById(R.id.spinnerRound);

        //Repeat Spinner

        ArrayAdapter<CharSequence> repeateAadapter = ArrayAdapter.createFromResource(this, R.array.repeat, android.R.layout.simple_spinner_item);
        repeateAadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRepeat.setAdapter(repeateAadapter);

        spinnerRepeat.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                repeatStringValue = parent.getItemAtPosition(position).toString();
               // Toast.makeText(AddTripActivity.this, repeatStringValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Repeat Spinner

        ArrayAdapter<CharSequence> RoundAdapter = ArrayAdapter.createFromResource(this, R.array.round, android.R.layout.simple_spinner_item);
        RoundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRound.setAdapter(RoundAdapter);
        spinnerRound.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                roundStringValue = parent.getItemAtPosition(position).toString();
                // Toast toast = Toast.makeText(MainActivity.this, roundStringValue, Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAddTrip = findViewById(R.id.btnAddTrip);

        btnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFilled()) {
             //   Log.d("Debug", "onClick: Add trip");
                            final String tripNameE = tripName.getText().toString();
                            final String startLocationString = startLocation.getText().toString();
                           // final Double startLocLat = placeApi.getCoordinatesFromAddress(AddTripActivity.this, startLocation.getText().toString()).latitude;
                            // final Double startLocLong = placeApi.getCoordinatesFromAddress(AddTripActivity.this, startLocation.getText().toString()).longitude;

                            final String destinationString = destination.getText().toString();
                            //final Double destinationLat = placeApi.getCoordinatesFromAddress(AddTripActivity.this, destination.getText().toString()).latitude;
                            // final Double destinationLong = placeApi.getCoordinatesFromAddress(AddTripActivity.this, destination.getText().toString()).longitude;

                            final String startDateE = startDate.getText().toString();

                            final String startTimeE = startTime.getText().toString();
                            final String descriptionN = description.getText().toString();
                            final String repeat = repeatStringValue;
                            final String round = roundStringValue;
                            final String id = "";
                            final Trip newTrip = new Trip(id, tripNameE, startLocationString,
                                   destinationString, startDateE, startTimeE, descriptionN,
                                    repeat, round,"0 km", "0 hr 0 min");
                if(roundStringValue.equalsIgnoreCase("One way")){

                    new Thread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void run() {
                            //upcomming trip

                            ArrayList<String > list= new ArrayList<>();
                           list =  Distance.getSpaceTime(startLocationString, destinationString);
                           newTrip.setDistance(list.get(0));
                           //txtDistance.setText(list.get(0));
                           newTrip.setDuration(list.get(1));
                           //txtDuration.setText(list.get(1));
                            database.addTripToDataBase(AddTripActivity.this, newTrip);

                            Log.i("id",newTrip.getId());
                            //Here edit time and minute
                            Calendar c = datePicker.getCalender();
                            startAlarm(c,tripNameE,startTimeE,startDateE,destinationString);
                            Toasting.toastAnywhere(getApplicationContext(), "Trip Added",0);
                            Toasting.toastAnywhere(getApplicationContext(),  newTrip.getDistance() +" & "+ newTrip.getDuration(),1);

                           // finish();
                            /*
                            txtDistance.setAnimation(fabOpen);
                            textViewDistance.setAnimation(fabOpen);
                            txtDuration.setAnimation(fabOpen);
                            textViewDuration.setAnimation(fabOpen);

                            txtDistance.setVisibility(View.VISIBLE);
                            textViewDistance.setVisibility(View.VISIBLE);
                            txtDuration.setVisibility(View.VISIBLE);
                            textViewDuration.setVisibility(View.VISIBLE);

                             */

                        }
                    }).start();


                    btnAddNote.setEnabled(true);
                } else //it's a rounded trip, add it twice with reverted directions

                    {

                    //   Log.d("Debug", "Go to rounded trip activity");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //upcomming trip

                                ArrayList<String > list= new ArrayList<>();
                                list=  Distance.getSpaceTime(startLocationString, destinationString);
                                newTrip.setDistance(list.get(0));
                             //   txtDistance.setText(list.get(0));
                                newTrip.setDuration(list.get(1));
                               // txtDuration.setText(list.get(1));
                                database.addTripToDataBase(AddTripActivity.this, newTrip);
                                Toasting.toastAnywhere(getApplicationContext(), "Trip Added",0);
                                Toasting.toastAnywhere(getApplicationContext(),  newTrip.getDistance() +" & "+ newTrip.getDuration(),1);
//TODO rounded trip
                                //Here edit time and minute
                                                             //Start Activity
                            }
                        }).start();

                                Intent roundedTrip = new Intent(AddTripActivity.this, RoundedTrip.class);
                                roundedTrip.putExtra("trip", newTrip);
                                startActivity(roundedTrip);


                }
                }else{

                    Toast.makeText(AddTripActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public boolean isFilled() {

        if (!(
                         tripName.getText().toString().isEmpty() ||
                        startLocation.getText().toString().isEmpty() ||
                        destination.getText().toString().isEmpty() ||
                        startDate.getText().toString().isEmpty() ||
                        startTime.getText().toString().isEmpty() ||
                        description.getText().toString().isEmpty()

        )) {
            return true;


        } else {

            return false;
        }

    }



        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void startAlarm(Calendar c, String tripName, String startTime, String startDate, String dest) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlermReciever.class);
            intent.putExtra("tripName",tripName);
            intent.putExtra("tripTime",startTime);
            intent.putExtra("tripDate",startDate);
            intent.putExtra("tripDest",dest);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

            if (c.before(Calendar.getInstance())) {
                c.add(Calendar.DATE, 1);
            }
                    Intent authCheckIntent = new Intent(AddTripActivity.this, AuthenticationCheckService.class);
        startService(authCheckIntent);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);


        }
    }
