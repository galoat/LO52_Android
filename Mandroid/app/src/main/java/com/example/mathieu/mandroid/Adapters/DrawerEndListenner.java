package com.example.mathieu.mandroid.Adapters;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.mathieu.mandroid.Activiter.AcitivityVideo;

/**
 * Created by Florian on 08/01/2017.
 */

public class DrawerEndListenner implements DrawerListener {
    String path;
    ViewGroup parent;
    OnClickListener meta;
    DrawerLayout lay;

    DrawerEndListenner(String path, final ViewGroup parent,DrawerLayout lay){
        this.path=path;
        this.parent=parent;
        this.lay=lay;

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
        parent.getContext().startActivity(intent);

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
