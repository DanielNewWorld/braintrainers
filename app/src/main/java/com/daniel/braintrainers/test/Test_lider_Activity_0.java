package com.daniel.braintrainers.test;

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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.DBHelper;
import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;
import com.daniel.braintrainers.trainers.KvitnykActivity_1;
import com.daniel.braintrainers.ui.home.TrainersEndActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Test_lider_Activity_0 extends Activity {
    private RecyclerView rv_test;
    private List<ItemsClassTest> itemsClassTest;
    TextView txtQuest, txtDesc;
    int questNum = 0, points = 0, count = 0;
    String results = "";
    String[] questions_1, questions_2;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String query;
    Cursor c;
    String localeSet = "en";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_chekbox_lider_activity);

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

        rv_test = (RecyclerView) findViewById(R.id.rv_test);
        txtQuest = (TextView) findViewById(R.id.txtQuestion);
        txtDesc = (TextView) findViewById(R.id.txtDesc);

        String []test_quest = getResources().getStringArray(R.array.test_name_array);
        txtQuest.setText(test_quest[0]);

        txtDesc.setText(R.string.test_txt_quest);

        GridLayoutManager llm = new GridLayoutManager(this, 1);
        rv_test.setLayoutManager(llm);
        rv_test.setHasFixedSize(true);

        questions_1 = getResources().getStringArray(R.array.test_lider_array_1);
        questions_2 = getResources().getStringArray(R.array.test_lider_array_2);
        count = questions_1.length;

        initializeData();
        initializeAdapter();
    }

    public void initializeData(){
        itemsClassTest = new ArrayList<>();
        itemsClassTest.add(new ItemsClassTest(questions_1[questNum], questions_2[questNum], 0, questNum));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemsClassTest);
        rv_test.setAdapter(adapter);
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        public class PersonViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            RadioButton personName_1, personName_2;
            Button btnNext;

            PersonViewHolder(View itemView) {
                super(itemView);

                cv = (CardView) itemView.findViewById(R.id.cv);
                personName_1 = (RadioButton) itemView.findViewById(R.id.radioBtn1);
                personName_2 = (RadioButton) itemView.findViewById(R.id.radioBtn2);
                btnNext = (Button) itemView.findViewById(R.id.btn_next_item);
            }
        }

        List<ItemsClassTest> itemsClassTest;

        RVAdapter(List<ItemsClassTest> itemsClassTest){
            this.itemsClassTest = itemsClassTest;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chekbox, viewGroup, false);
            RVAdapter.PersonViewHolder pvh = new RVAdapter.PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(RVAdapter.PersonViewHolder personViewHolder, @SuppressLint("RecyclerView") int i) {
            personViewHolder.personName_1.setText(itemsClassTest.get(i).name_1);
            personViewHolder.personName_2.setText(itemsClassTest.get(i).name_2);
            personViewHolder.personName_1.setChecked(false);
            personViewHolder.personName_2.setChecked(false);

            View.OnClickListener onClickYes = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean cheked = false;

                    if (personViewHolder.personName_1.isChecked()) {
                        cheked = true;

                        switch (itemsClassTest.get(i).id) {
                            case 0:
                                points++;
                                break;

                            case 1:
                                points = points + 2;
                                break;

                            case 2:
                                points++;
                                break;

                            case 3:
                                points = points + 2;
                                break;

                            case 4:
                                points++;
                                break;

                            case 5:
                                points = points + 2;
                                break;

                            case 6:
                                points++;
                                break;

                            case 7:
                                points = points + 2;
                                break;

                            case 8:
                                points++;
                                break;

                            case 9:
                                points = points + 2;
                                break;

                            default:
                                break;
                        }
                    }

                    if (personViewHolder.personName_2.isChecked()) {
                        cheked = true;

                        switch (itemsClassTest.get(i).id) {
                            case 0:
                                points = points + 2;
                                break;

                            case 1:
                                points++;
                                break;

                            case 2:
                                points = points + 2;
                                break;

                            case 3:
                                points++;
                                break;

                            case 4:
                                points = points + 2;
                                break;

                            case 5:
                                points++;
                                break;

                            case 6:
                                points = points + 2;
                                break;

                            case 7:
                                points++;
                                break;

                            case 8:
                                points = points + 2;
                                break;

                            case 9:
                                points++;
                                break;

                            default:
                                break;
                        }
                    }

                    if (cheked) {
                        questNum++;

                        if (questNum < count) {
                            initializeData();
                            initializeAdapter();
                        } else {
                            String[] test_answer = getResources().getStringArray(R.array.test_lider_answer_array);

                            if (points >= 18) {
                                results = test_answer[0];
                            }

                            if (points >= 13 && points <= 17) {
                                results = test_answer[1];
                            }

                            if (points <= 12) {
                                results = test_answer[2];
                            }

                            Intent intent = new Intent(Test_lider_Activity_0.this, TestEndActivity.class);
                            intent.putExtra("results_txt", results);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(Test_lider_Activity_0.this,
                                R.string.test_message_select, Toast.LENGTH_SHORT).show();
                    }
                }
            };
            personViewHolder.btnNext.setOnClickListener(onClickYes);

//            View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    RadioButton rb = (RadioButton) v;
//                    switch (rb.getId()) {
//                        case R.id.radioBtn1:
//                            txtDesc.setText("id: " + Integer.toString(itemsClassTest.get(i).id) + "\n" +
//                                    "points: " + points + "\n" +
//                                    "quest: " + questNum);
//                            break;
//
//                        case R.id.radioBtn2:
//                            txtDesc.setText("id: " + Integer.toString(itemsClassTest.get(i).id) + "\n" +
//                                    "points: " + points + "\n" +
//                                    "quest: " + questNum);
//                            break;
//
//                        default:
//                            break;
//                    }
//                }
//            };
//            personViewHolder.personName_1.setOnClickListener(radioButtonClickListener);
//            personViewHolder.personName_2.setOnClickListener(radioButtonClickListener);
        }

        @Override
        public int getItemCount() {
            return itemsClassTest.size();
        }
    }
}