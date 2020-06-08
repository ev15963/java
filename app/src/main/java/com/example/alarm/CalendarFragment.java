package com.example.alarm;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalendarFragment extends Fragment {
    ViewGroup viewGroup;

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.calendar,container,false);
        CalendarView calendarView = (CalendarView) viewGroup.findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                Toast.makeText(getActivity(), "" + year + "/" +
                        (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

        return viewGroup;
    }
}
