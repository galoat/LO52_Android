package com.example.mathieu.mandroid.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mathieu.mandroid.Adapters.Item.ItemMetadata;
import com.example.mathieu.mandroid.Adapters.Item.ItemSerieMetadata;
import com.example.mathieu.mandroid.R;

import java.util.List;

/**
 * Created by Florian on 10/01/2017.
 */

public class FileArraySeriesMetadataAdapter extends FileArrayMetadataAdapter {


    public FileArraySeriesMetadataAdapter(Context context, int textViewResourceId, List<ItemMetadata> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
       View v= super.getView(position,convertView,parent);
        final ItemSerieMetadata o = (ItemSerieMetadata)items.get(position);
        if(o!=null){
            TextView t1 = (TextView) v.findViewById(R.id.saisonInput);
            t1.setText("fr,fk,rl");

        }
        return v;
    }
}
