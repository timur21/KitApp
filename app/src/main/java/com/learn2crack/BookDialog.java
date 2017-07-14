package com.learn2crack;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.learn2crack.model.Book;
import com.learn2crack.model.User;
import com.learn2crack.network.RetrofitInterface;
import com.learn2crack.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.learn2crack.utils.Validation.validateFields;

/**
 * Created by timur on 10-Jul-17.
 */

public class BookDialog extends DialogFragment implements View.OnClickListener{

//    public static EditText book_title;
//    public static EditText book_author;
//    public static EditText book_price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dialog_book,null);

        final EditText book_title = (EditText) v.findViewById(R.id.book_title);
        final EditText book_price = (EditText) v.findViewById(R.id.book_price);
        final EditText book_author = (EditText) v.findViewById(R.id.book_author);

        final Spinner spinner_genre = (Spinner) v.findViewById(R.id.spinner_genre);
        final Spinner spinner_lang = (Spinner) v.findViewById(R.id.spinner_lang);



        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.langs,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_lang.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.genres,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genre.setAdapter(adapter2);

        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view;
                Toast.makeText(getActivity(),text.getText(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view;
                Toast.makeText(getActivity(),text.getText(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String lang_choice = spinner_lang.getSelectedItem().toString();
        String genre_choice = spinner_genre.getSelectedItem().toString();

        Book book = new Book(book_title.getText().toString(),
                book_author.getText().toString(),
                lang_choice,
                genre_choice,
                Integer.parseInt(book_price.getText().toString()));

        sendNetworkRequest(book);

        return v;
    }

    private void sendNetworkRequest(Book book){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Book> call = retrofitInterface.registerBook(book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Snackbar.make(getView(),"Book registered!",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Snackbar.make(getView(),"Something went wrong!",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    public void onClick(View view) {

    }

//    private boolean isValid() {
//
//        setError();
//
//        String title = book_title.getText().toString();
//        String author = book_author.getText().toString();
//        String price = book_price.getText().toString();
//
//        int err = 0;
//
//        if (!validateFields(title)) {
//            err++;
//        }
//
//        if (!validateFields(author)) {
//            err++;
//        }
//
//        if (!validateFields(price)) {
//            err++;
//        }
//        if(err == 0){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    private void setError() {
//
//        book_title.setError(null);
//        book_author.setError(null);
//        book_price.setError(null);
//    }

    private void showSnackBarMessage(String message) {

        if (getView() != null) {

            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }
}
