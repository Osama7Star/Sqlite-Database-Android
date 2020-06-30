package com.osama.bait;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.osama.bait.database.delegate;

public class ShowDelegateInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_delegate_info);

        TextView DelegateName     = (TextView)findViewById(R.id.txtDelegateName);
        TextView DelegateMainArea = (TextView)findViewById(R.id.txtDelegateMainArea);
        ImageView DelegateImage   = (ImageView)findViewById(R.id.imgDelegateImage);


        Intent intent = getIntent();

            Log.i("CommingFromsearch","I'm Comming from search");
            String delegateName  = intent.getStringExtra("delegateName");
            String mainArea      = intent.getStringExtra("delegateMainAreaName");
            String imageUrl      = intent.getStringExtra("delegteImageUrl");


        DelegateName.setText("إسم المندوب : "+delegateName);
        DelegateMainArea.setText(" المنطقة الرئيسية : "+mainArea);
        Uri uri = Uri.parse(imageUrl);
        DelegateImage.setImageURI(uri);

    }
}
