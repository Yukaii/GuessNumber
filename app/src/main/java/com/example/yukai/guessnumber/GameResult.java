package com.example.yukai.guessnumber;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameResult extends ActionBarActivity implements Button.OnClickListener{

    Button quitButton, restartButton;
    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        initializeComponents();
        updateUIResult();
    }

    private void initializeComponents() {
        resultText = (TextView) findViewById(R.id.gameresult_result_text);
        restartButton = (Button) findViewById(R.id.gameresult_restart_button);
        quitButton = (Button) findViewById(R.id.gameresult_quit_button);

        restartButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
    }

    private void updateUIResult() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int correctTimes = bundle.getInt("ROUND", 1) - 2;
        resultText.setText(Integer.toString(correctTimes)+"次");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_result, menu);
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
        switch (view.getId()) {
            case R.id.gameresult_quit_button:
                this.finish();
                break;
            case R.id.gameresult_restart_button:
                Intent intent = new Intent();
                intent.setClass(GameResult.this, MainActivity.class);
                intent.putExtras(new Bundle());

                finish();
                startActivity(intent);
                break;
        }
    }
}
