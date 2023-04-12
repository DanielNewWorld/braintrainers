package com.example.braintrainers.trainers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrainers.DescriptionTrainers;
import com.example.braintrainers.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

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
        personViewHolder.personName.setBackgroundColor(persons.get(i).color);
        personViewHolder.personName.setTextColor(Color.WHITE);

        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (persons.get(i).booleanWIN == true) {
                    Intent intent = new Intent(v.getContext(), DescriptionTrainers.class);
                    v.getContext().startActivity(intent);
                }
            }
        };
        personViewHolder.personName.setOnClickListener(onClickGo);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}