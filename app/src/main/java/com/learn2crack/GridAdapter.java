package com.learn2crack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by timur on 10-Jul-17.
 */

public class GridAdapter extends BaseAdapter {

    Context context;

    private final String[] values;
    private final int[] images;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, String[] values, int[] images) {
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item,null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            imageView.setImageResource(images[i]);
            textView.setText(values[i]);
        }
        return view;
    }
}
