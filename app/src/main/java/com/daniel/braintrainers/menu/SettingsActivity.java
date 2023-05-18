package com.daniel.braintrainers.menu;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.daniel.braintrainers.MainActivity;
import com.daniel.braintrainers.R;

import java.util.Locale;

public class SettingsActivity extends Activity {
    TextView txtLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Configuration config = new Configuration();
        config.setLocale(new Locale(MainActivity.localeSet));
        Resources res = getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        txtLocale = (TextView) findViewById(R.id.txtLocale);

        txtLocale.setText(R.string.txt_locale);
    }
}
