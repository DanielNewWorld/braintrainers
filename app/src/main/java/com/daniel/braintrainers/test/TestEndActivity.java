package com.daniel.braintrainers.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;

import java.util.Locale;

public class TestEndActivity extends Activity {

    private static long back_pressed;
    TextView txtResults;
    Button btnEnd;
    String results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_end);

        Configuration config = new Configuration();
        config.setLocale(new Locale(MainActivity.localeSet));
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