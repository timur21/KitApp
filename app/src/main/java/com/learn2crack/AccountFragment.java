package com.learn2crack;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.learn2crack.fragments.LoginFragment;
import com.learn2crack.fragments.RegisterFragment;
import com.learn2crack.model.Book;
import com.learn2crack.model.User;
import com.learn2crack.network.NetworkUtil;
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
import rx.Observable;
import rx.subscriptions.CompositeSubscription;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    public static final String TAG = AccountFragment.class.getSimpleName();

    private TextView Name;
    private TextView Email;
    private TextView Date;
    private Button logout;


    private SharedPreferences mSharedPreferences;
    private static String mToken;
    private static String mEmail;

    private CompositeSubscription mSubscriptions;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account,container,false);


        mSubscriptions = new CompositeSubscription();
        initSharedPreferences();

        Name = (TextView) view.findViewById(R.id.a_name);
        Email = (TextView) view.findViewById(R.id.a_email);
        Date = (TextView) view.findViewById(R.id.a_date);

        logout = (Button) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        getProfile();

        return view;
    }

    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");

    }

    public void logout(){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.TOKEN,"");
        editor.apply();
        getActivity().finish();

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
    public void getProfile(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client());
        Retrofit retrofit = builder.build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<User> call = retrofitInterface.getUserInfo(mEmail);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Toast.makeText(getActivity(),"User loaded!",Toast.LENGTH_SHORT).show();
                User user= response.body();
                Name.setText(user.getEmail());
                Email.setText(user.getName());
                Date.setText(user.getCreated_at());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getActivity(),"User not loaded!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
