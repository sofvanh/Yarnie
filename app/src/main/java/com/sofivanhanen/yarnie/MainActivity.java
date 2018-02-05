package com.sofivanhanen.yarnie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: move this from here
        GetPatternsTask task = new GetPatternsTask(this);
        task.execute();
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void handleClick(View v) {
        if (v.getId() == R.id.button_search) {
            TextView tv = findViewById(R.id.tv_pattern_result);
            EditText et = findViewById(R.id.et_amount_of_yarn);
            tv.clearAnimation();
            tv.setText(tv.getText() + "\n" + "Button clicked! Text was: " + et.getText());
            tv.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        }
    }
}
