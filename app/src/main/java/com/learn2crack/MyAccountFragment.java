package com.learn2crack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learn2crack.fragments.ChangePasswordDialog;
import com.learn2crack.model.Response;
import com.learn2crack.model.User;
import com.learn2crack.network.NetworkUtil;
import com.learn2crack.network.RetrofitInterface;
import com.learn2crack.utils.Constants;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by timur on 10-Jul-17.
 */

public class MyAccountFragment extends Fragment implements ChangePasswordDialog.Listener {

    private static final String TAG = "MyAccountFragment";

    private SharedPreferences mSharedPreferences;
    private String mToken;
    private String mEmail;
    private CompositeSubscription mSubscriptions;

    private TextView mTvName;
    private TextView mTvEmail;
    private TextView mTvDate;

    private Button mBtLogout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myaccount_fragment,container,false);
        mBtLogout = (Button) view.findViewById(R.id.btn_logout);

        initSharedPreferences();

        mTvName = (TextView) view.findViewById(R.id.u_name);
        mTvEmail = (TextView) view.findViewById(R.id.u_email);
        mTvDate = (TextView) view.findViewById(R.id.u_date);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<User>> call = retrofitInterface.getUserInfo(mEmail);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        return view;

    }

    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPasswordChanged() {
    }


}
