package com.osama.bait;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class delegateSalesActivity extends AppCompatActivity {
    TextView textviewTitle,number,name,month,year,firstArea,secondArea,thirdArea,fourthArea,fifthArea,comessoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_sales);
        month        = (TextView) findViewById(R.id.delegateMonth);
        year         = (TextView) findViewById(R.id.delegateYear);
        number       = (TextView) findViewById(R.id.delegateNumber);
        name         = (TextView) findViewById(R.id.delegateName);
        firstArea    = (TextView) findViewById(R.id.delegateFirstArea);
        secondArea   = (TextView) findViewById(R.id.delegateSecondtArea);
        thirdArea    = (TextView) findViewById(R.id.delegateThirdtArea);
        fourthArea   = (TextView) findViewById(R.id.delegateFourthArea);
        fifthArea    = (TextView) findViewById(R.id.delegateFiveththArea);
        comessoin    = (TextView) findViewById(R.id.monthalcomession);
        textviewTitle= (TextView) findViewById(R.id.textviewTitle);
        
    }
}
