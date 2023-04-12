package com.example.braintrainers.trainers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrainers.R;

import java.util.ArrayList;
import java.util.List;

public class TrainersActivity extends AppCompatActivity {

    private RecyclerView rvTrainer;
    private List<Person> itemTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        rvTrainer = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvTrainer.setLayoutManager(llm);
        rvTrainer.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

    }

    private void initializeData(){
        itemTable = new ArrayList<>();
        //itemTable.add(new Person("1234", false));
        //itemTable.add(new Person("4534",false));
        //itemTable.add(new Person("6743",true));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemTable);
        rvTrainer.setAdapter(adapter);
    }
}