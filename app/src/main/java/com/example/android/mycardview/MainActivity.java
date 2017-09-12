package com.example.android.mycardview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    String[] c_names = { "India","Indonesia","Iran","Iraq","United Arab Emirates","United Kingdom", "United States","Fiji","Finland","France","South Africa","South Korea","Spain","Sri Lanka","Switzerland","Syria"};
    int[] c_flags = {R.drawable.india,R.drawable.indonesia,R.drawable.iran,R.drawable.iraq,R.drawable.united_arab_emirates,R.drawable.united_kingdom,R.drawable.united_states,R.drawable.fiji,R.drawable.finland,R.drawable.france,R.drawable.south_africa,R.drawable.south_korea,R.drawable.spain,R.drawable.sri_lanka,R.drawable.switzerland,R.drawable.syria};
    Toolbar toolbar;
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Country> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*

        When you write an XML layout, it will be inflated by the Android OS
        which basically means that it will be rendered by creating view object in memory.
         Let's call that implicit inflation (the OS will inflate the view for you).

   */
        //the OS will inflate the your_layout.xml file and use it for this activity
        // When you call setContentView(), it attaches the views it creates from reading the XML to the activity.
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        int count =0;
        for(String Name : c_names)
        {
            arrayList.add( new Country(Name,c_flags[count]));
            count++;

        }
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    /* Inflating is the process of adding a view(.xml) to activity on runtime.
    You can also inflate views explicitly by using the LayoutInflater. In that case you have to:

       1 Get an instance of the LayoutInflater
        2  Specify the XML to inflate
         3  Use the returned View
    */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        //Returns the currently set action view for this menu item.
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(menuItem);

        //Sets a listener for user actions within the SearchView.
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    //Called when the user submits the query.
    // returns true if the query has been handled by the listener, false to let the SearchView perform the default action.
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override

    // Called when the query text is changed by the user.
    //false if the SearchView should perform the default action of showing any suggestions if available,
    // true if the action was handled by the listener.
    public boolean onQueryTextChange(String newText) {

        newText =newText.toLowerCase();
        ArrayList<Country> newlist = new ArrayList<>();
        for(Country country : arrayList)
        {
            String name = country.getName().toLowerCase();
            if(name.contains(newText))
                newlist.add(country);

        }
        // change the adapter.
        adapter.setFilter(newlist);


        return true;
    }
}
