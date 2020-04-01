package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trippalnner.R;

import Code.Database;
import Code.DateTimePickers;
import Code.PlaceApi;
import POJOs.Trip;

public class RoundedTrip extends AppCompatActivity {

    private TextView startDate, startTime, textViewTripName, textViewStartDate,  textViewStartTime, textViewDescription;
    private EditText tripName;
    private EditText description;
    private DatePickerDialog datePickerDialog;
    private Database database;
    private Button btnConfirmTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_trip);

        textViewTripName = findViewById(R.id.textViewTripName);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewStartTime = findViewById(R.id.textViewStartTime);
        textViewDescription = findViewById(R.id.textViewDescription);
        description = findViewById(R.id.description);
        tripName = findViewById(R.id.tripName);
        startDate = findViewById(R.id.startDate);
        startTime = findViewById(R.id.startTime);


        DateTimePickers datePicker = new DateTimePickers(this);

        datePicker.getDate(startDate);
        datePicker.getTime(startTime);


        Intent incoming = getIntent();

        final Trip roundedTripFirstJourney = (Trip) incoming.getSerializableExtra("trip");

        tripName.setText(roundedTripFirstJourney.getTripName()+" - return trip");
        description.setText(roundedTripFirstJourney.getDescription()+ " - return trip");


        btnConfirmTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isFilled()){
                            String secEndLoc = roundedTripFirstJourney.getStartLocationString();
                            String secStartLoc = roundedTripFirstJourney.getDestinationString();
                            Double secStartLat = roundedTripFirstJourney.getDestinationLat();
                            Double secStartLong = roundedTripFirstJourney.getGetDestinationLong();
                            Double secEndLat = roundedTripFirstJourney.getStartLocLat();
                            Double secEndLong = roundedTripFirstJourney.getStartLocLong();
                            String repeat = roundedTripFirstJourney.getRepeat();
                            String round = roundedTripFirstJourney.getRound();

                            String tripNameE = tripName.getText().toString();
                            String startDateE = startDate.getText().toString();
                            String startTimeE = startTime.getText().toString();
                            String descriptionN = description.getText().toString();
                            String id = "";
                            Trip newRoundTrip = new Trip(id,tripNameE,secStartLoc,secStartLat,secStartLong
                            ,secEndLoc,secEndLat,secEndLong,startDateE,startDateE,descriptionN,repeat,round);

                            database.addTripToDataBase(getApplicationContext(), newRoundTrip);
                        }
                    }
                }).start();
            }
        });
        // Clicks

        textViewTripName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Log.d("Debug", "onClick: trip name");
                tripName.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(AddTripActivity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(tripName, InputMethodManager.SHOW_IMPLICIT);
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


    }

    public boolean isFilled() {

        if (!(
                        tripName.getText().toString().isEmpty()||
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
