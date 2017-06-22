package com.apps.yasser.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;


import ExternalClasses.Item;
import ExternalClasses.MyListAdapter;

public class MainActivity extends AppCompatActivity {
       ListView myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ArrayList<String> spinner_list=new ArrayList();

        Spinner spinner=(Spinner)findViewById(R.id.story_spinner);
     myList=(ListView)findViewById(R.id.story_list);


        //data of sotry section**************************************************************************************************
        spinner_list.add("home");
        spinner_list.add("opinion");
        spinner_list.add("world");
        spinner_list.add("national");
        spinner_list.add("politics");
        spinner_list.add("nyregion");
        spinner_list.add("business");
        spinner_list.add("technology");
        spinner_list.add("science");
        spinner_list.add("health");
        spinner_list.add("sports");
        spinner_list.add("arts");
        spinner_list.add("books");
        spinner_list.add("movies");
        spinner_list.add("theater");
        spinner_list.add("sundayreview");
        spinner_list.add("fashion");
        spinner_list.add("tmagazine");
        spinner_list.add("food");
        spinner_list.add("travel");
        spinner_list.add("magazine");
        spinner_list.add("realestate");
        spinner_list.add("automobiles");
        spinner_list.add("obituaries");
        spinner_list.add("insider");
        //***************************************************************************************************************************



        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.spinner_layout,R.id.spin_text,spinner_list);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Ion.with(getApplicationContext())
                        .load("http://api.nytimes.com/svc/topstories/v2/"+spinner_list.get(position)+".json?api-key=8e7efd253a3a47c5badb8e98a705595f")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                // do stuff with the result or error
                                if (e == null) {

                                    JsonArray j = result.getAsJsonArray("results");
                                    ArrayList<Item>mylistArray=new ArrayList<>();


                                    for (int i = 0; i < j.size(); i++) {

                                        JsonObject elem= (JsonObject) j.get(i);

                                        String title=elem.get("title").toString().replace("\"", "");
                                        String published_date=elem.get("published_date").toString().replace("\"", "");

                                     JsonArray p=elem.getAsJsonArray("multimedia");

                                        if(p.size()>0) {
                                            JsonObject picelem = (JsonObject) p.get(0);
                                            String url = picelem.get("url").toString().replace("\"", "");
                                            mylistArray.add(new Item(title,published_date,url));
                                        }
                                        mylistArray.add(new Item(title,published_date,""));




                                    }
                                    MyListAdapter myListAdapter=new MyListAdapter(getApplicationContext(),mylistArray);

                                    myList.setAdapter(myListAdapter);
                                }
                                else
                                {

                                    Toast toast =Toast.makeText(getApplicationContext(),"No internet connection available",Toast.LENGTH_LONG);
                                    toast.show();

                                }

                            }
                        });




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"you didn't chose ",Toast.LENGTH_SHORT).show();


            }
        });














    }

    void parseJson(JSONObject results) {
        final    ArrayList<Item>mylistArray=new ArrayList<>();


        try {

            JSONArray j = results.getJSONArray("results");

            int t=j.length();


            for (int i = 0; i < t; i++) {

                JSONObject elem=j.getJSONObject(i);


                String title=elem.getString("title");


                String pub=elem.getString("published_date");
                JSONArray ur=elem.getJSONArray("multimedia");
                String url=ur.getString(0);


                mylistArray.add(new Item(title,"",url));
            }

        } catch (JSONException e1) {

            e1.printStackTrace();
                    }
        MyListAdapter myListAdapter=new MyListAdapter(getApplicationContext(),mylistArray);

        myList.setAdapter(myListAdapter);


    }
    }
