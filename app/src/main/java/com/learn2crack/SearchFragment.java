package com.learn2crack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by timur on 10-Jul-17.
 */

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";


    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,container,false);
        listView = (ListView) view.findViewById(R.id.items);

        editText = (EditText) view.findViewById(R.id.txtsearch);

//        initList();

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence.toString().equals("")){
//                    initList();
//                }
//                else{
//                    searchItem(charSequence.toString());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        return view;
    }

//    public void searchItem(String s){
//        for(String item : items){
//            if(!item.contains(s)){
//                listItems.remove(item);
//            }
//        }
//        adapter.notifyDataSetChanged();
//
//    }
//    public void initList(){
//        items = new String[]{"Kyrgyztan","USA","Russia","Germany","Italy","Japan"};
//        listItems = new ArrayList<String>(Arrays.asList(items));
//        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,R.id.txtsearch,listItems);
//        listView.setAdapter(adapter);
//
//
//    }


}
