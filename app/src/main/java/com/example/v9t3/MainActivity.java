package com.example.v9t3;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Spinner spin;
    private Spinner spin2;
    private Spinner spin3;
    private Spinner spin4;
    private Spinner spin5;

    public ArrayAdapter<String> adapter;
    public ArrayAdapter<String> adapter2;
    public ArrayAdapter<String> adapter3;
    public ArrayAdapter<String> adapter4;

    List<String> o = new ArrayList<>();

    TextView results;

    SmartpostDestinations s = SmartpostDestinations.getInstance();

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        results = (TextView) findViewById(R.id.results);

        spin = findViewById(R.id.spinner);
        spin2 = findViewById(R.id.spinner2);
        spin3 = findViewById(R.id.spinner3);
        spin4 = findViewById(R.id.spinner4);
        spin5 = findViewById(R.id.spinner5);

        spin.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        spin4.setOnItemSelectedListener(this);
        spin5.setOnItemSelectedListener(this);

        final List<String> weekdays = new ArrayList<>();
        weekdays.add(0, "All");
        weekdays.add("Monday");
        weekdays.add("Tuesday");
        weekdays.add("Wednesday");
        weekdays.add("Thursday");
        weekdays.add("Friday");
        weekdays.add("Saturday");
        weekdays.add("Sunday");

        final List<String> times = new ArrayList<>();
        times.add(0, "All");
        times.add("05");
        times.add("06");
        times.add("07");
        times.add("08");
        times.add("09");
        times.add("10");
        times.add("11");
        times.add("12");
        times.add("13");
        times.add("14");
        times.add("15");
        times.add("16");
        times.add("17");
        times.add("18");
        times.add("19");
        times.add("20");
        times.add("21");
        times.add("22");
        times.add("23");
        times.add("24");

        final List<String> locations = new ArrayList<>();
        locations.add(0, "All");
        locations.add("Finland");
        locations.add("Estonia");

        //Luetaan XML-tiedostot
        s.XMLdestinations();


        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, s.spinnerList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, weekdays);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin2.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, times);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin3.setAdapter(adapter3);

        spin4.setAdapter(adapter3);

        adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, locations);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin5.setAdapter(adapter4);

    }

    public List updateList(String v) {

        String auki;
        int laskin = 0;

        String[] part = new String[5];
        String[] part2 = new String[5];

        List<String> openDates = new ArrayList<>();

        //Muokkaan XML-tiedostosta haettuja aikatietoja, mutta en käytä niitä vielä tässä tehtäväsässä
        for(SmartpostObject x : s.getList()) {
            part = x.getAvailability().split(",");
            for (int i = 0; i < part.length; i++) {
                part2 = part[i].trim().split(" ");
               /*f (part2[0].equals("24h")){
                    openDates.add(x.getName());
                }else {
                    //if
                }*/
            }
        }
            return openDates;
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {


        switch (parent.getId()) {
            case R.id.spinner:

                results.setText("id: " + s.getList().get(position).getPlace_id() + "\n" + "Name: " +  s.getList().get(position).getName()
                + "\n" +"City: " +  s.getList().get(position).getCity() + "\n" + "Address: " +  s.getList().get(position).getAddress()
                        + "\n" + "Country: " + s.getList().get(position).getCountry() + "\n" +"Post. code: " +  s.getList().get(position).getPostalcode()
                        + "\n" +"Availability: " +  s.getList().get(position).getAvailability());
                break;
        }
        switch (parent.getId()) {
            case R.id.spinner2:
                String valinta = parent.getItemAtPosition(position).toString();

                if (valinta != "All") {
                    adapterChanger(valinta);
                } else {
                    adapterChangerAll();
                }
                break;
        }
        switch (parent.getId()) {
            case R.id.spinner3:
                String valinta2 = parent.getItemAtPosition(position).toString();

                break;
        }
        switch (parent.getId()) {
            case R.id.spinner4:
                String valinta3 = parent.getItemAtPosition(position).toString();

                if (valinta3 != "All") {
                    adapterChanger(valinta3);
                } else {
                    adapterChangerAll();
                }

                break;
        }
        switch (parent.getId()) {
            case R.id.spinner5:
                String valinta4 = parent.getItemAtPosition(position).toString();

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void adapterChanger(String v) {
        o = updateList(v);
        adapter.clear();
        adapter.addAll(o);
    }
    public void adapterChangerAll() {
        adapter.clear();
        adapter.addAll(s.spinnerList());
    }
}

