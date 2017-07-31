package com.learn2crack;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.learn2crack.model.Book;
import com.learn2crack.network.RetrofitInterface;
import com.learn2crack.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    private EditText book_title;
    private EditText book_price;
    private EditText book_author;
    private TextInputLayout title;
    private TextInputLayout author;
    private TextInputLayout price;
    private Spinner spinner_genre;
    private Spinner spinner_lang;

    private ImageView imageCamera;
    private ImageView imageGallery;

    private SharedPreferences mSharedPreferences;
    private static String mToken;
    private static String mEmail;

    final int CAMERA_REQUEST=13323;

    CameraPhoto cameraPhoto;


    public BookDialog(){

    }

    Button btn_add;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dialog_book,container);

        book_title = (EditText) v.findViewById(R.id.book_title);
        book_price = (EditText) v.findViewById(R.id.book_price);
        book_author = (EditText) v.findViewById(R.id.book_author);

        title = (TextInputLayout) v.findViewById(R.id.title);
        author = (TextInputLayout) v.findViewById(R.id.author);
        price = (TextInputLayout) v.findViewById(R.id.price);

        imageCamera = (ImageView) v.findViewById(R.id.imageCamera);
        imageGallery = (ImageView) v.findViewById(R.id.imageGallery);

        cameraPhoto = new CameraPhoto(getContext());

        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spinner_genre = (Spinner) v.findViewById(R.id.spinner_genre);
        spinner_lang = (Spinner) v.findViewById(R.id.spinner_lang);

        btn_add = (Button) v.findViewById(R.id.btnadd);

        initSharedPreferences();

        btn_add.setOnClickListener(this);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return v;
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");
    }

    public static OkHttpClient getClient(){
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

    private void sendNetworkRequest(Book book){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient());
        Retrofit retrofit = builder.build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Book> call = retrofitInterface.registerBook(book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Snackbar.make(getView(),"Book registered!",Snackbar.LENGTH_SHORT).show();
                book_title.setText(null);
                book_author.setText(null);
                book_price.setText(null);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Snackbar.make(getView(),"Something went wrong!",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValid() {

        setError();

        String title = book_title.getText().toString();
        String author = book_author.getText().toString();
        String price = book_price.getText().toString();

        int err = 0;

        if (!validateFields(title)) {
            err++;
        }
        if (!validateFields(author)) {
            err++;
        }
        if (!validateFields(price)) {
            err++;
        }
        if(err == 0){
            return true;
        }
        else{
            return false;
        }
    }

    private void setError() {
        book_title.setError(null);
        book_author.setError(null);
        book_price.setError(null);
    }

    private void showSnackBarMessage(String message) {

        if (getView() != null) {

            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        String lang_choice = spinner_lang.getSelectedItem().toString();
        String genre_choice = spinner_genre.getSelectedItem().toString();

        Book book = new Book(book_title.getText().toString(),
                book_author.getText().toString(),
                lang_choice,
                genre_choice,
                book_price.getText().toString());
        sendNetworkRequest(book);
    }
}