package com.learn2crack;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.learn2crack.model.Book;
import com.learn2crack.model.User;
import com.learn2crack.network.NetworkUtil;
import com.learn2crack.network.RetrofitInterface;
import com.learn2crack.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.learn2crack.R.id.spinner_genre;
import static com.learn2crack.R.id.spinner_lang;
import static com.learn2crack.utils.Validation.validateFields;

/**
 * Created by timur on 10-Jul-17.
 */

public class BookDetailsDialog extends DialogFragment{


    private SharedPreferences mSharedPreferences;
    private static String mToken;
    private static String mEmail;

    private TextView book_title;
    private TextView book_author;
    private TextView book_genre;
    private TextView book_lang;
    private TextView book_price;


    public BookDetailsDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_book_details_dialog,container);

        initSharedPreferences();

        book_title = (TextView) v.findViewById(R.id.d_book_title);
        book_author = (TextView) v.findViewById(R.id.d_book_author);
        book_genre = (TextView) v.findViewById(R.id.d_book_genre);
        book_lang = (TextView) v.findViewById(R.id.d_book_lang);
        book_price = (TextView) v.findViewById(R.id.d_book_price);

        return v;
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");
    }
}