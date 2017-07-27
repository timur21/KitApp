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
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.BaseAdapter;
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

public class CatalogFragment extends Fragment implements ListView.OnItemClickListener{
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

    private ListView listView;
    private List<Book> books;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_fragment,container,false);
        initSharedPreferences();
        listView = (ListView) view.findViewById(R.id.listViewBooks);

        //getBooks();

        listView.setOnItemClickListener(this);

        return view;
    }

    private void getBooks(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Fetching Data","Please wait...",false,false);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client());
        Retrofit retrofit = builder.build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);


        retrofitInterface.getBooks(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, retrofit2.Response<List<Book>> response) {

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

        listView.setAdapter(adapter);
    }

    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");

    }

    public static OkHttpClient Client(){
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain
                                .request().newBuilder()
                                .addHeader("x-access-token",mToken)
                                .addHeader("Accept","application/json;version=1");
                        return chain.proceed(request.build());
                    }
                })
                .build();
    }
    public void getBooksList(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client());
        Retrofit retrofit = builder.build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//        Intent intent = new Intent(getActivity(), ShowBookDetails.class);
//
//        Book book = books.get(i);
//
//        intent.putExtra(KEY_BOOK_TITLE,book.getTitle());
//        intent.putExtra(KEY_BOOK_AUTHOR,book.getAuthor());
//        intent.putExtra(KEY_BOOK_GENRE,book.getGenre());
//        intent.putExtra(KEY_BOOK_LANG,book.getLang());
//        intent.putExtra(KEY_BOOK_PRICE,book.getPrice());
//
//        startActivity(intent);
    }
}


