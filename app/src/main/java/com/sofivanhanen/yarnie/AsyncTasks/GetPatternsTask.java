package com.sofivanhanen.yarnie.AsyncTasks;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.sofivanhanen.yarnie.API.PatternsSearchResult;
import com.sofivanhanen.yarnie.API.RavelryApiService;
import com.sofivanhanen.yarnie.MainActivity;
import com.sofivanhanen.yarnie.R;

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
    private String yarnWeight;

    // We use Retrofit to easily connect to the API.
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.ravelry.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public GetPatternsTask(MainActivity context, String selectedYarnWeight) {
        this.context = context;
        // In spinner, we have 'Super Bulky' - query takes 'super-bulky'
        yarnWeight = selectedYarnWeight.replaceAll(" ", "-").toLowerCase();
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
        
        Call<PatternsSearchResult> call = service.getPatterns(RavelryApiService.NUMBER_OF_COLORS,
                RavelryApiService.MAX_NUMBER_OF_PATTERNS, yarnWeight, authHeader);

        try {
            Response<PatternsSearchResult> response = call.execute();
            if (response.isSuccessful()) {
                // yay success
            } else {
                // oh no
            }
            return response.body();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result == null || !result.getClass().equals(PatternsSearchResult.class)) {
            context.handleFailedAsyncTask();
            Log.e(this.getClass().toString(), "Result was not a PatternsSearchResult object!");
        } else {
            context.handleResult((PatternsSearchResult) result);
        }
    }
}
