package com.example.deepbreath;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CouponSearchActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchable);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                //execution come here when an item is clicked from
                //the search results displayed after search form is submitted
                //you can continue from here with user clicked search item
                Toast.makeText(CouponSearchActivity.this,
                        "clicked search result item is"+((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        // search
        handleSearch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch();
    }
    private void handleSearch() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);

            CustomSearchAdapter adapter = new CustomSearchAdapter(this,
                    android.R.layout.simple_dropdown_item_1line,
                    StoresData.filterData(searchQuery));
            listView.setAdapter(adapter);

        }else if(Intent.ACTION_VIEW.equals(intent.getAction())) {
            String selectedSuggestionRowId =  intent.getDataString();
            //execution comes here when an item is selected from search suggestions
            //you can continue from here with user selected search item
            Toast.makeText(this, "selected search suggestion "+selectedSuggestionRowId,
                    Toast.LENGTH_SHORT).show();
        }
    }
}