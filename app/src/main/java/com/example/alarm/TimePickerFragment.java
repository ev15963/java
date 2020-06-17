package com.example.alarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
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

        TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_DEVICE_DEFAULT_DARK, this,
                hour, minute, DateFormat.is24HourFormat(getActivity()));

        //타임 피커의 타이틀 설정
        TextView tvTitle = new TextView(getActivity());
        tvTitle.setText("TimepickerDialog 타이틀");
        tvTitle.setBackgroundColor(Color.parseColor("#ffEEE8AA"));
        tvTitle.setPadding(5, 3, 5, 3);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        tpd.setCustomTitle(tvTitle);
        return tpd;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        String aMpM = "AM";
        if(hourOfDay > 11){
            aMpM = "PM";
        }
        int currentHour;
        if(hourOfDay > 11){
            currentHour = hourOfDay-12;
        }
        else{
            currentHour = hourOfDay;
        }

        tv.setText("설정된 시간은.. \n\n");
        tv.setText(tv.getText() + String.valueOf(hourOfDay) + "시 "
                + String.valueOf(minute) + "분\n");
    }

//    public void show(FragmentManager fragmentManager, String timePicker) {
//    }
}

