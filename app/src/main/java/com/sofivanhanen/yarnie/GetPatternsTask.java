package com.sofivanhanen.yarnie;

import android.os.AsyncTask;
import android.util.Base64;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sofvanh on 24/01/18.
 */

public class GetPatternsTask extends AsyncTask {

    // Context to affect UI (show toasts, show/hide progress bar etc.)
    private MainActivity context;
    private String result;

    // We use Retrofit to easily connect to the API.
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.ravelry.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public GetPatternsTask(MainActivity context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        RavelryApiService service = retrofit.create(RavelryApiService.class);
        // Using HTTP basic authentication
        String keys = context.getString(R.string.API_username) + ":" + context.getString(R.string.API_password);
        String authHeader = "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP);
        if (authHeader.length() == 1) {
            result = "API keys missing";
            return null;
        }
        Call<Object> call = service.getPatterns(0, authHeader);

        try {
            Response<Object> response = call.execute();

            if (response.isSuccessful()) {
                result = "Connection established!";
                // TODO: parse the result
            } else {
                result = "Query unsuccessful: " + response.message() + " " + response.code();
            }
            return response.body();
        } catch (IOException e) {
            result = "IOException";
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        context.makeToast("Result: " + result);
    }
}
