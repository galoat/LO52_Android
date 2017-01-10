package com.example.mathieu.mandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathieu.mandroid.R;

import java.util.List;

/**
 * Created by Florian on 31/12/2016.
 */

public class FileArrayMetadataAdapter extends ArrayAdapter<ItemMetadata> {
    private Context c;
    private int id;
    private List<ItemMetadata>items;
    DrawerListener drawerListener;
    DrawerLayout drawerLayout;



    public FileArrayMetadataAdapter(Context context, int textViewResourceId,
                            List<ItemMetadata> objects) {
        super(context, textViewResourceId, objects);
        c = context;
        id = textViewResourceId;
        items = objects;
    }

    public ItemMetadata getItem(int i)
    {
        return items.get(i);
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, null);
        }




        final ItemMetadata o = items.get(position);
        if (o != null) {
            TextView t1 = (TextView) v.findViewById(R.id.inputBirate);
            TextView t2 = (TextView) v.findViewById(R.id.inputDate);
            t1.setText(o.getBitrate());
            t2.setText(o.getDateCreation());

         }
        if(drawerListener!=null){
            drawerLayout.removeDrawerListener(drawerListener);
            drawerListener=null;
        }

        Button buttonPlay = (Button) v.findViewById(R.id.PlayButton);

        buttonPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout = (DrawerLayout) ((Activity) getContext()).getWindow().getDecorView().findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawers();
                drawerListener = new DrawerEndListenner(o.getPath(), parent, drawerLayout);
                drawerLayout.addDrawerListener(drawerListener);

             /*
                pout l'audio


                File f= new File(o.getPath());

                Uri uri=Uri.fromFile(f);
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(parent.getContext().getApplicationContext(), uri);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();*/


            }
        });

        return v;
}



}