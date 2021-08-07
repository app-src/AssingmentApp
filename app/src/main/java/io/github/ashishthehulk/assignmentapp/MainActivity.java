package io.github.ashishthehulk.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

import assignmentapp.R;

public class MainActivity extends AppCompatActivity {
    EditText editTextStop;
    private Timer timer = new Timer();
    private final long DELAY = 500; // in ms
    Button weatherBtn;
    Button sportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sportBtn = findViewById(R.id.sportsBtn);
        weatherBtn = findViewById(R.id.weatherBtn);

        editTextStop = this.findViewById(R.id.countyInput);
        editTextStop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                if(timer != null)
                    timer.cancel();
            }
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short
                // Stuff that updates the UI
                if (s.length() >= 3) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    // Stuff that updates the UI
                                    weatherBtn.setVisibility(weatherBtn.VISIBLE);
                                    sportBtn.setVisibility(sportBtn.VISIBLE);
                                }
                            });
                        }

                    }, DELAY);

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // Stuff that updates the UI
                        weatherBtn.setVisibility(View.INVISIBLE);
                        sportBtn.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });

        sportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SportsActivity.class);
                EditText editText = (EditText) findViewById(R.id.countyInput);
                String message = editText.getText().toString();
                intent.putExtra("countryInput", message);
                startActivity(intent);


            }
        });
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                EditText editText = (EditText) findViewById(R.id.countyInput);
                String message = editText.getText().toString();
                intent.putExtra("countryInput", message);
                startActivity(intent);
            }
        });

    }





}