package com.daniel.braintrainers.trainers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.R;
import com.daniel.braintrainers.ui.home.TrainersEndActivity;

import java.util.ArrayList;
import java.util.List;

public class KvitnykActivity_2 extends Activity {
    private RecyclerView rv;
    TextView txtTime;
    Button btnYes, btnNo;
    LinearLayout llPanel;
    final long startTime = System.nanoTime();

    int countTrainerItem = 0;
    int maxCountTrueAnswer = 0;
    int neuron = 0;

    int level = 0;
    private static long back_pressed;
    private List<ItemsClassTrainers> itemsClassTrainers;

    boolean quest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kvitnyk_recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);
        txtTime = (TextView) findViewById(R.id.txtTime);
        llPanel = (LinearLayout) findViewById(R.id.ll_panel_trainers);
        btnNo = (Button) findViewById(R.id.btn_no);
        btnYes = (Button) findViewById(R.id.btn_yes);

        GridLayoutManager llm = new GridLayoutManager(KvitnykActivity_2.this, 1);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        View.OnClickListener onClickYes = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quest == true) {
                    switch (level) {
                        case 0:
                            neuron = neuron + 10;
                            break;

                        case 1:
                            neuron = neuron + 50;
                            break;

                        case 2:
                            neuron = neuron + 100;
                            break;

                        case 3:
                            neuron = neuron + 150;
                            break;

                        case 4:
                            neuron = neuron + 200;
                            break;

                        default:
                            neuron = neuron + 10;
                            break;
                    }
                    llPanel.setBackgroundColor(Color.GREEN);
                    maxCountTrueAnswer ++;
                    level ++;

                    initializeData();
                    initializeAdapter();
                } else
                {
                    llPanel.setBackgroundColor(Color.RED);

                    initializeData();
                    initializeAdapter();
                }

                countTrainerItem ++;
                timeCount();
            }
        };
        btnYes.setOnClickListener(onClickYes);

        View.OnClickListener onClickNo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quest == false) {
                    switch (level) {
                        case 0:
                            neuron = neuron + 10;
                            break;

                        case 1:
                            neuron = neuron + 50;
                            break;

                        case 2:
                            neuron = neuron + 100;
                            break;

                        case 3:
                            neuron = neuron + 150;
                            break;

                        case 4:
                            neuron = neuron + 200;
                            break;

                        default:
                            neuron = neuron + 10;
                            break;
                    }
                    llPanel.setBackgroundColor(Color.GREEN);
                    maxCountTrueAnswer ++;
                    level ++;

                    initializeData();
                    initializeAdapter();
                } else
                {
                    llPanel.setBackgroundColor(Color.RED);

                    initializeData();
                    initializeAdapter();
                }

                countTrainerItem ++;
                timeCount();
            }
        };
        btnNo.setOnClickListener(onClickNo);

        initializeData();
        initializeAdapter();

        timeCount();
    }

    public void timeCount(){
        long endTime = System.nanoTime();
        // получаем разницу между двумя значениями нано-времени
        long timeElapsed = 40 - (endTime - startTime) / 1000000 / 1000;

        if (timeElapsed < 10) {
            txtTime.setText("00:0" + timeElapsed);
        } else txtTime.setText("00:" + timeElapsed);

        if (timeElapsed <= 1) {
            txtTime.setText("00:00");
            Intent intent = new Intent(KvitnykActivity_2.this, TrainersEndActivity.class);
            intent.putExtra("maxCountTrueAnswer", Integer.toString(maxCountTrueAnswer));
            intent.putExtra("countTrainerItem", Integer.toString(countTrainerItem));
            intent.putExtra("neuron", Integer.toString(neuron));
            intent.putExtra("numberTrainer", "2");
            startActivity(intent);
        }
    }

    public void initializeData(){
        int[] color  = new int[] {Color.BLACK,Color.BLUE,Color.CYAN,Color.GRAY,
                Color.GREEN,Color.RED, Color.MAGENTA,Color.YELLOW};
        //String[] colorName  = new String[] {"чорний","синій","блакитний","сірий",
        //        "зелений","червоний", "пурпурний","жовтий"};
        String[] colorName = getResources().getStringArray(R.array.color_name_array);
        int x1, y1, x2, y2 = 0;
        int count = 2;
        itemsClassTrainers = new ArrayList<>();
        boolean booleanWIN = false;
        int rotation = 0;

        //for (int i = 0; i < count; i++) {
            x1 = (int) (Math.random()*colorName.length);
            y1 = (int) (Math.random()*colorName.length);
            rotation = (int) (Math.random()*50 - 25);
            itemsClassTrainers.add(new ItemsClassTrainers(colorName[x1], color[y1], booleanWIN, rotation));
        //}
        x2 = (int) (Math.random()*colorName.length);
        y2 = (int) (Math.random()*colorName.length);
        rotation = (int) (Math.random()*50 - 25);
        itemsClassTrainers.add(new ItemsClassTrainers(colorName[x2], color[y2], booleanWIN, rotation));

        if (x1 == y2) {
            quest = true;
        }
        else quest = false;
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemsClassTrainers);
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

        List<ItemsClassTrainers> itemsClassTrainers;

        RVAdapter(List<ItemsClassTrainers> itemsClassTrainers){
            this.itemsClassTrainers = itemsClassTrainers;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            RVAdapter.PersonViewHolder pvh = new RVAdapter.PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(RVAdapter.PersonViewHolder personViewHolder, @SuppressLint("RecyclerView") int i) {
            personViewHolder.personName.setText(itemsClassTrainers.get(i).name);
            personViewHolder.personName.setTextColor(itemsClassTrainers.get(i).color);
            personViewHolder.personName.setWidth(400);
            //personViewHolder.personName.setPadding(5, 20, 5, 20);

            //if (level >= 2) {personViewHolder.personName.setRotation(itemsClasses.get(i).rotation);}
        }

        @Override
        public int getItemCount() {
            return itemsClassTrainers.size();
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