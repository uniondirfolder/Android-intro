package com.example.nvv_1;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nvv_1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    public final static String TAG = "=== MainActivity";
    private final static String KEY_RANDOM_VALUE = "RandomValue";

    /*
    * ERROR - e сообщение об ошибках
    * WARN - w сообщение о предуприждениях
    * INFO - i информационное сообщение
    * DEBUG - d отладочный вывод
    * VERBOSE - v сообщения разработчиков
    * */
    // Активность создается
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*TextView TV = (TextView)this.findViewById(R.id.tv1);*/


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*if(savedInstanceState != null){
            String value = savedInstanceState.getString(MainActivity.KEY_RANDOM_VALUE);
            TV.setText(value);
        }
        else{
            int value = (int)(Math.random()*100);
            TV.setText("Random number: "+value);
        }*/

    }

    // Активность становится видимой
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }
    // Активность выходит на передний план
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }
    // Активность уходит с переднего плана
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }
    // Активность перестает быть видимой
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }
    // Активность уничтожается
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG,"onSaveInstanceState");
        TextView TV = (TextView)this.findViewById(R.id.tv1);
        String value = TV.getText().toString();
        outState.putString(MainActivity.KEY_RANDOM_VALUE,value);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch (id) {
            case R.id.action_settings :
                return true;
            case R.id.action_hello_world:
                Toast.makeText(this,R.string.hello_world,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_android_forever:
                Toast.makeText(this,R.string.action_android_forever,Toast.LENGTH_SHORT).show();
                return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}