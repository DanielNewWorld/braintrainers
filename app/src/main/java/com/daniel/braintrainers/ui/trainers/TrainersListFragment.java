package com.daniel.braintrainers.ui.trainers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.R;
import com.daniel.braintrainers.databinding.FragmentTrainersListBinding;
import com.daniel.braintrainers.trainers.FindNumberActivity_0;
import com.daniel.braintrainers.trainers.KvitnykActivity_1;
import com.daniel.braintrainers.ui.home.DescriptionTrainers;
import com.daniel.braintrainers.ui.home.TrainersEndActivity;

import java.util.ArrayList;
import java.util.List;

public class TrainersListFragment extends Fragment {

    private FragmentTrainersListBinding binding;
    private RecyclerView rvTrainersList;
    private List<ItemsClassTrainersList> itemsClassTrainersList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrainersViewModel trainersViewModel =
                new ViewModelProvider(this).get(TrainersViewModel.class);

        binding = FragmentTrainersListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textGallery;
        //trainersViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        rvTrainersList = (RecyclerView) root.findViewById(R.id.rv_trainers_list);

        GridLayoutManager llm = new GridLayoutManager(root.getContext(), 1);
        rvTrainersList.setLayoutManager(llm);
        rvTrainersList.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initializeData(){
        itemsClassTrainersList = new ArrayList<>();
        String []trainers_name = getResources().getStringArray(R.array.trainers_name_array);
        int []img_res = {R.drawable.find_number_1, R.drawable.kvitnyk_2};

        for (int i = 0; i < trainers_name.length; i++) {
            itemsClassTrainersList.add(new ItemsClassTrainersList(trainers_name[i],
                    0, img_res[i], i+1));
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(itemsClassTrainersList);
        rvTrainersList.setAdapter(adapter);
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        public class PersonViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            TextView personName;
            ImageView item_img;

            PersonViewHolder(View itemView) {
                super(itemView);

                cv = (CardView)itemView.findViewById(R.id.cv);
                personName = (TextView) itemView.findViewById(R.id.person_name);
                item_img = (ImageView) itemView.findViewById(R.id.item_img);
            }
        }

        List<ItemsClassTrainersList> itemsClassTrainersList;

        RVAdapter(List<ItemsClassTrainersList> itemsClassTrainersList){
            this.itemsClassTrainersList = itemsClassTrainersList;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public RVAdapter.PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_img, viewGroup, false);
            RVAdapter.PersonViewHolder pvh = new RVAdapter.PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(RVAdapter.PersonViewHolder personViewHolder, @SuppressLint("RecyclerView") int i) {
            personViewHolder.personName.setText(itemsClassTrainersList.get(i).name);
            int sss = R.drawable.find_number_1;
            personViewHolder.item_img.setImageResource(itemsClassTrainersList.get(i).img);
            personViewHolder.item_img.setMaxWidth(300);

            View.OnClickListener onClickGo = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent;

                    switch (itemsClassTrainersList.get(i).id) {
                        case 1:
                            intent = new Intent(context, DescriptionTrainers.class);
                            intent.putExtra("numberTrainer", "1");
                            intent.putExtra("dad", "TrainerList");
                            context.startActivity(intent);
                            break;

                        case 2:
                            intent = new Intent(context, DescriptionTrainers.class);
                            intent.putExtra("numberTrainer", "2");
                            intent.putExtra("dad", "TrainerList");
                            context.startActivity(intent);
                            break;

                        default:
                            break;
                    }
                }
            };
            personViewHolder.item_img.setOnClickListener(onClickGo);
        }

        @Override
        public int getItemCount() {
            return itemsClassTrainersList.size();
        }
    }
}