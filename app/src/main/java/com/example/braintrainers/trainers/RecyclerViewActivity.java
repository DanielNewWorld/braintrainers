package com.example.braintrainers.trainers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrainers.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {
    private List<Person> persons;
    private RecyclerView rv;
    TextView txtInfoNumber;

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
        int[] color  = new int[] {Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,
        Color.GREEN,Color.LTGRAY,Color.RED, Color.MAGENTA,Color.WHITE,
        Color.YELLOW};
        int y = -65536;
        int x = 0;
        int z = 0;
        persons = new ArrayList<>();
        boolean booleanWIN = false;

        txtInfoNumber = (TextView) findViewById(R.id.txt_info_number);
        z = (int) (Math.random()*27);

        for (int i = 0; i < 27; i++) {
            x = (int) (2000 + Math.random()*4000);
            y = (int) (Math.random()*11);
            if (i == z) {
                booleanWIN = true;
                txtInfoNumber.setText(Integer.toString(x));
            }
                else booleanWIN = false;
            persons.add(new Person(Integer.toString(x), color[y], z, booleanWIN));
        }

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
}
