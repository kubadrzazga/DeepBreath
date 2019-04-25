package com.example.deepbreath;

import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class fetchData extends AsyncTask<Void,Void,Void>{
    String data="";
    String dataParsed = "";
    String singleParsed = "";
    String data1="";
    String dataParsed1 = "";
    Integer singleParsed1;
    String data2="";
    String dataParsed2 = "";
    String singleParsed2;
    ArrayList<String> names;
   // HashMap<Integer,String> stations = new HashMap<>();
    private HashMap<Integer,LatLng> coordinate = new HashMap();
    private HashMap<Integer,String> Params = new HashMap<>();
    private HashMap<Integer,ArrayList<Integer>> Sensors = new HashMap<>();

    private Void SensorsReader(String id){
        try {

            URL url1 = new URL("http://api.gios.gov.pl/pjp-api/rest/station/sensors/"+id);
            HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
            InputStream inputStream1 = httpURLConnection1.getInputStream();
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));
            String line1 = "";
            while(line1 != null){
                line1 = bufferedReader1.readLine();
                data1 = data1 + line1;
            }

            JSONArray JA1 = new JSONArray(data1);
            Sensors.put((Integer.parseInt(id)),new ArrayList<Integer>());
            for(int i = 0; i < JA1.length(); i++){
                JSONObject JO1 = (JSONObject) JA1.get(i);

                    if(JO1.get("id")!=null){
                        Sensors.get((Integer.parseInt(id))).add(JO1.getInt("id"));
                    }

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MapsActivity.setSensors(Sensors);
        return null;

    }

    private Void ParamsReader(String id){
        try {

            URL url2 = new URL("http://api.gios.gov.pl/pjp-api/rest/data/getData/"+id);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
            InputStream inputStream2 = httpURLConnection2.getInputStream();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
            String line2 = "";
            while(line2 != null){
                line2 = bufferedReader2.readLine();
                data2 = data2 + line2;
            }

            JSONArray JA2 = new JSONArray(data2);

                JSONObject JO2 = (JSONObject) JA2.get(0);

                if(JO2.get("key")!=null){
                    singleParsed2 = JO2.get("key") + "\n" + JO2.get("value") + "\n\n";
                }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Params.put(Integer.parseInt(id),singleParsed2);

        return null;

    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://api.gios.gov.pl/pjp-api/rest/station/findAll");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            names = new ArrayList<>();
            JSONArray JA = new JSONArray(data);
            for(int i = 0; i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                    singleParsed = JO.get("stationName") + "\n\n";
                names.add(JO.get("stationName") + "\n\n");
                    dataParsed += singleParsed;

                coordinate.put(JO.getInt("id"),new LatLng(Double.valueOf((String)JO.get("gegrLat")),Double.valueOf((String)JO.get("gegrLon"))));
                SensorsReader(JO.get("id").toString());

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);
        MapsActivity.setCoordinate(coordinate);
        MainActivity.setNames(names);


    }
}
