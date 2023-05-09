package com.daniel.braintrainers.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.R;

import java.util.ArrayList;
import java.util.List;

public class Test_lider_Activity_0 extends Activity {
    private RecyclerView rv_test;
    private List<ItemsClassTest> itemsClassTest;
    TextView txtQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_chekbox_lider_activity);

        rv_test = (RecyclerView) findViewById(R.id.rv_test);
        txtQuest = (TextView) findViewById(R.id.txtQuestion);

        String []test_quest = getResources().getStringArray(R.array.test_name_array);
        txtQuest.setText(test_quest[0]);

        GridLayoutManager llm = new GridLayoutManager(this, 1);
        rv_test.setLayoutManager(llm);
        rv_test.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    public void initializeData(){
        itemsClassTest = new ArrayList<>();
        String []questions_1 = getResources().getStringArray(R.array.test_lider_array_1);
        String []questions_2 = getResources().getStringArray(R.array.test_lider_array_2);
        int count = questions_1.length;

        for (int i = 0; i < count; i++) {
            itemsClassTest.add(new ItemsClassTest(questions_1[i], questions_2[i], 0, i+1));
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemsClassTest);
        rv_test.setAdapter(adapter);
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        public class PersonViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            TextView personName_1, personName_2;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                personName_1 = (TextView)itemView.findViewById(R.id.person_name_1);
                personName_2 = (TextView)itemView.findViewById(R.id.person_name_2);
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
        }

        @Override
        public int getItemCount() {
            return itemsClassTest.size();
        }
    }
}