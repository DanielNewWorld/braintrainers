package com.example.braintrainers.trainers;

import static java.util.concurrent.TimeUnit.SECONDS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrainers.DescriptionTrainers;
import com.example.braintrainers.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class RecyclerViewActivity extends Activity {
    private List<Person> persons;
    private RecyclerView rv;
    TextView txtInfoNumber, txtTime;
    LinearLayout llPanel;
    final long startTime = System.nanoTime();
    int level = 0;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);
        txtInfoNumber = (TextView) findViewById(R.id.txt_info_number);
        txtTime = (TextView) findViewById(R.id.txtTime);
        llPanel = (LinearLayout) findViewById(R.id.ll_panel_trainers);

        GridLayoutManager llm = new GridLayoutManager(this, 3);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    public void initializeData(){
        int[] color  = new int[] {Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,
        Color.GREEN,Color.LTGRAY,Color.RED, Color.MAGENTA,Color.WHITE, Color.TRANSPARENT,
        Color.YELLOW};
        int y = -65536;
        int x = 0;
        int z = 0;
        int count;
        persons = new ArrayList<>();
        boolean booleanWIN = false;
        int rotation = 0;

        switch (level) {
            case 0:
                count = 9;
                z = (int) (Math.random()*count);
                break;

            case 1:
                count = 12;
                z = (int) (Math.random()*count);
                break;

            case 2:
                count = 15;
                z = (int) (Math.random()*count);
                break;

            case 3:
                count = 18;
                z = (int) (Math.random()*count);
                break;

            case 4:
                count = 21;
                z = (int) (Math.random()*count);
                break;

            default:
                count = 24;
                z = (int) (Math.random()*count);
                break;
        }

        for (int i = 0; i < count; i++) {
            x = (int) (2000 + Math.random()*4000);
            y = (int) (Math.random()*11);
            rotation = (int) (Math.random()*50 - 25);
            if (i == z) {
                booleanWIN = true;
                txtInfoNumber.setText(Integer.toString(x));
            }
                else booleanWIN = false;
            persons.add(new Person(Integer.toString(x), color[y], z, booleanWIN, rotation));
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        public class PersonViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            TextView personName;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                personName = (TextView)itemView.findViewById(R.id.person_name);
            }
        }

        List<Person> persons;

        RVAdapter(List<Person> persons){
            this.persons = persons;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, @SuppressLint("RecyclerView") int i) {
            personViewHolder.personName.setText(persons.get(i).name);

            if (level >= 2) {personViewHolder.personName.setRotation(persons.get(i).rotation);}

            if (level >= 4) {
                personViewHolder.personName.setBackgroundColor(persons.get(i).color);

                if (persons.get(i).color == Color.WHITE) {
                    personViewHolder.personName.setTextColor(Color.BLACK);}
                else personViewHolder.personName.setTextColor(Color.WHITE);
            }

            View.OnClickListener onClickGo = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (persons.get(i).booleanWIN == true) {
                        level = level + 1;
                        llPanel.setBackgroundColor(Color.GREEN);

                        initializeData();
                        initializeAdapter();
                    } else
                    {
                        llPanel.setBackgroundColor(Color.RED);

                        initializeData();
                        initializeAdapter();
                    }

                    long endTime = System.nanoTime();
                    // получаем разницу между двумя значениями нано-времени
                    long timeElapsed = 60 - (endTime - startTime) / 1000000 / 1000;

                    if (timeElapsed < 10) {
                        txtTime.setText("00:0" + timeElapsed);
                    } else txtTime.setText("00:" + timeElapsed);

                    if (timeElapsed <= 1) {
                        txtTime.setText("00:00");
                        Intent intent = new Intent(RecyclerViewActivity.this, TrainersEndActivity.class);
                        startActivity(intent);
                    }
                }
            };
            personViewHolder.personName.setOnClickListener(onClickGo);

            //personViewHolder.personName.setOnFocusChangeListener();

        }

        @Override
        public int getItemCount() {
            return persons.size();
        }
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
