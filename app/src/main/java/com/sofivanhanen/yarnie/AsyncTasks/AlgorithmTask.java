package com.sofivanhanen.yarnie.AsyncTasks;

import android.os.AsyncTask;

import com.sofivanhanen.yarnie.MainActivity;
import com.sofivanhanen.yarnie.Data.PatternList;
import com.sofivanhanen.yarnie.Utils.AlgoUtils;

/**
 * Created by sofvanh on 19/02/18.
 */

public class AlgorithmTask extends AsyncTask {

    // The task that will run the knapsack algorithm on a background thread

    private MainActivity context;
    private PatternList patterns;
    private int maxYardage;

    public AlgorithmTask(MainActivity context, PatternList patterns, int maxYardage) {
        this.context = context;
        this.patterns = patterns;
        this.maxYardage = maxYardage;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        // This takes a while when maxYardage is large
        return AlgoUtils.patternKnapsackWeightOnly(patterns.returnAsArray(), maxYardage);
    }

    @Override
    protected void onPostExecute(Object result) {
        context.handleResult((PatternList)result);
    }
}
