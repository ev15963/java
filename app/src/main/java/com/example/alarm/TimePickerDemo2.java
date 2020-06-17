package com.example.alarm;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TimePickerDemo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
    }

    public void onBtnClicked(View v){
        TimePickerFragment newFragment = new TimePickerFragment();
        //show : fragmentManager에 추가된 대화상자 출력
        newFragment.show(getFragmentManager(), "TimePicker");
    }
}
