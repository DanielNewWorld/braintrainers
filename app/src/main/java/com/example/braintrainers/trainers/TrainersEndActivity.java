package com.example.braintrainers.trainers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.braintrainers.R;

public class TrainersEndActivity extends Activity {

    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_end);
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
        {
            //    super.onBackPressed();
            moveTaskToBack(true);
            finish();
            System.runFinalizersOnExit(true);
            System.exit(0);
        }
        else
            Toast.makeText(getBaseContext(), R.string.back_txt,
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
