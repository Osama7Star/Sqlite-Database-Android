package com.osama.bait;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.delegate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddComession extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> ls = new ArrayList<>();
    EditText monthSearch,yearSearch,amountAddComession;

    List<delegate> l;

    int delegateId;
    String delegateName ;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_comession2);
        l = databaseHelper.getAllToDelegate();
        for (int i = 0; i < l.size(); i++) {
            ls.add(l.get(i).getName());
        }

        Spinner spinName  = (Spinner) findViewById(R.id.spinAddComession);
        monthSearch  =(EditText)findViewById(R.id.monthAddComession);
        yearSearch   =(EditText)findViewById(R.id.yearAddComession);
        amountAddComession =(EditText)findViewById(R.id.AmountAddComession);


        spinName.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aName  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ls);


        aName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinName.setAdapter(aName);

    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        delegateId   = l.get(position).getDelegateId();
        delegateName = l.get(position).getName() ;





    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void addComession(View v)
    {
        addRing();

        if(yearSearch.getText().toString().equals("")|| monthSearch.getText().toString().equals("")||amountAddComession.getText().toString().equals(""))
        {
            Toast.makeText(AddComession.this,"الرجاء تعبئة كل الحقول",Toast.LENGTH_LONG).show();



        }
        else
            {
                final  int year   = Integer.parseInt(yearSearch.getText().toString());
                final  int month  = Integer.parseInt(monthSearch.getText().toString());
                final  int comessionAmount = Integer.parseInt(amountAddComession.getText().toString());
                int rowNumber = databaseHelper.getDelegateComessionCount(delegateId,month,year);
                if (rowNumber>0)
                {
                    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddComession.this);

                    // Setting Dialog Title
                    alertDialog.setTitle("العمولة موجودة مسبقا ");

                    // Setting Dialog Message
                    alertDialog.setMessage("هل تريد إستبدالها");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.question);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            databaseHelper.addDelegateComession(delegateId,comessionAmount,month , year,getDate() );

                            Toast.makeText(getApplicationContext(), "تم إستبدال العمولة بنجاح", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();
                }
                else
                {
                    databaseHelper.addDelegateComession(delegateId,comessionAmount,month , year,getDate() );
                    Toast.makeText(getApplicationContext(), "تم إضافة العمولة بنجاح", Toast.LENGTH_SHORT).show();

                }
            }
    }
    String getDate()
    {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);


        String date= day+"/"+month+"/"+year;
        return date ;
    }
    public void addRing()
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddComession.this);
    }
}
