package com.example.nvv_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnClick(View view){
        int id =view.getId();
        switch (id){
            case R.id.button:
                Toast.makeText(this, "Button Clicked!",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}