package com.example.mathieu.mandroid;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.mathieu.mandroid.R.id.listFilm;
import static com.example.mathieu.mandroid.R.string.video;

public class FileexplorerActivity extends Activity {

    private static final int REQUEST_PATH = 1;
    String curFileName;
    EditText edittext;
    List<Item> fls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explorer);
        edittext = (EditText) findViewById(R.id.editText);
        edittext.setText(PreferenceManager.getDefaultSharedPreferences(this).getString("nomDir", ""));
        curFileName = PreferenceManager.getDefaultSharedPreferences(this).getString("nomDir", "");
        menusDeroulant();
    }

    public void getfile(View view) {
        Intent intent1 = new Intent(this, FileChooser.class);
        startActivityForResult(intent1, REQUEST_PATH);

    }

    // Listen for results.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // See which child activity is calling us back.
        if (requestCode == REQUEST_PATH) {
            if (resultCode == RESULT_OK) {
                curFileName = data.getStringExtra("GetPath");
                curFileName = curFileName + "/" + data.getStringExtra("GetFileName");
                edittext.setText(curFileName);
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("nomDir", curFileName).apply();
            }
        }

    }

    private ListView addViewToPrincipalScreen(File cuurentDir){
        ListView v =(ListView)findViewById(R.id.codec);
        v.setAdapter(null);

        File[] dirs = cuurentDir.listFiles();
        fls = new ArrayList<Item>();
        for (File ff : dirs) {
            Date lastModDate = new Date(ff.lastModified());
            DateFormat formater = DateFormat.getDateTimeInstance();
            String date_modify = formater.format(lastModDate);
            fls.add(new Item(ff.getName(), ff.length() + "byt", date_modify, ff.getAbsolutePath(), getString(video)));
        }
        ListView la = (ListView) findViewById(R.id.listView);
        FileArrayAdapter adapter = new FileArrayAdapter(FileexplorerActivity.this, R.layout.row2, fls);
        la.setAdapter(adapter);
        return la;
    }

    private void gestionMetadataFilm(int position){
        Item o = fls.get(position);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        FileArrayMetadataAdapter metaAdapter ;
        try {
            File f = new File(curFileName + "/film/"+o.getName());
            LinkedList<ItemMetadata> lis= new LinkedList<ItemMetadata>();
            retriever.setDataSource(f.getAbsolutePath());
            ItemMetadata met= new ItemMetadata(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE), retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE),retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
            lis.add(met);
            metaAdapter= new FileArrayMetadataAdapter(FileexplorerActivity.this,R.layout.rowmetadata,lis);
            ListView v =(ListView)findViewById(R.id.codec);
            v.setAdapter(metaAdapter);
        }catch (Exception e){
            Log.e(TAG, "Exception : " + e.getMessage());
        }

    }

    private void menusDeroulant() {
        //Gestion de l'affichage du menus deroulant
        ListView listview = (ListView) findViewById(listFilm);

        String[] name = {
                "film",
                "audio",
                "serie"
        };

        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(name));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, planetList);
        listview.setAdapter(adapter);



        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (position == 0) {
                    File currentDir = new File(curFileName + "/film");
                    ListView listVideo= addViewToPrincipalScreen(currentDir);


                    listVideo.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            gestionMetadataFilm(position);
                        }
                    });
                } else if (position == 1) {
                    File currentDir = new File(curFileName + "/music");

                    ListView listMusic= addViewToPrincipalScreen(currentDir);


                } else {
                    File currentDir = new File(curFileName + "/series");

                    ListView listSeries= addViewToPrincipalScreen(currentDir);
                }
            }
        });

    }
}


