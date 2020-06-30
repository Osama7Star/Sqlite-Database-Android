package com.osama.bait;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.delegate;

public class editDelegate extends AppCompatActivity {
    Button btnDelegateEdit;
    DatabaseHelper databaseHelper ;
    int delegateId ,delegateNumber ,mainArea;
    String delegateName, mainAreaName , imageUrl;

    EditText delegateNumberEdit,delegateNameEdit;
    TextView delegateMainarea;
    ImageView delegateImageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delegate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper   = new DatabaseHelper(getApplicationContext());

        btnDelegateEdit = (Button)findViewById(R.id.btnDelegateEdit);
        Intent intent  = getIntent();
        delegateId     = intent.getIntExtra("delegateId",99);
        delegateNumber = intent.getIntExtra("delegateNumber",99);
        delegateName   = intent.getStringExtra("delegateName");
        mainAreaName   = intent.getStringExtra("delegateMainAreaName");
        mainArea       = intent.getIntExtra ("delegateMainArea",99);
        imageUrl       = intent.getStringExtra("delegateImageurl");


        Log.i("delegateId",delegateId+"  -");
        Log.i("mainArea",mainArea+"  -");

         delegateNumberEdit = (EditText)findViewById(R.id.delegateNumberEdit);
         delegateNameEdit   = (EditText)findViewById(R.id.delegateNameEdit);
         delegateMainarea   = (TextView)findViewById(R.id.delegateMainarea);
         delegateImageEdit  = (ImageView) findViewById(R.id.delegateImageEdit);


        Uri uri = Uri.parse(imageUrl);

        delegateNumberEdit.append(delegateNumber+" ");
        delegateNameEdit.append(delegateName+" ");
        delegateMainarea.append(mainAreaName+ " ");
        delegateImageEdit.setImageURI(uri);





    }

    public void delegateEdit(View v )
    {
        addRing();
        int number = Integer.parseInt(delegateNumberEdit.getText().toString()) ;
        String name = delegateNameEdit.getText().toString();
        if (delegateNumberEdit.getText().toString().equals("")  || (delegateNameEdit.getText().toString().equals("")))
        {
            Toast.makeText(editDelegate.this,"الرجاء تعبئة كل الحقول",Toast.LENGTH_LONG).show();


        }
        else{
            delegate delegate = new delegate(number, name,"imageUrl",mainArea);
            databaseHelper.updateDelegate(delegateId,delegate);
            Intent i = new Intent(editDelegate.this, showDelegates.class);
            startActivity(i);
            finish();
        }




    }
    public void addRing()
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(editDelegate.this);
    }

}
