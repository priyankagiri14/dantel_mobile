package com.app.dantel.mobile.Web_Services;

import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.app.dantel.mobile.Web_Services.IBaseURL.SMARTCALL_BASE_URL;

public class RetrofitSmartCallToken {
    public static String token;

    private static Retrofit retrofit = null;
    private static String getSavedToken;

    public static Retrofit getClient()
    {   if(retrofit== null)
    {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        SharedPreferences sharedPreferences=MyApp.getContext().getSharedPreferences("smartCallLogin", 0);

        getSavedToken= sharedPreferences.getString("smartCallToken",null);
        if(getSavedToken!=null) {
            Log.d("RetrofitSmartCallToken", "getClienttoken: "+getSavedToken);

            httpClient.addInterceptor(new HeaderIntercepter())

                    //here we adding Interceptor for full level logging
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(SMARTCALL_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();
        }

    }
        return retrofit;


    }
}

