package com.daniel.braintrainers.ui.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.R;
import com.daniel.braintrainers.test.Test_lider_Activity_0;

import java.util.ArrayList;
import java.util.List;

public class TestListFragment extends Fragment {
    private RecyclerView rvTestList;
    private List<ItemsClassTestList> itemsClassTestList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_list, container, false);

        rvTestList = (RecyclerView) rootView.findViewById(R.id.rv_test_list);

        GridLayoutManager llm = new GridLayoutManager(rootView.getContext(), 1);
        rvTestList.setLayoutManager(llm);
        rvTestList.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        return rootView;
    }

    public void initializeData(){
        itemsClassTestList = new ArrayList<>();
        String []test_name = getResources().getStringArray(R.array.test_name_array);

        itemsClassTestList.add(new ItemsClassTestList(test_name[0],
                0, 1));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemsClassTestList);
        rvTestList.setAdapter(adapter);
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

        List<ItemsClassTestList> itemsClassTestList;

        RVAdapter(List<ItemsClassTestList> itemsClassTestList){
            this.itemsClassTestList = itemsClassTestList;
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
            personViewHolder.personName.setText(itemsClassTestList.get(i).name);

            View.OnClickListener onClickGo = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent;

                    switch (itemsClassTestList.get(i).id) {
                        case 1:
                            intent = new Intent(context, Test_lider_Activity_0.class);
                            context.startActivity(intent);
                            break;

                        case 2:
                            break;

                        default:
                            break;
                    }
                }
            };
            personViewHolder.personName.setOnClickListener(onClickGo);
        }

        @Override
        public int getItemCount() {
            return itemsClassTestList.size();
        }
    }

}
