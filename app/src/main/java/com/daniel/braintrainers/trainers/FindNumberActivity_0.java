package com.daniel.braintrainers.trainers;

import static com.daniel.braintrainers.DBHelper.keyLocaleDefault;
import static com.daniel.braintrainers.DBHelper.tableName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.daniel.braintrainers.DBHelper;
import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;
import com.daniel.braintrainers.ui.home.TrainersEndActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FindNumberActivity_0 extends Activity {
    private RecyclerView rv;
    TextView txtInfoNumber, txtTime, txtQuest;
    LinearLayout llPanel;
    final long startTime = System.nanoTime();

    int countTrainerItem = 0;
    int maxCountTrueAnswer = 0;
    int neuron = 0;

    int level = 0;
    private static long back_pressed;
    private List<ItemsClassTrainers> itemsClassTrainers;

    String switchTrainer, dad;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String query;
    Cursor c;
    String localeSet = "en";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_number_recyclerview_activity);

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        query = "SELECT * FROM " + tableName;
        c = db.rawQuery(query, null);
        c.moveToFirst(); // переходим на первую строку
        localeSet = c.getString(c.getColumnIndex(keyLocaleDefault));
        c.close();
        dbHelper.close();

        Configuration config = new Configuration();
        config.setLocale(new Locale(localeSet));
        Resources res = getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        rv=(RecyclerView)findViewById(R.id.rv);
        txtInfoNumber = (TextView) findViewById(R.id.txt_info_number);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtQuest = (TextView) findViewById(R.id.txtQuestion);
        llPanel = (LinearLayout) findViewById(R.id.ll_panel_trainers);

        String []trainers_quest = getResources().getStringArray(R.array.trainers_txt_quest_array);
        txtQuest.setText(trainers_quest[0]);

        GridLayoutManager llm = new GridLayoutManager(FindNumberActivity_0.this, 3);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        timeCount();

        Intent intent = getIntent();
        switchTrainer = intent.getStringExtra("numberTrainer");
        dad = intent.getStringExtra("dad");
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
            Intent intent = new Intent(FindNumberActivity_0.this, TrainersEndActivity.class);
            intent.putExtra("maxCountTrueAnswer", Integer.toString(maxCountTrueAnswer));
            intent.putExtra("countTrainerItem", Integer.toString(countTrainerItem));
            intent.putExtra("neuron", Integer.toString(neuron));
            intent.putExtra("numberTrainer", switchTrainer);
            intent.putExtra("dad", dad);
            startActivity(intent);
        }
    }

    public void initializeData(){
        int[] color  = new int[] {Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,
        Color.GREEN,Color.LTGRAY,Color.RED, Color.MAGENTA,Color.WHITE,
        Color.YELLOW};
        int y = -65536;
        int x = 0;
        int z = 0;
        int count;
        itemsClassTrainers = new ArrayList<>();
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
            y = (int) (Math.random()*color.length);
            rotation = (int) (Math.random()*50 - 25);
            if (i == z) {
                booleanWIN = true;
                txtInfoNumber.setText(Integer.toString(x));
            }
                else booleanWIN = false;
            itemsClassTrainers.add(new ItemsClassTrainers(Integer.toString(x), color[y], booleanWIN, rotation));
        }
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
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, @SuppressLint("RecyclerView") int i) {
            personViewHolder.personName.setText(itemsClassTrainers.get(i).name);

            if (level >= 2) {personViewHolder.personName.setRotation(itemsClassTrainers.get(i).rotation);}

            if (level >= 4) {
                personViewHolder.personName.setBackgroundColor(itemsClassTrainers.get(i).color);

                if (itemsClassTrainers.get(i).color == Color.WHITE) {
                    personViewHolder.personName.setTextColor(Color.BLACK);}
                else personViewHolder.personName.setTextColor(Color.WHITE);
            }

            View.OnClickListener onClickGo = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemsClassTrainers.get(i).booleanWIN == true) {
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
            personViewHolder.personName.setOnClickListener(onClickGo);
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
            Toast.makeText(getBaseContext(), R.string.back_message,
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
