package UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.trip.trippalnner.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;

import Code.Database;
import Code.DateTimePickers;
import Code.Distance;
import Code.PlaceApi;
import Adpters.PlacesAutoSuggestAdapter;
import POJOs.Trip;

public class UpdateActivity extends AppCompatActivity {

    private final String APIKey = "AIzaSyCudlTIHtQyuZ7-6l7Gz9-Nb_0P8Ehyjdc";
    private TextView startDate, startTime, textViewAddNotes, textViewSupposedDirections, textViewAddTrip,
            textViewTripName, textViewFrom, textViewStartDate, textViewTo, textViewStartTime, textViewDescription;
    private EditText tripName;
    private EditText description;
    private AutoCompleteTextView startLocation;
    private AutoCompleteTextView destination;
    private Spinner spinnerRepeat;
    private Spinner spinnerRound;
    private DatePickerDialog datePickerDialog;
    private String repeatStringValue;
    private String roundStringValue;
    private PlaceApi placeApi;
    private Database database;
    private Button btnUpdateTrip, btnDeleteTrip;
    private String updateableTripId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Trip updatableTrip= (Trip) getIntent().getSerializableExtra("trip");
        updateableTripId = updatableTrip.getId();
        //Find misc views

        textViewTripName = findViewById(R.id.textViewTripName);
        textViewFrom = findViewById(R.id.textViewStartLocation);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewStartTime = findViewById(R.id.textViewStartTime);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewTo = findViewById(R.id.textViewTo);
        description = findViewById(R.id.description);
        tripName = findViewById(R.id.tripName);

        //Set the views to the updatable trip inputs

        tripName.setText(updatableTrip.getTripName());
        startLocation.setText(updatableTrip.getStartLocationString());
        startDate.setText(updatableTrip.getStartDate());
        startTime.setText(updatableTrip.getStartTime());
        description.setText(updatableTrip.getDescription());
        destination.setText(updatableTrip.getDestinationString());

        //Focus on views

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



        //Auto completion, Google places API

        placeApi = new PlaceApi();


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
        DateTimePickers datePicker = new DateTimePickers(this);

        datePicker.getDate(startDate);
        datePicker.getTime(startTime);



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
                Toast toast = Toast.makeText(getApplicationContext(), repeatStringValue, Toast.LENGTH_SHORT);
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

        btnUpdateTrip = findViewById(R.id.btnUpdateTrip);

        btnUpdateTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Log.d("Debug", "onClick: Add trip");

                if (isFilled()) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String tripNameE = tripName.getText().toString();
                            String startLocationString = startLocation.getText().toString();
                          //  Double startLocLat = placeApi.getCoordinatesFromAddress(getApplicationContext(), startLocation.getText().toString()).latitude;
                          //  Double startLocLong = placeApi.getCoordinatesFromAddress(getApplicationContext(), startLocation.getText().toString()).longitude;

                            String destinationString = destination.getText().toString();
                           // Double destinationLat = placeApi.getCoordinatesFromAddress(getApplicationContext(), destination.getText().toString()).latitude;
                           // Double destinationLong = placeApi.getCoordinatesFromAddress(getApplicationContext(), destination.getText().toString()).longitude;

                            String startDateE = startDate.getText().toString();

                            String startTimeE = startTime.getText().toString();
                            String descriptionN = description.getText().toString();
                            String repeat = repeatStringValue;
                            String round = roundStringValue;
                            String id = updateableTripId;

                            ArrayList<String > list= new ArrayList<>();
                            list = Distance.getSpaceTime(startLocationString,destinationString);
                            Trip updatedTrip = new Trip(id, tripNameE, startLocationString
                                    , destinationString,startDateE, startTimeE, descriptionN,
                                    repeat, round, list.get(0), list.get(1));

                            database.updateTrip(id, updatedTrip);





                        }
                    }).start();

                    Toast.makeText(getApplicationContext(), "Trip Updated Successfully",Toast.LENGTH_SHORT).show();

                   // btnAddNote.setEnabled(true);
                }else{

                    //   Log.d("Debug", "onClick: Else");
                    Toast.makeText(UpdateActivity.this, "Make sure you filled all details",Toast.LENGTH_SHORT).show();

                }
            }
        });


        btnDeleteTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteTripAndNote(updateableTripId);
            }
        });

    }

    public boolean isFilled() {

        if (!(
                tripName.getText().toString().isEmpty()||
                        startLocation.getText().toString().isEmpty()||
                        destination.getText().toString().isEmpty()||
                        startDate.getText().toString().isEmpty()||
                        startTime.getText().toString().isEmpty()||
                        description.getText().toString().isEmpty()

        )){
            return true;


        }else{

            return false;
        }

    }

}
