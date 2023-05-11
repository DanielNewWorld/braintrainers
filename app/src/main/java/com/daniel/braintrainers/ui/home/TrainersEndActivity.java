package com.daniel.braintrainers.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;
import com.daniel.braintrainers.ui.home.DescriptionTrainers;
import com.daniel.braintrainers.ui.trainers.TrainersListFragment;

public class TrainersEndActivity extends Activity {

    private static long back_pressed;
    TextView txtNeuron, txtAnswer, txtEnd;
    Button btnNextTrainer;
    String switchTrainer, dad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_end);

        txtAnswer = (TextView) findViewById(R.id.trainer_txt_answer);
        txtNeuron = (TextView) findViewById(R.id.trainer_txt_neuron);
        txtEnd = (TextView) findViewById(R.id.txtTrainerEnd);
        btnNextTrainer = (Button) findViewById(R.id.btnNextTrainer);

        Intent intent = getIntent();
        txtAnswer.setText(intent.getStringExtra("maxCountTrueAnswer") + " з " +
                intent.getStringExtra("countTrainerItem"));
        txtNeuron.setText(intent.getStringExtra("neuron"));

        switchTrainer = intent.getStringExtra("numberTrainer");
        dad = intent.getStringExtra("dad");

        if (dad.equals("TrainerList")) {
            btnNextTrainer.setText(R.string.training_btn_end);
            txtEnd.setText(R.string.training_txt_info_end);
        }

        switch (switchTrainer) {
            case "2":
                btnNextTrainer.setText(R.string.training_btn_end);
                txtEnd.setText(R.string.training_txt_info_end);
                break;

            default:
                break;
        }

        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent;

                if (dad.equals("TrainerList")) {
                    intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
                else {
                    switch (switchTrainer) {
                        case "2":
                            intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                            break;

                        default:
                            intent = new Intent(context, DescriptionTrainers.class);
                            intent.putExtra("numberTrainer", "2");
                            intent.putExtra("dad", dad);
                            context.startActivity(intent);
                            break;
                    }
                }
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