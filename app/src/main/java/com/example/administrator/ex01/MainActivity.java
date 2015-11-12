package com.example.administrator.ex01;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private StopWatch stopWatch = new StopWatch();
    private TextView textView1;
    private TextView textView2 ;
    static final int TEXT_SIZE = 50;
    static final String text_view1_key ="key1";
    static final String text_view2_key="key2";
    private boolean flagDublePress1 = false;
    private ViewGroup.LayoutParams params;


    //random place of button 1/2  finished !!!implements SensorEventListener
    private int randomButton(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2); // random int we will get "1" || "2"
        if (randomInt ==1) {

            // replace X location
            float temp  = button1.getX();
            button1.setX(button2.getX());
            button2.setX(temp);

            // replace Y location
            temp = button1.getY();
            button1.setY(button2.getY());
            button2.setY(temp);

        }
        // else , if randomInt == 2 than do not Anything

        return  randomInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView1.setTextSize(TEXT_SIZE);
        textView2.setTextSize(TEXT_SIZE);
        textView2.setText("0");
        
        randomButton();

        if(savedInstanceState != null)
        {
            String savedText1 = savedInstanceState.getString(text_view1_key);
            textView1.setText(savedText1);
            String savedText2 = savedInstanceState.getString(text_view2_key);
            textView2.setText(savedText2);
        }
        else {

        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagDublePress1)
                    stopWatch.start();
                flagDublePress1 = true;

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagDublePress1) {
                    stopWatch.stop();
                    flagDublePress1 = false;

                    long time = stopWatch.getTimeMili();
                    textView1.setText(Long.toString(time));
                    int t1 = Integer.parseInt(textView1.getText().toString());
                    int t2 = Integer.parseInt((textView2.getText()).toString());
                    if (t2 > t1 || t2 == 0)  //new record
                    {
                        Toast.makeText(MainActivity.this, "Good job Bboh!!!:\n" + "New record: " + textView1.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        textView2.setText(textView1.getText());
                    }
                    randomButton();
                }
            }
        });


        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("0");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save input text in to the state bundle
        String inputText1 = textView1.getText().toString();
        outState.putString(text_view1_key, inputText1);
        String inputText2 = textView2.getText().toString();
        outState.putString(text_view2_key, inputText2);
    }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
