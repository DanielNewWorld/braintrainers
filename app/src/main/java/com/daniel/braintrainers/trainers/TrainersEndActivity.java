package com.daniel.braintrainers.trainers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.braintrainers.R;

public class TrainersEndActivity extends Activity {

    private static long back_pressed;
    TextView txtNeuron, txtAnswer;
    Button btnNextTrainer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_end);

        txtAnswer = (TextView) findViewById(R.id.trainer_txt_answer);
        txtNeuron = (TextView) findViewById(R.id.trainer_txt_neuron);
        btnNextTrainer = (Button) findViewById(R.id.btnNextTrainer);

        Intent intent = getIntent();
        txtAnswer.setText(intent.getStringExtra("maxCountTrueAnswer") + " з " +
                intent.getStringExtra("countTrainerItem"));
        txtNeuron.setText(intent.getStringExtra("neuron"));

        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DescriptionTrainers.class);
                intent.putExtra("numberTrainer", Integer.toString(2));
                context.startActivity(intent);
            }
        };
        btnNextTrainer.setOnClickListener(onClickGo);
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
