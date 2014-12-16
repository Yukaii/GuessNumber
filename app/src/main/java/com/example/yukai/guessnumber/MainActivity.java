package com.example.yukai.guessnumber;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity implements Button.OnClickListener{

    Button smallerButton, biggerButton;
    TextView baseNumberText;

    Bundle bundle;

    private int baseNumber, guessNumber = -1, correctTimes, nthRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        generateGameParameters();
        updateView();

    }

    private void initializeComponents() {
        // Control Buttons
        smallerButton = (Button) findViewById(R.id.main_small_button);
        smallerButton.setOnClickListener(this);

        biggerButton = (Button) findViewById(R.id.main_big_button);
        biggerButton.setOnClickListener(this);

        // Textviews

        baseNumberText = (TextView) findViewById(R.id.main_base_number);
    }

    private void generateGameParameters() {
        Intent mIntent = getIntent();
        bundle = mIntent.getExtras();

        // play for the first round
        if (bundle == null) {
            bundle = new Bundle();
            nthRound = 1;
            baseNumber = 50;
            correctTimes = 0;

        }
        // nth Round
        else {
            nthRound = bundle.getInt("ROUND", 1);
            baseNumber = bundle.getInt("BASE_NUMBER", 50);
            correctTimes = bundle.getInt("CORRECT_TIMES", 0);
        }

        guessNumber = (int)(Math.random()*101);
//        Log.d("answer", ""+guessNumber);
    }

    private void updateView() {
        baseNumberText.setText(Integer.toString(baseNumber));
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

    @Override
    public void onClick(View view) {
        if (guessNumber == -1) {
            return;
        }

        switch (view.getId()) {
            case R.id.main_big_button:
                if (guessNumber >= baseNumber)
                    loadNextRound();
                else
                    endGame();
                break;
            case R.id.main_small_button:
                if (guessNumber < baseNumber)
                    loadNextRound();
                else
                    endGame();
                break;
        }
    }

    private void endGame() {
        bundle.putInt("ROUND", nthRound+1);
        bundle.putInt("CORRECT_TIMES", correctTimes);

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, GameResult.class);
        intent.putExtras(bundle);

        this.finish();
        startActivity(intent);
    }

    private void loadNextRound() {
        bundle.putInt("ROUND", nthRound+1);
        bundle.putInt("BASE_NUMBER", guessNumber);
        bundle.putInt("CORRECT_TIMES", correctTimes);

        Intent intent = getIntent();
        intent.putExtras(bundle);

        this.finish();
        startActivity(intent);
    }
}
