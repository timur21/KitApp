package com.learn2crack;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.learn2crack.model.Response;

/**
 * Created by timur on 10-Jul-17.
 */

public class CatalogFragment extends Fragment {
    private static final String TAG = "CatalogFragment";

    ListView listView;
    String[] titles;
    String[] langs;

    int[] imgs={
            R.drawable.book,
            R.drawable.book,
            R.drawable.book,
            R.drawable.book,
            R.drawable.book,
            R.drawable.book,
            R.drawable.book,
            R.drawable.book,
            R.drawable.book
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_fragment,container,false);

        listView = (ListView) view.findViewById(R.id.books_catalog);

        Resources res = getResources();

        titles = res.getStringArray(R.array.c_titles);
        langs = res.getStringArray(R.array.c_langs);

//        MyAdapter myAdapter = new MyAdapter(getActivity(),titles,imgs,langs);
//        listView.setAdapter(myAdapter);

        return view;
    }

//    class MyAdapter extends ArrayAdapter<String>{
//        Context context;
//        int[] imgs;
//        String[] titles;
//        String[] langs;
//
//        MyAdapter(Context c,String[] titles,int[] imgs,String[] langs){
//            super(c,R.layout.book_item,R.id.b_title,titles);
//            this.context = c;
//            this.imgs = imgs;
//            this.langs = langs;
//            this.titles = titles;
//        }
//
//        public View getView(int position,View view,ViewGroup parent){
//            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View item = layoutInflater.inflate(R.layout.book_item,parent,false);
//
//            ImageView image_book = (ImageView) view.findViewById(R.id.image_book);
//            TextView b_title = (TextView) view.findViewById(R.id.b_title);
//
//            image_book.setImageResource(imgs[position]);
//            b_title.setText(titles[position]);
//
//            return item;
//        }
//    }

}
