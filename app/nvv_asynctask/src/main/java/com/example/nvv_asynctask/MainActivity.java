package com.example.nvv_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    class SomeBackGroundWork extends AsyncTask<Integer,Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setProgress(0);
            mTextViewInfo.setText("Download...");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Integer arg = integers[0];

            for(int i=0; i<100; i++){

                try {
                    Thread.sleep(arg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isCancelled()){
                    break;
                }
                publishProgress(i+1); // --> вызов метода onProgressUpdate
            }
            return "Completed"; // --> onPostExecute
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Integer completed = values[0];
            mProgressBar.setProgress(completed);
        }

        @Override
        protected void onPostExecute(String status) {
            super.onPostExecute(status);
            mTextViewInfo.setText(status);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mTextViewInfo.setText("Canceled");
        }
    }

    ProgressBar mProgressBar;
    TextView mTextViewInfo;
    SomeBackGroundWork backGroundWork = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL("https://chernivtsi.itstep.org/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream stream = conn.getInputStream();
            byte[] buff = new byte[256];
            int count = 0;
            String html = "";
            while((count = stream.read(buff)) > 0){
                html += new String(buff, 0, count);
            }
            // stream.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextViewInfo = (TextView)findViewById(R.id.textView2);


        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // do in background
                backGroundWork = new SomeBackGroundWork();
                backGroundWork.execute(100);

            }
        });

        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(backGroundWork != null && !backGroundWork.isCancelled()){
                    backGroundWork.cancel(true);
                }
            }
        });
    }
}