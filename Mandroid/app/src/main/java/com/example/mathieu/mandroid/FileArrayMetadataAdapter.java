package com.example.mathieu.mandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Florian on 31/12/2016.
 */

public class FileArrayMetadataAdapter extends ArrayAdapter<ItemMetadata> {
    private Context c;
    private int id;
    private List<ItemMetadata>items;


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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, null);
        }

               /* create a new view of my layout and inflate it in the row */
        //convertView = ( RelativeLayout ) inflater.inflate( resource, null );

        final ItemMetadata o = items.get(position);
        if (o != null) {
            TextView t1 = (TextView) v.findViewById(R.id.inputBirate);
            TextView t2 = (TextView) v.findViewById(R.id.inputDate);
              t1.setText(o.getBitrate());
            t2.setText(o.getDateCreation());

         }
        return v;
}



}
