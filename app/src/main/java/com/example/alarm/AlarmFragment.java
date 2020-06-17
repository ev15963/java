package com.example.alarm;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class AlarmFragment extends Fragment {
        private static final int REQUESTCODE_RINGTONE_PICKER = 1000;
        ViewGroup viewGroup;
        Button set,remove, ringtoneShow, ringtoneRemove, plus;
        PendingIntent alarmIntent;
        TimePicker timePicker;
        static AlarmManager alarmMgr;
        int hour, minute;
        String ringtoneUri;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.alarm, container, false);
        timePicker = viewGroup.findViewById(R.id.timePick);
        set = viewGroup.findViewById(R.id.setAl);
        remove = viewGroup.findViewById(R.id.removeAl);
        ringtoneShow = viewGroup.findViewById(R.id.ringtoneShow);
        ringtoneRemove = viewGroup.findViewById(R.id.ringtoneRemove);
        plus = viewGroup.findViewById(R.id.plus);

        //알람 설정
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlarm(viewGroup);
            }
        });

        //알람 해제
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAlarm(viewGroup);
            }
        });

        ringtoneShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRingtone();
            }
        });

        ringtoneRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showRingtone();
            }
        });

        return viewGroup;
    }

    public void startAlarm(View view){
        alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmBroadcast.class);
        Intent uriIntent = new Intent(getActivity(), AlarmBroadcast.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            hour = timePicker.getHour(); // API23 이후에서 getHour, getMinute 작동
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour(); // API23 이전은 getCurrentHour, getCurrentMinute 작동
            minute = timePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);   //타임피커에서 설정한 hour 를 저장
        calendar.set(Calendar.MINUTE,minute);       //타임피커에서 설정한 minute 을 저장
        calendar.set(Calendar.SECOND, 0);           //'초' 를 0으로 설정
        calendar.set(Calendar.MILLISECOND,0);       //'밀리초' 를 0으로 설정

        //알람 매니저로 알람을 설정, (UTC표준시간 기기가 절전모드에서도 wakeup, 내가 설정한 시간에, 24시간 뒤 또 알람울림)
       alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pIntent);
       uriIntent.putExtra("MusicUri", ringtoneUri);
       if(getActivity() != null){
           getActivity().sendBroadcast(uriIntent);
       }

        Toast.makeText(getActivity(), "알람이 설정되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public void removeAlarm(View view){
        Intent intent = new Intent(getActivity(), AlarmBroadcast.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
        alarmMgr.cancel(pIntent);
        Toast.makeText(getActivity(), "알람이 해제되었습니다.", Toast.LENGTH_SHORT).show(); //알람해제
    }

    public void showRingtone(){
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);

        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "알람음 선택"); //알람음 선택
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL);

        if(ringtoneUri != null && ringtoneUri.isEmpty()){
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(ringtoneUri));
        }
        this.startActivityForResult(intent, REQUESTCODE_RINGTONE_PICKER);
    }
    //날짜표시
    public void displayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
        ((TextView) findViewById(R.id.txtDate)).setText(format.format(this.calendar.getTime()));
    }

    //DatePickerDialog 호출
    public void showDatePicker(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                //알람날짜설정
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, dayOfMonth);

                //날짜표시
                displayDate();
            }
        }, this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), this .calendar.get(Calendar.DAY_OF_MONTH);

        dialog.show();
    }

    //알람 등록
    //public void setAlarm

    //mTimePicker = (TimePicker) findViewById(R.id.timePicker);

    //mCalendar = Calendar.getInstance();

//    public void startAlarm(View view){
//
//        Log.i(TAG, "startAlarm");
//
//        alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(getActivity(), AlarmBroadcast.class);
//        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
//
//        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 60*1000, alarmIntent);
//        Toast.makeText(getActivity(), "알람이 설정되었습니다.", Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void removeAlarm(View view){
//        Log.i(TAG, "removeAlarm");
//
//        if(alarmMgr!= null){
//            alarmMgr.cancel(alarmIntent);
//        }
//    }


//    public void createNotification(ViewGroup viewGroup){
//        show();
//    }
//
//    public void show() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "default");
//
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentTitle("알람 제목");
//        builder.setContentText("알람 세부 텍스트");
//
//        Intent intent = new Intent(getActivity(), AlarmFragment.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        builder.setContentIntent(pendingIntent);
//
//        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        builder.setColor(Color.RED);
//
//        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity(), RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(ringtoneUri);
//
//        long[] vibrate = {0, 100, 200, 300};
//        builder.setVibrate(vibrate);
//        builder.setAutoCancel(true);
//
//        NotificationManager manager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
//        manager.notify(1,builder.build());
//    }
//
//    public void removeNotification(ViewGroup viewGroup){
//        hide();
//    }
//
//    public void hide() {
//        NotificationManagerCompat.from(getActivity()).cancel(1);
//    }

    //    public void setAlarm(){
//        Intent intent = new Intent(getActivity(), AlarmBroadcast.class);
//        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            hour = timePicker.getHour();
//            minute = timePicker.getMinute();
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,hour);
//        calendar.set(Calendar.MINUTE,minute);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MILLISECOND,0);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY
//        , pIntent);
//
//    }
//

}
