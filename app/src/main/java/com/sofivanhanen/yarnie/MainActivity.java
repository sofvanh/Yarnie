package com.sofivanhanen.yarnie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sofivanhanen.yarnie.API.FullPatternsResult;
import com.sofivanhanen.yarnie.API.GetDetailedPatternsTask;
import com.sofivanhanen.yarnie.API.GetPatternsTask;
import com.sofivanhanen.yarnie.API.PatternsSearchResult;
import com.sofivanhanen.yarnie.Utils.AlgoUtils;
import com.sofivanhanen.yarnie.Utils.MiscUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText amountOfYarnEditText;
    Spinner yarnWeightSpinner;
    ProgressBar progressBar;
    TextView resultTextView;

    AsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountOfYarnEditText = findViewById(R.id.et_amount_of_yarn);
        yarnWeightSpinner = findViewById(R.id.spinner_yarn_weights);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.yarn_weights_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yarnWeightSpinner.setAdapter(adapter);
        progressBar = findViewById(R.id.progress_bar);
        resultTextView = findViewById(R.id.tv_pattern_result);
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void handleClick(View v) {
        if (task != null) return; // There's a task running already.
        String amount = amountOfYarnEditText.getText().toString();
        if (amount.toString().equals("")) {
            // User didn't input amount of yarn!
            makeToast("Please give amount of yarn");
            return;
        }
        // Start the API request
        progressBar.setVisibility(View.VISIBLE);
        task = new GetPatternsTask(this, (String) yarnWeightSpinner.getSelectedItem());
        task.execute();
    }

    // handleResult is only called after a successful query.
    // GetPatternsTask returns PatternsSearchResult.
    public void handleResult(PatternsSearchResult result) {
        // GetPatternsTask is useful for looking up projects by parameters.
        // However, the results are insufficient Pattern objects.
        // They do not contain all the details (for instance, the yardage.)
        // Therefore, we request new, detailed versions of those patterns,
        // searching by id.
        // This is a weakness in the Ravelry API.
        task = new GetDetailedPatternsTask(this, result.getIdsAsString());
        task.execute();
    }

    // GetDetailedPatternsTask returns FullPatternsResult.
    public void handleResult(FullPatternsResult result) {
        progressBar.setVisibility(View.GONE);
        // Getting patterns from result object; Turning that list into an array;
        // Getting the max amount of yarn from the text view; And running the algorithm on them.
        // TODO: Do this on a separate thread
        printListOfPatterns(AlgoUtils.patternKnapsackWeightOnly(
                MiscUtils.listToArray(result.getPatternsAsList(false)),
                Integer.parseInt(amountOfYarnEditText.getText().toString())));
        task = null;
    }

    private void printListOfPatterns(List<Pattern> patterns) {
        StringBuilder string = new StringBuilder();
        for (Pattern pattern : patterns) {
            string.append(pattern.getName() + ", Yardage: " + pattern.getYardage() + "\n");
        }
        setResultText(string.toString());
    }

    // For general API failures
    public void handleFailedAsyncTask() {
        handleFailedAsyncTask("Connecting to the API failed.");
    }

    // For specific API failure messages
    public void handleFailedAsyncTask(String message) {
        progressBar.setVisibility(View.GONE);
        makeToast(message);
        task = null;
    }

    private void setResultText(String text) {
        resultTextView.clearAnimation();
        resultTextView.setText(text);
        resultTextView.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
    }
}
