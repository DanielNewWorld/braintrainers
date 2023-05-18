package com.daniel.braintrainers.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;
import com.daniel.braintrainers.trainers.FindNumberActivity_0;
import com.daniel.braintrainers.trainers.KvitnykActivity_1;

import java.util.Locale;

public class DescriptionTrainers extends AppCompatActivity {
    TextView txtNameTrainer, txtDesc1, txtDesc2, txtDesc3, txtQuest, txtAuthorDesc;
    Button btnTrainingStart;
    Intent intent;
    String switchTrainer, dad;
    String[] trainers_name, authorDesc, trainers_quest, meta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_trainers);

        Configuration config = new Configuration();
        config.setLocale(new Locale(MainActivity.localeSet));
        Resources res = getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        btnTrainingStart = findViewById(R.id.training_btn_start);
        txtNameTrainer = (TextView) findViewById(R.id.txtNameTrainers);
        txtAuthorDesc = (TextView) findViewById(R.id.txtAuthorDesc);
        txtDesc1 = (TextView) findViewById(R.id.txt_trainer_decription_1);
        txtDesc2 = (TextView) findViewById(R.id.txt_trainer_decription_2);
        txtDesc3 = (TextView) findViewById(R.id.txt_trainer_decription_3);
        txtQuest = (TextView) findViewById(R.id.txt_trainer_quest);

        intent = getIntent();
        switchTrainer = intent.getStringExtra("numberTrainer");
        dad = intent.getStringExtra("dad");

        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                switch (switchTrainer) {
                    case "1":
                        intent = new Intent(context, FindNumberActivity_0.class);
                        intent.putExtra("numberTrainer", switchTrainer);
                        intent.putExtra("dad", dad);
                        context.startActivity(intent);
                        break;

                    case "2":
                        intent = new Intent(context, KvitnykActivity_1.class);
                        intent.putExtra("numberTrainer", switchTrainer);
                        intent.putExtra("dad", dad);
                        context.startActivity(intent);
                        break;

                    default:
                        break;
                }

            }
        };
        btnTrainingStart.setOnClickListener(onClickGo);

        trainers_name = getResources().getStringArray(R.array.trainers_name_array);
        trainers_quest = getResources().getStringArray(R.array.trainers_txt_quest_array);
        meta = getResources().getStringArray(R.array.meta_array);
        authorDesc = getResources().getStringArray(R.array.author_desc_array);

        switch (switchTrainer) {
            case "1":
                txtNameTrainer.setText(trainers_name[0]);
                txtQuest.setText(trainers_quest[0]);
                txtDesc1.setText(meta[0]);
                txtDesc2.setText(meta[1]);
                txtDesc3.setText(meta[2]);
                btnTrainingStart.setText(R.string.btn_next);
                txtAuthorDesc.setText(authorDesc[0]);
                break;
            case "2":
                txtNameTrainer.setText(trainers_name[1]);
                txtQuest.setText(trainers_quest[1]);
                txtDesc1.setText(meta[3]);
                txtDesc2.setText(meta[2]);
                txtDesc3.setText(meta[4]);
                btnTrainingStart.setText(R.string.btn_next);
                txtAuthorDesc.setText(authorDesc[1]);
                break;

            default:
                break;
        }

    }
}