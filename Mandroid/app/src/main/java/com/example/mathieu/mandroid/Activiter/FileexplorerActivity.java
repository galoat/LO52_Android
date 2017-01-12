package com.example.mathieu.mandroid.Activiter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mathieu.mandroid.Adapters.FileArrayAdapter;
import com.example.mathieu.mandroid.Adapters.FileArrayMetadataAdapter;
import com.example.mathieu.mandroid.Adapters.FileArrayMusicMetadataAdapter;
import com.example.mathieu.mandroid.Adapters.FileArraySeriesMetadataAdapter;
import com.example.mathieu.mandroid.Adapters.Item.Item;
import com.example.mathieu.mandroid.Adapters.Item.ItemMetadata;
import com.example.mathieu.mandroid.Adapters.Item.ItemSerieMetadata;
import com.example.mathieu.mandroid.Adapters.Item.ItemSongMetadata;
import com.example.mathieu.mandroid.R;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static com.example.mathieu.mandroid.R.id.listFilm;
import static com.example.mathieu.mandroid.R.id.listView;
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
                DrawerLayout mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.openDrawer(findViewById(R.id.listFilm));
            }
        }

    }

    private ListView addMetadataToRightView(File curentDir){
        ListView listViewDroite =(ListView)findViewById(R.id.codec);
        listViewDroite.setAdapter(null);

        File[] dirs = curentDir.listFiles();
        fls = new ArrayList<>();
        for (File ff : dirs) {
            Date lastModDate = new Date(ff.lastModified());
            DateFormat formater = DateFormat.getDateTimeInstance();
            String date_modify = formater.format(lastModDate);
            fls.add(new Item(ff.getName(), ff.length() + "byt", date_modify, ff.getAbsolutePath(), getString(video)));
        }
        ListView la = (ListView) findViewById(listView);
        FileArrayAdapter adapter = new FileArrayAdapter(FileexplorerActivity.this, R.layout.row2, fls);
        la.setAdapter(adapter);
        return la;

    }


    private File getFileAtPosition(int pos,String sousDossier){
        Item o = fls.get(pos);
        return new File(curFileName + "/"+sousDossier+"/"+o.getName());
    }


    private void gestionMetadataFilm(int position){

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        FileArrayMetadataAdapter metaAdapter ;

        try {
            File f=  getFileAtPosition(position,"film");
            LinkedList<ItemMetadata> lis= new LinkedList<>();
            retriever.setDataSource(f.getAbsolutePath());
            ItemMetadata met= new ItemMetadata(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE),
                    retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE),
                    retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION),
                    f.getAbsolutePath());
            lis.add(met);
            metaAdapter= new FileArrayMetadataAdapter(FileexplorerActivity.this,R.layout.rowmetadata,lis);
            ListView v =(ListView)findViewById(R.id.codec);
            v.setAdapter(metaAdapter);
        }catch (Exception e){
            Log.e(TAG, "Exception : " + e.getMessage());
        }

    }
    public void gestionMetadataSerie(int position){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        FileArrayMetadataAdapter metaAdapter ;

        try {
            File f=  getFileAtPosition(position,"series");
            LinkedList<ItemMetadata> lis= new LinkedList<>();
            retriever.setDataSource(f.getPath());

            ItemSerieMetadata met= new ItemSerieMetadata(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE),
                    retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE),
                    retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION),
                    f.getAbsolutePath());
            String filename=f.getName();
            Pattern pattern= Pattern.compile(".*[sS](\\d{2})[eE](\\d{2}).*");
            Matcher m = pattern.matcher(filename);
            boolean result=  m.matches();
            if(result){
               met.setSaison(m.group(1));
               met.setEpisode(m.group(2));

            }



            lis.add(met);
            metaAdapter= new FileArraySeriesMetadataAdapter(FileexplorerActivity.this,R.layout.rowseriemetadata,lis);
            ListView v =(ListView)findViewById(R.id.codec);
            v.setAdapter(metaAdapter);
        }catch (Exception e){
            Log.e(TAG, "Exception : " + e.getMessage());
        }
    }

    public void gestionMetadataMusic(int position){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        FileArrayMetadataAdapter metaAdapter ;
        try {
            File f=  getFileAtPosition(position,"music");
            LinkedList<ItemMetadata> lis= new LinkedList<>();
            retriever.setDataSource(f.getAbsolutePath());

            ItemSongMetadata met= new ItemSongMetadata(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE),
                    f.getAbsolutePath());
            met.setEchantilloage(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
            lis.add(met);
            metaAdapter= new FileArrayMusicMetadataAdapter(FileexplorerActivity.this,R.layout.rowmusicmetadata,lis);
            ListView v =(ListView)findViewById(R.id.codec);
            v.setAdapter(metaAdapter);
            //get image peut être null
        }catch (Exception e){
            Log.e(TAG, "Exception : " + e.getMessage());
        }
    }
    private void openDrawerMetadata(){
        DrawerLayout mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.openDrawer(findViewById(R.id.codec));
    }

    private void menusDeroulant() {
        //Gestion de l'affichage du menus deroulant
        ListView listview = (ListView) findViewById(listFilm);

        String[] name = {
                "film",
                "audio",
                "serie"
        };

        ArrayList<String> planetList = new ArrayList<>();
        planetList.addAll(Arrays.asList(name));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, planetList);
        listview.setAdapter(adapter);



        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (position == 0) {
                    File currentDir = new File(curFileName + "/film");
                    ListView listLeft = addMetadataToRightView(currentDir);
                    listLeft.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            gestionMetadataFilm(position);
                            openDrawerMetadata();
                        }
                    });

                } else if (position == 1) {
                    File currentDir = new File(curFileName + "/music");
                    ListView listView =  addMetadataToRightView(currentDir);

                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            gestionMetadataMusic(position);
                            openDrawerMetadata();
                        }

                    });
                } else {
                    File currentDir = new File(curFileName + "/series");
                    ListView listView = addMetadataToRightView(currentDir);
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            gestionMetadataSerie(position);
                            openDrawerMetadata();

                        }

                    }


                    );

                }
                DrawerLayout mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawers();
            }
        });

    }
}


