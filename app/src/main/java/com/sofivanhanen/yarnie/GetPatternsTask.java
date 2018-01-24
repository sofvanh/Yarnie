package com.sofivanhanen.yarnie;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by sofvanh on 24/01/18.
 */

public class GetPatternsTask extends AsyncTask {

    private MainActivity context;
    private String result;

    public GetPatternsTask(MainActivity context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        result = "to be implemented";
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        context.makeToast("Result: " + result);
    }
}
