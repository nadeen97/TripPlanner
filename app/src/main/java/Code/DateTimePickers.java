package Code;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class DateTimePickers {

    TextView textView;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Context context;
    public DateTimePickers(Context context) {
      //  this.textView = textView;
        this.context = context;
       // getDate();
    }

    public void getDate(final TextView textView){

            this.textView = textView;
             textView.setOnKeyListener(null);
             textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int ThisYear = calendar.get(Calendar.YEAR);
                final int ThisMonth = calendar.get(Calendar.MONTH);
                final int ThisDay = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar selectedCalendar = Calendar.getInstance();

                        selectedCalendar.set(Calendar.YEAR, year);
                        selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        selectedCalendar.set(Calendar.MONTH,month);

                        String selectedDate = DateFormat.getDateInstance(DateFormat.LONG).format(selectedCalendar.getTime());
                        textView.setText(selectedDate);
                       // textView.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, ThisYear, ThisMonth, ThisDay);
                datePickerDialog.show();
            }
        });

    }

    public void getTime(final TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Log.d("Debug", "onClick: Clicked");
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minuteA = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                       // String am_pm="";
                        Calendar selectedTime = Calendar.getInstance();

                        /*
                        //
                        if (selectedTime.get(Calendar.AM_PM) == Calendar.AM)
                            am_pm = "AM";
                        else if (selectedTime.get(Calendar.AM_PM) == Calendar.PM)
                            am_pm = "PM";
                        //
                        */

                        selectedTime.set(Calendar.HOUR, hourOfDay);
                        selectedTime.set(Calendar.MINUTE, minute);
                       // selectedTime.set(Calendar.AM_PM,)
                        textView.setText(hourOfDay + " : " + minute);
                    }
                }, hour, minuteA, false);
                timePickerDialog.show();
            }
        });

    }

}

