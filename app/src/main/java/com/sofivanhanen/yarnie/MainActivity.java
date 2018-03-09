package com.sofivanhanen.yarnie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sofivanhanen.yarnie.api.FullPatternsResult;
import com.sofivanhanen.yarnie.asynctasks.AlgorithmWeightOnlyTask;
import com.sofivanhanen.yarnie.asynctasks.AlgorithmWithValueTask;
import com.sofivanhanen.yarnie.asynctasks.GetDetailedPatternsTask;
import com.sofivanhanen.yarnie.asynctasks.GetPatternsTask;
import com.sofivanhanen.yarnie.api.PatternsSearchResult;
import com.sofivanhanen.yarnie.data.Pattern;
import com.sofivanhanen.yarnie.data.PatternList;
import com.sofivanhanen.yarnie.utils.AlgoUtils;

public class MainActivity extends AppCompatActivity {

    EditText amountOfYarnEditText;
    int yarnAmount;
    Spinner yardsOrMetersSpinner;
    boolean meters;
    Spinner yarnWeightSpinner;
    CheckBox prioritizeCheckBox;
    boolean prioritize;
    ProgressBar progressBar;
    TextView resultTextView;

    AsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountOfYarnEditText = findViewById(R.id.et_amount_of_yarn);

        yardsOrMetersSpinner = findViewById(R.id.spinner_yards_meters);
        ArrayAdapter<CharSequence> adapterYards = ArrayAdapter.createFromResource(this,
                R.array.meters_yards_array, R.layout.support_simple_spinner_dropdown_item);
        adapterYards.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yardsOrMetersSpinner.setAdapter(adapterYards);

        yarnWeightSpinner = findViewById(R.id.spinner_yarn_weights);
        ArrayAdapter<CharSequence> adapterWeights = ArrayAdapter.createFromResource(this,
                R.array.yarn_weights_array, R.layout.support_simple_spinner_dropdown_item);
        adapterWeights.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yarnWeightSpinner.setAdapter(adapterWeights);

        prioritizeCheckBox = findViewById(R.id.checkbox_prioritize);

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
        if (amount.equals("")) {
            // User didn't input amount of yarn!
            makeToast("Please give amount of yarn");
            return;
        }
        yarnAmount = Integer.parseInt(amount);
        meters = ((String) yardsOrMetersSpinner.getSelectedItem()).equals("Meters");
        prioritize = prioritizeCheckBox.isChecked();
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
        int yardage = yarnAmount;
        if (meters) yardage = AlgoUtils.metersToYards(yardage);
        // We run the algorithm on a separate thread so as to not block the UI.
        if (prioritize) {
            task = new AlgorithmWithValueTask(this,
                    result.getPatternsAsList(false),
                    yardage);
        } else {
            task = new AlgorithmWeightOnlyTask(this,
                    result.getPatternsAsList(false),
                    yardage);
        }
        task.execute();
    }

    // AlgorithmWeightOnlyTask returns a list of patterns.
    public void handleResult(PatternList result) {
        progressBar.setVisibility(View.GONE);
        printListOfPatterns(result);
        task = null;
    }

    private void printListOfPatterns(PatternList patterns) {
        StringBuilder string = new StringBuilder();
        if (patterns.isEmpty()) {
            string.append("No patterns! Try increasing the amount of yarn.");
        } else {
            if (meters) {
                string.append(yarnAmount + " meters = " + AlgoUtils.metersToYards(yarnAmount) + " yards\n");
            }
            for (Pattern pattern : patterns) {
                string.append(pattern.showcaseString() + "\n");
            }
            string.append(patterns.getTotalYards() + " yards total.");
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
