package com.sofivanhanen.yarnie.API;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.sofivanhanen.yarnie.MainActivity;
import com.sofivanhanen.yarnie.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sofvanh on 13/02/18.
 */

public class GetDetailedPatternsTask extends AsyncTask {

    // Context to affect UI (show toasts, show/hide progress bar etc.)
    private MainActivity context;
    // Pattern ids we're looking for
    private String ids;

    // We use Retrofit to easily connect to the API.
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.ravelry.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public GetDetailedPatternsTask(MainActivity context, String ids) {
        this.context = context;
        this.ids = ids;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        RavelryApiService service = retrofit.create(RavelryApiService.class);
        // Using HTTP basic authentication
        String keys = context.getString(R.string.API_username) + ":" + context.getString(R.string.API_password);
        String authHeader = "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP);
        if (authHeader.length() == 1) {
            Log.e(this.getClass().toString(), "API keys missing.");
            return null;
        }

        Call<FullPatternsResult> call = service.getPatternsById(ids, authHeader);

        try {
            Response<FullPatternsResult> response = call.execute();
            if (response.isSuccessful()) {
                Log.i(this.getClass().toString(), "Response successful!");
            } else {
                Log.e(this.getClass().toString(), "Response unsuccessful: " + response.errorBody().string());
            }
            return response.body();
        } catch (IOException e) {
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result == null || !result.getClass().equals(FullPatternsResult.class)) {
            context.handleFailedAsyncTask();
            Log.e(this.getClass().toString(), "Result was not a FullPatternsResult object!");
        } else {
            context.handleResult((FullPatternsResult)result);
        }
    }
}
