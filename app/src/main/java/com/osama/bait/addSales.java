package com.osama.bait;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.sales;
import java.text.DateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.Calendar;



public class addSales extends AppCompatActivity {

    EditText firstArea,secondArea,thirdArea,fourthArea,fifthArea;
    TextView textviewAddSales;
    Button addSales;
    DatabaseHelper databaseHelper ;
    int  delegateId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        firstArea  = (EditText)findViewById(R.id.firstArea);
        secondArea = (EditText)findViewById(R.id.secondArea);
        thirdArea  = (EditText)findViewById(R.id.thirdArea);
        fourthArea = (EditText)findViewById(R.id.fourthArea);
        fifthArea  = (EditText)findViewById(R.id.fifthArea);
        addSales   = (Button)findViewById(R.id.addSales);
        textviewAddSales = (TextView)findViewById(R.id.textviewAddSales);



        Calendar c = Calendar.getInstance();
        int year1  = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH)+1;
        int day1   = c.get(Calendar.DAY_OF_MONTH);
        String date= day1+"/"+month1+"/"+year1;
        Log.i("GetDate",day1+"/"+month1+"/"+year1);

        textviewAddSales.setText("إدخال كمية المبيعات "+"\n"+ date);
        Intent intent  = getIntent();
        delegateId     = intent.getIntExtra("delegateId",99);
      Log.i("delegateId",delegateId+" ");


    }
    public void addSales(View view)
    {
        addRing();
        databaseHelper   = new DatabaseHelper(getApplicationContext());


        if (     firstArea.getText().toString().equals("")|| secondArea.getText().toString().equals("") ||
                 thirdArea.getText().toString().equals("")|| fourthArea.getText().toString().equals("") ||
                 fifthArea.getText().toString().equals("")
           )
        {

            Toast.makeText(addSales.this,"الرجاء تعبئة كل الحقول",Toast.LENGTH_LONG).show();



        }
        else
        {

            Calendar c = Calendar.getInstance();
         final   int year  = c.get(Calendar.YEAR);
         final   int month = c.get(Calendar.MONTH)+1;
         final   int day   = c.get(Calendar.DAY_OF_MONTH);

            int count = databaseHelper.getDelegateSalesCount(delegateId,month,year);

            if (count>0)
            {
                ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(addSales.this);

                // Setting Dialog Title
                alertDialog.setTitle("المبيعات موجودة  ");

                // Setting Dialog Message
             alertDialog.setMessage("الميبعات لهذا المستخدم موجودة مسبقا");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.question);

                alertDialog.show();
            }
            else
                {
                    int firsAreaSales   = Integer.parseInt(firstArea.getText().toString());
                    int secondAreaSales = Integer.parseInt(secondArea.getText().toString());
                    int thirdAreaSales  = Integer.parseInt(thirdArea.getText().toString());
                    int fourthAreaSales = Integer.parseInt(fourthArea.getText().toString());
                    int fifthAreaSales  = Integer.parseInt(fifthArea.getText().toString());




                    String date= day+"/"+month+"/"+year;


                    sales s1 = new sales(1,day,month,year,firsAreaSales,date);
                    sales s2 = new sales(2,day,month,year,secondAreaSales,date);
                    sales s3 = new sales(3,day,month,year,thirdAreaSales,date);
                    sales s4 = new sales(4,day,month,year,fourthAreaSales,date);
                    sales s5 = new sales(5,day,month,year,fifthAreaSales,date);

                    databaseHelper.insertSalesDelegate(s1,delegateId);
                    databaseHelper.insertSalesDelegate(s2,delegateId);
                    databaseHelper.insertSalesDelegate(s3,delegateId);
                    databaseHelper.insertSalesDelegate(s4,delegateId);
                    databaseHelper.insertSalesDelegate(s5,delegateId);

                    Toast.makeText(addSales.this,"تم إضافة المبيعات بنجاح",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(addSales.this,monthlyComession.class);
                    intent.putExtra("delegateId",delegateId);
                    startActivity(intent);
                    finish();

                }
        }

    }
    public void addRing()
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(addSales.this);
    }
}
