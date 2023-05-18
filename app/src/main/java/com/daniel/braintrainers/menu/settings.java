package com.daniel.braintrainers.menu;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;

import java.util.Locale;

public class settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Configuration config = new Configuration();
        config.setLocale(new Locale(MainActivity.localeSet));
        Resources res = getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

    }
}
