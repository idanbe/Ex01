package com.example.administrator.ex01;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


// Speedometer or something like that
// time in milliseconds
public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private StopWatch stopWatch = new StopWatch(); // init watch
    private TextView textView1;
    private TextView textView2 ;
    static final int TEXT_SIZE = 50;
    static final String text_view1_key ="key1";     // key for shardepre..
    static final String text_view2_key="key2";      // key for shardepre..
    private boolean flagDublePress = false;         // double press is no ok
    SharedPreferences sharedPreferences;

    //random place of button 1/2  finished !!!
    private void randomButton(){

        // random int we will get "1" || "2"
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);

        if (randomInt == 1) {

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

        return ;
    }


    // save data in file
    private void saveData(SharedPreferences sharedPreferences){
        // create aditor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // add data
        editor.putString(text_view1_key, textView1.getText().toString());
        editor.putString(text_view2_key, textView2.getText().toString());

        // save data
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView1.setTextSize(TEXT_SIZE);
        textView2.setTextSize(TEXT_SIZE);

        // create shared pref...
        sharedPreferences = getPreferences(MODE_PRIVATE);

        textView2.setText("0");

        // listener to button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagDublePress)
                    stopWatch.start();
                flagDublePress = true;
            }
        });

        // listener to button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // no double press
                if (flagDublePress) {
                    stopWatch.stop();
                    flagDublePress = false;

                    // get time
                    long time = stopWatch.getTimeMilli();

                    // convert to string
                    textView1.setText(Long.toString(time));

                    int t1 = Integer.parseInt(textView1.getText().toString());
                    int t2 = Integer.parseInt((textView2.getText()).toString());

                    //if is a new record
                    if ( t2 > t1 || t2 == 0 )
                    {
                        if(t1 == 1){ // Congratulations message
                            Toast.makeText(MainActivity.this,"the best record !!!: " + textView1.getText().toString() + "\n touch in record to reset" ,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else { // Good Job message
                            Toast.makeText(MainActivity.this, "Good Job Bboh!!!:\n" + "New record: " + textView1.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        textView2.setText(textView1.getText());
                    }
                    randomButton();
                }
            }
        });

        // reset record
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("0");
                randomButton();
            }
        });
        randomButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // check if there is a data
        if(sharedPreferences != null)
        {
            // restore data
            textView1.setText(sharedPreferences.getString(text_view1_key ,""));
            textView2.setText(sharedPreferences.getString(text_view2_key , "0"));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData(sharedPreferences);
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
