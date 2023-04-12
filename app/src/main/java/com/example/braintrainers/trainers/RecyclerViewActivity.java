package com.example.braintrainers.trainers;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrainers.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {
    private List<Person> persons;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);

        GridLayoutManager llm = new GridLayoutManager(this, 3);
        rv.setLayoutManager(llm);
        //rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("1278"));
        persons.add(new Person("9878"));
        persons.add(new Person("5656"));
        persons.add(new Person("9877"));
        persons.add(new Person("8789"));
        persons.add(new Person("9899"));
        persons.add(new Person("5656"));
        persons.add(new Person("4654"));
        persons.add(new Person("8989"));
        persons.add(new Person("9354"));
        persons.add(new Person("6678"));
        persons.add(new Person("8945"));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
}
