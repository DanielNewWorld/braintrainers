package com.daniel.braintrainers;

import static com.daniel.braintrainers.DBHelper.tableName;
import static com.daniel.braintrainers.DBHelper.keyLocaleDefault;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.daniel.braintrainers.menu.SettingsActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.daniel.braintrainers.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static String[] localeArray = {"English", "українська"};
    public static String[] localeArrayID = {"en", "uk"};
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static long back_pressed;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String query;
    Cursor c;
    String localeSet = "en";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_trainers, R.id.nav_statistic, R.id.nav_test)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //----
//        privacy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
        else {
            Toast.makeText(getBaseContext(), R.string.back_message,
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_privacy:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/3mIz8cl"));
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}