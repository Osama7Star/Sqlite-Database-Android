package com.osama.bait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.area;
import com.osama.bait.database.delegate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddDelegets extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final int PICK_IMAGE = 1;

    ArrayList<String> ls = new ArrayList<>();
    List<area> l ;
    int idOfArea = 0   ;

    Button addDelegateButton ;
    ImageView myimagemyimage;
    Uri imagePath ;
    Uri myUri;

    DatabaseHelper databaseHelper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delegets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        addDelegateButton = (Button)findViewById(R.id.addDelegateButton);
         databaseHelper   = new DatabaseHelper(getApplicationContext());

       l = databaseHelper.getAllToArea();
        for (int i = 0; i < l.size(); i++) {
            ls.add(l.get(i).getAreaName());
        }

        myimagemyimage = (ImageView)findViewById(R.id.myimagemyimage);

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ls);


        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        idOfArea = l.get(position).getAreaId();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void addDelegate(View view )
    {
        addRing();
        EditText delegateNumberT  = (EditText)findViewById(R.id.delegateNumber);
        EditText delegateNameT    = (EditText)findViewById(R.id.delegateName);

        if (delegateNumberT.getText().toString().equals("")  || (delegateNameT.getText().toString().equals("")))
        {

            Toast.makeText(AddDelegets.this,"الرجاء تعبئة كل الحقول",Toast.LENGTH_LONG).show();

        }
        else
            {
                int delegateNumber  = Integer.parseInt(delegateNumberT.getText().toString()) ;
                String delegateName = delegateNameT.getText().toString();
                delegate de = new delegate(delegateNumber,delegateName,imagePath.toString(),idOfArea);

                databaseHelper.insertDeleget(de);
                Intent intent = new Intent(AddDelegets.this,showDelegates.class);
                intent.putExtra("myUri",myUri);
                startActivity(intent);
                Toast.makeText(AddDelegets.this,"تم إضافة المندوب بنجاح ",Toast.LENGTH_LONG).show();

                finish();
            }




    }



    public void AddImage(View view)
    {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action

            myimagemyimage.setImageURI(data.getData());


            myUri =  data.getData();

            File f = new File(data.getData().getPath());
            imagePath = data.getData();
            String imageName = f.getName();

            Log.i("ImagePathpath",            imagePath+"") ;
            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);


        }
    }
    public void addRing()
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddDelegets.this);
    }
}
