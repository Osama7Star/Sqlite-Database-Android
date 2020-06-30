package com.osama.bait;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.delegate;
import com.osama.bait.database.delegateSales;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class monthlyComession extends AppCompatActivity {

    private List<delegateSales> delegateSalesList = new ArrayList<>();
    private List<Double> comessionList = new ArrayList<>();
    private List<Integer> amountList   = new ArrayList<>();
    private List<delegateSales> delegateSalesListTwo = new ArrayList<>();
    int month , year;
    int fromWhere ;


    private RecyclerView recyclerView;
    private monthlycomessionAdapter monthlycomessionAdapter1;
    DatabaseHelper databaseHelper ;
    double lastAmount =  0 ;


    int delegateId;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_comession);


        databaseHelper= new DatabaseHelper(getApplicationContext());
        fromWhere  = 0  ;
        Intent intent = getIntent();

        if (intent.hasExtra("delegateIdFromSearch"))
        {

              delegateId   = intent.getIntExtra("delegateIdFromSearch",99);

            int  month        = intent.getIntExtra("month",99);
            int  year         = intent.getIntExtra("year",99);
             delegateSalesList  = databaseHelper.getDelegateSalesAccordingDate(delegateId,month,year);



            if (delegateSalesList.size()==0)
            {
                fromWhere   = 1 ;
                Toast.makeText(monthlyComession.this, "\n"+ "]   يبدو ان هذا المندوب ليس لديه مبيعات في هذا التاريخ "+month +"/"+year+"]", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(monthlyComession.this,searchForSales.class);
                startActivity(intent1);

            }



        }
        else
        {

                fromWhere = 1;
                Intent intent1    = getIntent();
                delegateId        = intent1.getIntExtra("delegateId",99);
                delegateSalesList = databaseHelper.getDelegateSales( delegateId);


        }

        for (int i = 0 ; i< delegateSalesList.size();i++)
        {
            amountList.add(delegateSalesList.get(i).getAmount());
        }

        delegateSales d = new delegateSales();

        lastAmount = 0 ;
      delegate  de = databaseHelper.getAllToDelegateAcordingToId(delegateId);
        int delegateMainArea1 = de.getMainArea();

        for (int i = 0  ; i < delegateSalesList.size();i++)
        {
            month = delegateSalesList.get(i).getMonth();
            year  = delegateSalesList.get(i).getYear();
            double amount = delegateSalesList.get(i).getAmount();
            if (delegateSalesList.get(i).getAreaId()==delegateMainArea1)
            {
              if (amount>10000000)
                {

                    lastAmount = lastAmount + (amount-10000000)*0.07;
                    lastAmount = lastAmount + (10000000*0.05);
                }
                else
                {
                    lastAmount = lastAmount + (amount*0.05);

                }
            }
            else
            {

                if (amount>10000000)
                {
                    lastAmount = lastAmount + (amount-10000000)*0.04;
                    lastAmount = lastAmount + (10000000*0.03);
                }
                else
                {
                    lastAmount = lastAmount + (amount*0.03);

                }


                comessionList.add(lastAmount);

            }

        }
        for (int i = 0  ; i < delegateSalesList.size();i=i+5)
        {

            d.setDelegateSalesId(delegateSalesList.get(i).getDelegateSalesId());
            delegateId = delegateSalesList.get(i).getDelegateId();


            d.setDelegateId(delegateSalesList.get(i).getDelegateId());
            d.setAreaId(delegateSalesList.get(i).getAreaId());
            d.setDate(delegateSalesList.get(i).getDate());
            d.setMonth(delegateSalesList.get(i).getMonth());
            d.setYear(delegateSalesList.get(i).getMonth());


            for(int j= 0 ; j <amountList.size();j=j+5)
            {

                try
                {
                    d.setFirstArea (amountList.get(j));
                    d.setSecondArea(amountList.get(j+1));
                    d.setThirdArea (amountList.get(j+2));
                    d.setForuthArea(amountList.get(j+3));
                    d.setFifthArea (amountList.get(j+4));

                    d.setComession(comessionList.get(j));


                }
                catch (Exception e)
                {

                }
 }


            delegateSalesListTwo.add(d);
            d= new delegateSales();

        }



        monthlycomessionAdapter1 = new monthlycomessionAdapter(delegateSalesListTwo,databaseHelper,lastAmount);


        if (fromWhere==1)
        {
            databaseHelper.addDelegateComession(delegateId,lastAmount,month , year,getDate() );


        }


        delegateSalesList.clear();
        lastAmount = 0 ;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewMonthly);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(monthlycomessionAdapter1);



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
}
