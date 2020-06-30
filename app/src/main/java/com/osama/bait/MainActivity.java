package com.osama.bait;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.osama.bait.database.area;
import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.delegate;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();





 DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        databaseHelper.deleteAllArea();
        area a = new area();

        a.setAreaName("المنطقة الجنوبية ");
        databaseHelper.insertArea(a);

        area a1 = new area();
        a1.setAreaName("المنطقة الساحلية ");
        databaseHelper.insertArea(a1);

        area a2 = new area();
        a2.setAreaName("المنطقة الشمالية ");
        databaseHelper.insertArea(a2);


        area a3 = new area();
        a3.setAreaName("المنطقة الشرقية ");
        databaseHelper.insertArea(a3);

        area a4 = new area();
        a4.setAreaName("لبنان ");
        databaseHelper.insertArea(a4);



//  databaseHelper.deleteAllDelegate();
//  databaseHelper.deleteAllComession();
//  databaseHelper.deleteAllDelegateSale();
//  databaseHelper.deleteAllSales();

    }

    public void addDelegate(View view) {
        addRing();
        Intent i = new Intent( MainActivity.this, AddDelegets.class);
        startActivity(i);
    }
    public void addSales(View view )
    {
        addRing();
        Intent i = new Intent( MainActivity.this, addSales.class);
        startActivity(i);

    }
    public void delegateSearchSales(View v)
    {
        addRing();
        Intent i = new Intent( MainActivity.this, searchForSales.class);
        startActivity(i);

    }

    public void comessiondelegateSales(View v)
    {
        addRing();
        Intent i = new Intent( MainActivity.this, SearchForComession.class);
        startActivity(i);
    }

    public void addComession(View v)
    {
        addRing();
        Intent i = new Intent( MainActivity.this, AddComession.class);
        startActivity(i);

    }
    public void showAllDelegate(View v )
    {
        addRing();
        Intent intent = new Intent(MainActivity.this,showDelegates.class);
        startActivity(intent);
    }

    public void addRing()
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
    }
}
