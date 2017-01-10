package com.example.mathieu.mandroid.Activiter;

/**
 * Created by Mathieu on 09/12/2016.
 */


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mathieu.mandroid.Adapters.FileArrayAdapter;
import com.example.mathieu.mandroid.Adapters.Item;
import com.example.mathieu.mandroid.R;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileChooser extends ListActivity {

    private File currentDir;
    private FileArrayAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        longClick();
        currentDir =  new File("/");

        try {
            fill(currentDir);
        }catch (Exception e){
            Toast.makeText(FileChooser.this, getString(R.string.pasDeDossier), Toast.LENGTH_LONG).show();
        }

    }

    private void longClick(){
        this.getListView().setLongClickable(true);
        //TODO afficher un "menu" permettant de cliquer sur importer
        this.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                Item o = adapter.getItem(position);
                if(o.getImage().equalsIgnoreCase("directory")||o.getImage().equalsIgnoreCase("directory_up")) {
                    Intent intent = new Intent();
                    intent.putExtra("GetPath", currentDir.getAbsolutePath());
                    intent.putExtra("GetFileName", o.getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(FileChooser.this, "il s'agit d'un fichier", Toast.LENGTH_LONG).show();
                }

                return true;
            }

        });
    }
    private void fill(File f) throws Exception
    {
        File[]dirs = f.listFiles();
        this.setTitle("Current Dir: "+f.getName());
        List<Item>dir = new ArrayList<Item>();
        List<Item>fls = new ArrayList<Item>();

            for(File ff: dirs)
            {
                Date lastModDate = new Date(ff.lastModified());
                DateFormat formater = DateFormat.getDateTimeInstance();
                String date_modify = formater.format(lastModDate);
                if(ff.isDirectory()){


                    File[] fbuf = ff.listFiles();
                    int buf = 0;
                    if(fbuf != null){
                        buf = fbuf.length;
                    }
                    else buf = 0;
                    String num_item = String.valueOf(buf);
                    if(buf == 0) num_item = num_item + " item";
                    else num_item = num_item + " items";

                    //String formated = lastModDate.toString();
                    dir.add(new Item(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),getString(R.string.dir)));
                }
                else
                {
                    fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),getString(R.string.file)));
                }
            }

        Collections.sort(dir);
        Collections.sort(fls);
        dir.addAll(fls);
        if(!f.getName().equalsIgnoreCase("sdcard"))
            dir.add(0,new Item("..","Parent Directory","",f.getParent(),"directory_up"));
        adapter = new FileArrayAdapter(FileChooser.this,R.layout.row2,dir);

        this.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Item o = adapter.getItem(position);

        if(o.getImage().equalsIgnoreCase("directory")||o.getImage().equalsIgnoreCase("directory_up")){
            try {
                currentDir = new File(o.getPath());
                fill(currentDir);
            }catch (Exception e){
                Toast.makeText(FileChooser.this, getString(R.string.pasDeDossier), Toast.LENGTH_LONG).show();

            }

        }
        else
        {
            onFileClick(o);
        }
    }


    private void onFileClick(Item o)
    {

        Toast.makeText(FileChooser.this, getString(R.string.selectDir), Toast.LENGTH_LONG).show();

        //Toast.makeText(this, "Folder Clicked: "+ currentDir, Toast.LENGTH_SHORT).show();

    }



}