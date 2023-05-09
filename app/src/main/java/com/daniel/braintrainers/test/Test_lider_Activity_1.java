package com.daniel.braintrainers.test;

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

import com.daniel.braintrainers.R;

import java.util.ArrayList;
import java.util.List;

public class Test_lider_Activity_1 extends Activity {
    private static long back_pressed;
    private RecyclerView rv_test;
    private List<ItemsClassTest> itemsClassTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_chekbox_lider_activity);

        rv_test = (RecyclerView) findViewById(R.id.rv_test);

        GridLayoutManager llm = new GridLayoutManager(this, 1);
        rv_test.setLayoutManager(llm);
        rv_test.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
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

    public void initializeData(){
        itemsClassTest = new ArrayList<>();
        String []questions = getResources().getStringArray(R.array.test_lider_array);
        int count = questions.length;

        for (int i = 0; i < count; i++) {
            itemsClassTest.add(new ItemsClassTest(questions[i], 0, i+1));
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemsClassTest);
        rv_test.setAdapter(adapter);
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
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            RVAdapter.PersonViewHolder pvh = new RVAdapter.PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(RVAdapter.PersonViewHolder personViewHolder, @SuppressLint("RecyclerView") int i) {
            personViewHolder.personName.setText(itemsClassTest.get(i).name);

            View.OnClickListener onClickGo = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
            personViewHolder.personName.setOnClickListener(onClickGo);
        }

        @Override
        public int getItemCount() {
            return itemsClassTest.size();
        }
    }
}