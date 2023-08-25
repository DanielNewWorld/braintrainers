package com.daniel.braintrainers.menu;

import static com.daniel.braintrainers.DBHelper.keyLocaleDefault;
import static com.daniel.braintrainers.DBHelper.tableName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.DBHelper;
import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;
import java.util.Locale;

public class SettingsActivity extends Activity {
    TextView txtLocale;
    RecyclerView rvLocale;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String query;
    Cursor c;
    String localeSet = "en";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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

        txtLocale = (TextView) findViewById(R.id.txtLocale);
        rvLocale = (RecyclerView) findViewById(R.id.rvLocale);

        txtLocale.setText(R.string.txt_locale);

        GridLayoutManager llm = new GridLayoutManager(this, 1);
        rvLocale.setLayoutManager(llm);
        rvLocale.setHasFixedSize(true);

        initializeAdapter();
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(MainActivity.localeArray);
        rvLocale.setAdapter(adapter);
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

        String[] itemsList;

        RVAdapter(String[] itemsList){
            this.itemsList = itemsList;
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
            personViewHolder.personName.setText(itemsList[i]);

            View.OnClickListener onClickGo = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Configuration config = new Configuration();
                    config.setLocale(new Locale(MainActivity.localeArrayID[i]));
                    Resources res = getResources();
                    res.updateConfiguration(config, res.getDisplayMetrics());

                    ContentValues cv;
                    long rowID;

                    dbHelper = new DBHelper(SettingsActivity.this);
                    db = dbHelper.getWritableDatabase();
                    cv = new ContentValues();
                    cv.put(keyLocaleDefault, MainActivity.localeArrayID[i]);
                    rowID = db.update(tableName, cv, null, null);
                    dbHelper.close();

                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            };
            personViewHolder.personName.setOnClickListener(onClickGo);
        }

        @Override
        public int getItemCount() {
            return itemsList.length;
        }
    }

}