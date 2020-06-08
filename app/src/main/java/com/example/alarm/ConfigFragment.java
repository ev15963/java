package com.example.alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class ConfigFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.config,container,false);
        Button btnDeveloper = (Button) viewGroup.findViewById(R.id.configDeveloper);    //개발자 정보 버튼
        Button btnAlarm = (Button) viewGroup.findViewById(R.id.configAlarm);            //알람 설정 버튼 *미구현
        Button btnCalendar = (Button) viewGroup.findViewById(R.id.configCalendar);      //캘린더 설정 버튼 *미구현
        Button btnWeather = (Button) viewGroup.findViewById(R.id.configWeather);        //날씨 설정 버튼 *미구현

        btnDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());     //개발자 정보버튼 클릭시 대화상자
                alert.setTitle("개발자 정보");
                alert.setMessage("팀장 : 이동화\n" +
                        "팀원 : 이상우\n" +
                        "팀원 : 오동수\n" +
                        "팀원 : 김현승\n");
                alert.setPositiveButton("확인",null);
                alert.show();


            }
        });

        return  viewGroup;
    }
}
