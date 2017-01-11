package com.example.mathieu.mandroid.Activiter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.mathieu.mandroid.R;

/**
 * Created by Florian on 07/01/2017.
 */

public class AcitivityVideo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityvideo);
        final Intent intent = getIntent();
        String uri = intent.getStringExtra("path");
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath(uri);
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
