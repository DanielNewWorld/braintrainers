package com.example.braintrainers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.braintrainers.trainers.TrainersActivity;

public class DescriptionTrainers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_trainers);

        Button btnTrainingStart;
        btnTrainingStart = findViewById(R.id.training_btn_start);
        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TrainersActivity.class);
                context.startActivity(intent);
            }
        };
        btnTrainingStart.setOnClickListener(onClickGo);
    }
}
