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

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView resultTextView;

    AsyncTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        resultTextView = findViewById(R.id.tv_pattern_result);
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void handleClick(View v) {
        // TODO: Get EditText values and start AsyncTask on them
        progressBar.setVisibility(View.VISIBLE);
        if (task != null) return;
        task = new GetPatternsTask(this);
        task.execute();
    }

    public void handleResult(PatternsSearchResult result) {
        StringBuilder string = new StringBuilder();
        for (Pattern pattern : result.getPatterns()) {
            string.append(pattern.getName() + "\n");
        }
        progressBar.setVisibility(View.GONE);
        setResultText(string.toString());
        task = null;
    }

    public void handleFailedAsyncTask() {
        progressBar.setVisibility(View.GONE);
        makeToast("Connecting to the API failed.");
        task = null;
    }

    private void setResultText(String text) {
        resultTextView.clearAnimation();
        resultTextView.setText(text);
        resultTextView.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
    }
}
