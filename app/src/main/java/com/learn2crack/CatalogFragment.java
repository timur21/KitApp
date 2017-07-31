package com.learn2crack;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn2crack.model.Book;
import com.learn2crack.model.Response;
import com.learn2crack.model.User;
import com.learn2crack.network.RetrofitInterface;
import com.learn2crack.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.subscriptions.CompositeSubscription;

import static com.learn2crack.MyBooksFragment.KEY_BOOK_ID;

/**
 * Created by timur on 10-Jul-17.
 */

public class CatalogFragment extends Fragment{
    private static final String TAG = "CatalogFragment";

    private SharedPreferences mSharedPreferences;
    private static String mToken;
    private static String mEmail;

    private CompositeSubscription mSubscriptions;

    public static final String KEY_BOOK_TITLE="key_book_title";
    public static final String KEY_BOOK_AUTHOR="key_book_author";
    public static final String KEY_BOOK_GENRE="key_book_genre";
    public static final String KEY_BOOK_LANG="key_book_lang";
    public static final String KEY_BOOK_PRICE="key_book_price";

    private CatalogAdapter catalogAdapter;

    GridView gridView;
    private List<Book> books;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_fragment,container,false);
        initSharedPreferences();

        Toast.makeText(getActivity(),"Hint: Tap on the book to get information",Toast.LENGTH_LONG).show();

        gridView = (GridView) view.findViewById(R.id.catalog_books);

        catalogAdapter = new CatalogAdapter(getActivity(),values,images);
        gridView.setAdapter(catalogAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog();
                Toast.makeText(getActivity(),values[i],Toast.LENGTH_SHORT).show();
            }
        });

        getBooks();
        return view;
    }

    public void showDialog(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        BookDetailsDialog bookDetailsDialog = new BookDetailsDialog();
        bookDetailsDialog.show(fm,"BookDetailsDialog");
    }

    private void getBooks(){

        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Fetching Data","Please wait...",false,false);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client());
        Retrofit retrofit = builder.build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<Book>> call = retrofitInterface.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, retrofit2.Response<List<Book>> response) {
                showList();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }

    private void showList(){

        String[] items = new String[books.size()];
        for(int i=0; i<books.size(); i++){
            items[i] = books.get(i).getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_list,items);
    }

    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");

    }

    public static OkHttpClient Client() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain
                                .request().newBuilder()
                                .addHeader("x-access-token", mToken)
                                .addHeader("Accept", "application/json;version=1");
                        return chain.proceed(request.build());
                    }
                })
                .build();
    }
}


