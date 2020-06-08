package com.example.alarm;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;

import static android.text.format.DateFormat.is24HourFormat;

public class TimePickerFragment extends AlarmFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    @NonNull
    @Override
    public Dialog onCreatelog(Bundle savedInstanceState) {
        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int min = mCalendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                getContext(), this, hour, min, DateFormat.is24HourFormat(getContext())
        );
        return mTimePickerDialog;
    }
}

