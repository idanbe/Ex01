package com.example.administrator.ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private StopWatch stopWatch = new StopWatch();
    private TextView textView1;
    private TextView textView2 ;
    static final String text_view1_key ="key1";
    static final String text_view2_key="key2";
    private boolean flagDublePress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);

        textView1.setTextSize(50);
        textView2.setTextSize(50);
        textView2.setText("0");


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
                if(!flagDublePress)
                    stopWatch.start();
                flagDublePress=true;

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagDublePress==true)
                {
                    stopWatch.stop();
                    long time = stopWatch.getTimeMili();
                    textView1.setText(Long.toString(time));
                    int t1 = Integer.parseInt(textView1.getText().toString());
                    int t2 = Integer.parseInt((textView2.getText()).toString());
                    if (t2 > t1 || t2 == 0)  //new record
                    {
                        Toast.makeText(MainActivity.this,"Good job Bboh!!!:\n" +"New record: " + textView1.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        textView2.setText(textView1.getText());
                    }
                    flagDublePress = false;
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
