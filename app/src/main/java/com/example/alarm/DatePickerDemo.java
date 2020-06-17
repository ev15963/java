package com.example.alarm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class DatePickerDemo extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    DatePicker dp;
    Calendar c;
    Button btnUpdateDate;
    Button btnGetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH); //1월은 '0'부터 시작
        int dayOfMonth = c.get(c.DAY_OF_MONTH);

        tv = (TextView) findViewById(R.id.tv);
        btnUpdateDate = (Button) findViewById(R.id.btn_update_date);
        btnGetDate = (Button) findViewById(R.id.btn_get_date);
        dp = (DatePicker) findViewById(R.id.dp);

        tv.setText("초기 설정된 날자 [월/일/년도] : \n"
                + year + "/" + (month+1) + "/" + dayOfMonth);
        btnUpdateDate.setOnClickListener(this);
        btnGetDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnUpdateDate) {
            tv.setText("");
        }
        else{
            tv.setText("선택된 날짜 : [월/일/년도]\n");
            tv.setText(tv.getText() + " " + dp.getYear() + "/"
                    + (dp.getMonth()+1) + "/" + dp.getDayOfMonth());
        }
    }
}

