package com.osama.bait;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.area;
import com.osama.bait.database.delegate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class searchForSales extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> ls = new ArrayList<>();
    ArrayList<String> ldateMonth = new ArrayList<>();
    ArrayList<String> ldateYear  = new ArrayList<>();
    EditText monthSearch,yearSearch;

    List<delegate> l;

    int delegateId;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_sales);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper = new DatabaseHelper(getApplicationContext());

        l = databaseHelper.getAllToDelegate();
        for (int i = 0; i < l.size(); i++) {
            ls.add(l.get(i).getName());
        }

        Spinner spinName  = (Spinner) findViewById(R.id.searchSpinnerName);
        monthSearch =(EditText)findViewById(R.id.monthSearch);
        yearSearch =(EditText)findViewById(R.id.yearSearch);


        spinName.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aName  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ls);


     aName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
     spinName.setAdapter(aName);


    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        delegateId = l.get(position).getDelegateId();




    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void searchSaels(View v )
    {
        addRing();
        if (monthSearch.getText().toString().equals("")  || (yearSearch.getText().toString().equals("")))
        {
            Toast.makeText(searchForSales.this,"الرجاء تعبئة كل الحقول",Toast.LENGTH_LONG).show();


        }
        else
            {
                int month = Integer.parseInt(monthSearch.getText().toString());
                int year  = Integer.parseInt(yearSearch.getText().toString());
                Log.i("UserInfo", "delegateId : " + delegateId + " |  Month : " + month + " -  year " + year);
                Intent intent = new Intent(searchForSales.this, monthlyComession.class);
                intent.putExtra("delegateIdFromSearch", delegateId);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );
                startActivity(intent);

                finish();
            }
    }

    public void addRing()
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(searchForSales.this);
    }

}