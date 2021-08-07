package io.github.ashishthehulk.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import assignmentapp.R;

public class WeatherActivity extends AppCompatActivity {
    TextView searchedCountry;
    TextView resultCountry;
    TextView temperature;
    TextView condition;
    TextView windSpeed;
    TextView precipitation;
    TextView humidity;
    TextView cloud;
    TextView UV;
    String apiURL = "https://api.weatherapi.com/v1/current.json?key=187d898146f543a3b4c112505210708&q=";
    String apiRest = "&aqi=no";
    String query;
    Weather weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //after activity started we are assigning the values to variables
        searchedCountry = findViewById(R.id.searchedCountry);
        resultCountry = findViewById(R.id.resultCountry);
        temperature = findViewById(R.id.temeprature);
        condition= findViewById(R.id.condition);
        windSpeed = findViewById(R.id.windSpeed);
        precipitation = findViewById(R.id.precip);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.clouds);
        UV = findViewById(R.id.uv);

        //setting the searched country text
        Intent currentIntent = getIntent();
        query = currentIntent.getStringExtra("countryInput");
        searchedCountry.setText(query);

        //API stuff
        //creating request
        StringRequest stringRequest = new StringRequest(apiURL + query + apiRest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //called then response returns
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                weather = gson.fromJson(response,Weather.class);
                Location location = weather.getLocation();
                Current current = weather.getCurrent();
                String loc = location.getName();
                String country = location.getCountry();
                //setting text
                resultCountry.setText(loc +", "+country);
                temperature.setText(temperature.getText() + current.getTempC().toString()+"°C");
                condition.setText(condition.getText()+current.getCondition().getText());
                windSpeed.setText(windSpeed.getText()+current.getWindKph().toString()+"KMph");
                precipitation.setText(precipitation.getText()+current.getPrecipMm().toString()+"mm");
                humidity.setText(humidity.getText()+current.getHumidity().toString()+"%");
                cloud.setText(cloud.getText()+current.getCloud().toString()+"%");
                UV.setText(UV.getText()+current.getUv().toString()+"milliWpm²");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //called when something wrong happens and response doesn't come
                Toast.makeText(WeatherActivity.this, "Check your internet and try again!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( WeatherActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //adding request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}