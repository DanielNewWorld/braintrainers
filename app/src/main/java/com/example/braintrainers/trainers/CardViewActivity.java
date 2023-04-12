package com.example.braintrainers.trainers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.braintrainers.R;

public class CardViewActivity extends Activity {

    TextView personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        //personName = (TextView) findViewById(R.id.person_name);

        //personName.setText("Emma Wilson");
    }
}
