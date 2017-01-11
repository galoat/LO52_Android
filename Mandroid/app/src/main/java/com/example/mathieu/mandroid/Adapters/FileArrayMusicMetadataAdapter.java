package com.example.mathieu.mandroid.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathieu.mandroid.Adapters.Item.ItemMetadata;
import com.example.mathieu.mandroid.Adapters.Item.ItemSongMetadata;
import com.example.mathieu.mandroid.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Florian on 11/01/2017.
 */

public class FileArrayMusicMetadataAdapter extends FileArrayMetadataAdapter {

    public FileArrayMusicMetadataAdapter(Context context, int textViewResourceId, List<ItemMetadata> objects) {
        super(context, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        final Button buttonPlay = (Button) v.findViewById(R.id.PlayButton);
        try {
            final ItemSongMetadata o = (ItemSongMetadata) items.get(position);
            final File f = new File(o.getPath());


            TextView t1 = (TextView) v.findViewById(R.id.echantilText);
            if (o.getEchantilloage().equals("")) {
                t1.setText(getContext().getString(R.string.nothing));
            } else {
                t1.setText(o.getEchantilloage());
            }

            buttonPlay.setOnClickListener(new OnClickListener() {
                MediaPlayer mediaPlayer;
                boolean lecture = false;

                @Override
                public void onClick(View v) {

                    if (!lecture) {
                        Uri uri = Uri.fromFile(f);
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(parent.getContext().getApplicationContext(), uri);
                            mediaPlayer.prepare();
                            lecture = true;
                            buttonPlay.setText(getContext().getString(R.string.stop));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                    } else {
                        buttonPlay.setText(getContext().getString(R.string.play));
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        lecture = false;

                    }
                }
            });
            // si le get etais en debors
        }catch (IndexOutOfBoundsException e){
            Log.e(TAG, "Exception : " + e.getMessage());
        }

        return v;
    }
}
