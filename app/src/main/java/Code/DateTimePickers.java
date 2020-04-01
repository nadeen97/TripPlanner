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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimePickers {
Calendar remindCalender;
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
                        remindCalender=Calendar.getInstance();
                        remindCalender.set(Calendar.YEAR, year);
                        remindCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        remindCalender.set(Calendar.MONTH,month);

                        String selectedDate = DateFormat.getDateInstance(DateFormat.LONG).format(selectedCalendar.getTime());
                        textView.setText(selectedDate);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                       // textView.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                            Date d = new Date();
                            Date chosen = new Date(year, month, dayOfMonth);
                            if(d.before(chosen)){


                               textView.performClick();
                               Toasting.toastAnywhere(context, "Please, choose a later time");
                            }

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

                        selectedTime.set(Calendar.HOUR, hourOfDay);
                        selectedTime.set(Calendar.MINUTE, minute);
                       // selectedTime.set(Calendar.AM_PM,)
                        textView.setText(hourOfDay + " : " + minute);

                        remindCalender.set(Calendar.HOUR, hourOfDay);
                        remindCalender.set(Calendar.MINUTE, minute);
                    }
                }, hour, minuteA, false);
                timePickerDialog.show();
            }
        });

    }
    public Calendar getCalender()
    {
        return remindCalender;
    }


}

