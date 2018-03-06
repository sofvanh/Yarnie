package com.sofivanhanen.yarnie.AsyncTasks;

import android.os.AsyncTask;

import com.sofivanhanen.yarnie.Data.PatternList;
import com.sofivanhanen.yarnie.MainActivity;
import com.sofivanhanen.yarnie.Utils.AlgoUtils;

/**
 * Created by sofvanh on 06/03/18.
 */

public class AlgorithmWithValueTask extends AsyncTask {

    // The task that will run the full knapsack algorithm on a background thread

    private MainActivity context;
    private PatternList patterns;
    private int maxYardage;

    public AlgorithmWithValueTask(MainActivity context, PatternList patterns, int maxYardage) {
        this.context = context;
        this.patterns = patterns;
        this.maxYardage = maxYardage;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        // This takes a while when maxYardage is large
        return AlgoUtils.patternKnapsack(patterns.returnAsArray(), maxYardage);
    }

    @Override
    protected void onPostExecute(Object result) {
        context.handleResult((PatternList)result);
    }
}
