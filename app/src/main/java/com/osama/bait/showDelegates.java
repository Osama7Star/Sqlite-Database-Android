package com.osama.bait;

import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.area;
import com.osama.bait.database.delegate;

import java.util.ArrayList;
import java.util.List;

public class showDelegates extends AppCompatActivity {

    private List<delegate>   delegateList = new ArrayList<>();
    private RecyclerView     recyclerView;
    private delegatesAdapter delegatesAdapter;
    DatabaseHelper databaseHelper ;
    Button button ;
    Uri myUri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_delegates);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper= new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();




        if (intent.hasExtra("deleteIntent"))
        {
            Toast.makeText(showDelegates.this," تم حذف المندوب بنجاح",Toast.LENGTH_SHORT).show();

        }
        delegateList= databaseHelper. getAllToDelegate();

        delegatesAdapter = new delegatesAdapter(delegateList,databaseHelper);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(delegatesAdapter);

        button = (Button)findViewById(R.id.deleteDelegate);





    }

    public void deleteDelegate(View view)
    {
        
    }

}
