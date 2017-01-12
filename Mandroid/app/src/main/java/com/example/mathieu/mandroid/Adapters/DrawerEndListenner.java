package com.example.mathieu.mandroid.Adapters;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathieu.mandroid.Activiter.AcitivityVideo;

/**
 * Created by Florian on 08/01/2017.
 */

public class DrawerEndListenner implements DrawerListener {
    private String path;
    private ViewGroup parent;
    private FileArrayMetadataAdapter fileArrayMetadataAdapter;
    DrawerEndListenner(String path, final ViewGroup parent,FileArrayMetadataAdapter f){
        this.path=path;
        this.parent=parent;
        this.fileArrayMetadataAdapter=f;

    }
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Intent intent= new Intent(parent.getContext(),AcitivityVideo.class);
        intent.putExtra("path",path);
        fileArrayMetadataAdapter.removeBindDrawerListenner();
        parent.getContext().startActivity(intent);


    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
