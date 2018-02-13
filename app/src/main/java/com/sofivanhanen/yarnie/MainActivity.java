package com.sofivanhanen.yarnie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sofivanhanen.yarnie.API.FullPatternsResult;
import com.sofivanhanen.yarnie.API.GetDetailedPatternsTask;
import com.sofivanhanen.yarnie.API.GetPatternsTask;
import com.sofivanhanen.yarnie.API.PatternsSearchResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText amountOfYarnEditText;
    ProgressBar progressBar;
    TextView resultTextView;

    AsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountOfYarnEditText = findViewById(R.id.et_amount_of_yarn);
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
        task = new GetPatternsTask(this);
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
        printListOfPatterns(result.getPatternsAsList());
        task = null;
        // TODO: Run patterns through algorithm here.
    }

    // API calls generally return arrays, but algorithms run easier on lists.
    private List<Pattern> arrayToList(Pattern[] patterns) {
        ArrayList<Pattern> list = new ArrayList<>();
        for (Pattern pattern : patterns) {
            list.add(pattern);
        }
        return list;
    }

    private void printListOfPatterns(List<Pattern> patterns) {
        StringBuilder string = new StringBuilder();
        for (Pattern pattern : patterns) {
            string.append(pattern.getName() + "\n");
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
