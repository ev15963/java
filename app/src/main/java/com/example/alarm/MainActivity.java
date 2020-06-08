package com.example.alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import static android.text.format.DateFormat.is24HourFormat;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    AlarmFragment alarmfragment;
    CalendarFragment calendarfragment;
    WeatherFragment weatherfragmet;
    ConfigFragment configfragment;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        alarmfragment = new AlarmFragment();
        calendarfragment = new CalendarFragment();
        weatherfragmet = new WeatherFragment();
        configfragment = new ConfigFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, alarmfragment).commitAllowingStateLoss();       //첫 화면은 알람

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tab1:{        //탭1 클릭시 알람화면
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, alarmfragment).commitAllowingStateLoss();
                        return true;
                    }

                    case R.id.tab2:{        //탭2 클릭시 캘린더화면
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, calendarfragment).commitAllowingStateLoss();
                        return true;
                    }

                    case R.id.tab3:{        //탭3 클릭시 날씨화면
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,weatherfragmet).commitAllowingStateLoss();
                        return true;
                    }

                    case R.id.tab4:{        //탭4 클릭시 어플설정 화면
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, configfragment).commitAllowingStateLoss();
                        return true;
                    }

                    default: return false;
                }
            }

        });

    }
    @Override
    public void onClick(View v) {
        TimePickerFragment mTimePickerFragment = new TimePickerFragment();
        mTimePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.FRAGMENT_TAG);
    }
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Canlendar canlendar = Canlendar.getInstance();
//        int hour = canlendar.get(Calendar.HOUR_OF_DAY);
//        int min = canlendar.get(Calendar.MINUTE);
//        TimePickerDialog mTimePickerlog = new TimePickerDialog(
//                getContext(), this, hour, min, DateFormat,is24HourFormat(getContext())
//        );
//        return mTimePickerlog;
//    }

}
