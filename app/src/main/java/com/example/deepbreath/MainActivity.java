package com.example.deepbreath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    public static TextView data;
    protected static ArrayList<String> names;
    public static ListView listView;

    private static SearchView searchView;
    public static ArrayAdapter<String> arrayAdapter;




/*
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] animalNameList;
    ArrayList<AnimalNames> arraylist = new ArrayList<AnimalNames>();
*/


    public static void setNames(ArrayList<String> L){names = L;}

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);


                    return true;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent2);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Button click;
        click = (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.data);


            fetchData process = new fetchData();
            process.execute();


        //------------------------------------------------------
      /*  while(process.getStatus()== AsyncTask.Status.RUNNING)
        {

        }*/
   /*   names = new ArrayList<>();
        names.add("Wrocław");
        names.add("Łódź");
        names.add("Warszawa");
        names.add("Białystok");
        names.add("Zduńska Wola");*/
        //-----------------------------------------------------------






        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_m:
                //start search dialog
                super.onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        names = new ArrayList<>();
        SharedPreferences sharedPref2 = this.getPreferences(Context.MODE_PRIVATE);
        int j = sharedPref2.getInt("names_size", -77);
        for (int i=0; i < j; i++) {
            names.add(sharedPref2.getString(Integer.toString(i),"null"));
        }

/*
        listView = (ListView) findViewById(R.id.listView);
        //ListViewAdapter adapter = new ListViewAdapter(this,names);
        //listView.setAdapter(adapter);


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);
        searchView = (SearchView) findViewById(R.id.searchView);
*/

    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("names_size",names.size());
        int i = 0;
        for(String S: names) {
            editor.putString(Integer.toString(i), S);
            i++;
        }
        editor.apply();
    }


/*    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    public boolean onQueryTextChange(String newText) {
        String text = newText;
      //  adapter.filter(text);
        return false;
    }*/
}