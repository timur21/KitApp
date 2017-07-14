package com.learn2crack;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.learn2crack.model.Book;
import com.learn2crack.utils.Constants;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by timur on 10-Jul-17.
 */

public class MyBooksFragment extends Fragment implements View.OnClickListener{

    GridView gridView;

    FloatingActionButton btn_add;

    String[] values={
            "Java",
            "Python",
            "Ruby",
            "C++",
            "C",
            "C#",
            "VisualBasic",
            "Assembler",
            "Django"
    };

    int[] images={
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

    private List<Book> books;

    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_TITLE = "key_book_title";
    public static final String KEY_BOOK_PRICE = "key_book_price";

    private static final String TAG = "MyBooksFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mybooks_fragment,container,false);

        btn_add = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        gridView = (GridView) view.findViewById(R.id.books);

        GridAdapter gridAdapter = new GridAdapter(getActivity(),values,images);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),values[i],Toast.LENGTH_SHORT).show();
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        return view;
    }

    private void getBooks(){

    }

    public void showDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_book);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        showDialog();
    }
}
