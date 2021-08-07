package io.github.ashishthehulk.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import assignmentapp.R;

public class AstronomyActivity extends AppCompatActivity {
    TextView sunSet;
    TextView sunRise;
    TextView moonSet;
    TextView moonRise;
    TextView moonPhase;
    TextView moonIllumination;
    TextView searchedCountry;
    TextView resultCountry;
    String apiURL = "https://api.weatherapi.com/v1/astronomy.json?key=187d898146f543a3b4c112505210708&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astronomy);
        searchedCountry = findViewById(R.id.searchedCountry);
        resultCountry = findViewById(R.id.resultCountry);
        sunRise = findViewById(R.id.sunrise);
        sunSet = findViewById(R.id.sunset);
        moonRise = findViewById(R.id.moonrise);
        moonSet = findViewById(R.id.moonset);
        moonPhase = findViewById(R.id.moon_phase);
        moonIllumination = findViewById(R.id.moon_illumination);

        //setting the searched country text
        Intent currentIntent = getIntent();
        String query = currentIntent.getStringExtra("countryInput");
        searchedCountry.setText(query);

        //API stuff
        //creating request
        StringRequest stringRequest = new StringRequest(apiURL + query, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //called then response returns
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Astronomy astronomy = gson.fromJson(response,Astronomy.class);
                Location location = astronomy.getLocation();
                Astronomy__1 astronomy__1 = astronomy.getAstronomy();
                String loc = location.getName();
                String country = location.getCountry();
                resultCountry.setText(loc +", "+country);
                sunRise.setText(sunRise.getText()+astronomy__1.getAstro().getSunrise());
                sunSet.setText(sunSet.getText()+astronomy__1.getAstro().getSunset());
                moonRise.setText(moonRise.getText()+astronomy__1.getAstro().getMoonrise());
                moonSet.setText(moonSet.getText()+astronomy__1.getAstro().getMoonset());
                moonPhase.setText(moonPhase.getText()+astronomy__1.getAstro().getMoonPhase());
                moonIllumination.setText(moonIllumination.getText()+astronomy__1.getAstro().getMoonIllumination());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Astrology", error.toString());
                //called when something wrong happens and response doesn't come
                Toast.makeText(AstronomyActivity.this, "Check your internet and try again!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( AstronomyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //adding request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}