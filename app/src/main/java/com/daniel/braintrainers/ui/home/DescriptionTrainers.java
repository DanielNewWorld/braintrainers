package com.daniel.braintrainers.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daniel.braintrainers.R;
import com.daniel.braintrainers.trainers.FindNumberActivity_0;
import com.daniel.braintrainers.trainers.KvitnykActivity_1;

public class DescriptionTrainers extends AppCompatActivity {
    TextView txtNameTrainer, txtDesc1, txtDesc2, txtDesc3, txtQuest;
    Button btnTrainingStart;
    Intent intent, intentNext;
    String switchTrainer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_trainers);

        btnTrainingStart = findViewById(R.id.training_btn_start);
        txtNameTrainer = (TextView) findViewById(R.id.txtNameTrainers);
        txtDesc1 = (TextView) findViewById(R.id.txt_trainer_decription_1);
        txtDesc2 = (TextView) findViewById(R.id.txt_trainer_decription_2);
        txtDesc3 = (TextView) findViewById(R.id.txt_trainer_decription_3);
        txtQuest = (TextView) findViewById(R.id.txt_trainer_quest);

        intent = getIntent();
        switchTrainer = intent.getStringExtra("numberTrainer");

        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                switch (switchTrainer) {
                    case "1":
                        intentNext = new Intent(context, FindNumberActivity_0.class);
                        context.startActivity(intentNext);
                        break;

                    case "2":
                        intentNext = new Intent(context, KvitnykActivity_1.class);
                        context.startActivity(intentNext);
                        break;

                    default:
                        break;
                }

            }
        };
        btnTrainingStart.setOnClickListener(onClickGo);

        String []trainers_name = getResources().getStringArray(R.array.trainers_name_array);
        String []trainers_quest = getResources().getStringArray(R.array.trainers_txt_quest_array);
        String []meta = getResources().getStringArray(R.array.meta_array);

        switch (switchTrainer) {
            case "1":
                txtNameTrainer.setText(trainers_name[0]);
                txtQuest.setText(trainers_quest[0]);
                txtDesc1.setText(meta[0]);
                txtDesc2.setText(meta[1]);
                txtDesc3.setText(meta[2]);
                //btnTrainingStart.setText(R.string.training_btn_start);
                break;
            case "2":
                txtNameTrainer.setText(trainers_name[1]);
                txtQuest.setText(trainers_quest[1]);
                txtDesc1.setText(meta[3]);
                txtDesc2.setText(meta[2]);
                txtDesc3.setText(meta[4]);
                btnTrainingStart.setText(R.string.training_btn_start);
                break;

            default:
                break;
        }

    }
}