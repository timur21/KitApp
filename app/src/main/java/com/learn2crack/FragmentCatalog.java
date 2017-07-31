package com.learn2crack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn2crack.model.Book;
import com.learn2crack.network.RetrofitInterface;
import com.learn2crack.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCatalog extends Fragment {

    private RecyclerView recyclerView;


    public FragmentCatalog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_catalog, container, false);

        configViews();

        return v;
    }

    public void getBookService(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<Book>> call = retrofitInterface.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> books = response.body();

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });

    }

    private void configViews() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setAdapter(new BookAdapter());
    }

}
