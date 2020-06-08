package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcast extends BroadcastReceiver {

    MediaPlayer mediaPlayer;
    private static final String TAG = AlarmBroadcast.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive");
            Log.e("Alarm", "알람설정!");
            String uri = intent.getExtras().getString("MusicUri");

            Toast.makeText(context, "알람", Toast.LENGTH_SHORT).show(); //알람등록
//          에러      mediaPlayer = MediaPlayer.create(context, Uri.parse(uri));
//          에러      mediaPlayer.start();
    }

   /* private void startRingtone(Uri uriRingtone){
        this.stopRingtone();

        try {
            mediaPlayer = MediaPlayer.create(this, uriRingtone);

            if(mMediaPlayer == null){
                throw new Exception("플레이어 생성 불가능");
            }
            //  mMediaPlayer.setAudioStreamType();
            mMediaPlayer.setAudioAttributes(
                    new AudioAttributes
                            .Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build());

            mMediaPlayer.start();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

    }*/
    public void stopRingtone(){
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();;
            }
            mediaPlayer.release();;
            mediaPlayer = null;
        }
    }
}
