package com.daniel.braintrainers.test;

import static com.daniel.braintrainers.DBHelper.keyLocaleDefault;
import static com.daniel.braintrainers.DBHelper.tableName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.braintrainers.DBHelper;
import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;

import java.util.Locale;

public class TestEndActivity extends Activity {

    private static long back_pressed;
    TextView txtResults;
    Button btnEnd;
    String results;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String query;
    Cursor c;
    String localeSet = "en";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_end);

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

        txtResults = (TextView) findViewById(R.id.txtResults);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        Intent intent = getIntent();
        results = intent.getStringExtra("results_txt");

        txtResults.setText(results);

        View.OnClickListener onClickGo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent;

                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);

            }
        };
        btnEnd.setOnClickListener(onClickGo);
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