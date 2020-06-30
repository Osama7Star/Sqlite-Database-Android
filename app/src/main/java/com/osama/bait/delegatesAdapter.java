package com.osama.bait;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.area;
import com.osama.bait.database.delegate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class delegatesAdapter extends RecyclerView.Adapter<delegatesAdapter.MyViewHolder> {
    area area ;
    private List<delegate> delegateList;
    DatabaseHelper databaseHelper ;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView number, name, area ;
        ImageView image;
        Button addDelegateSales,updateDelegate,deleteDelegate ;


        public MyViewHolder(View view)
        {
            super(view);
            number           = (TextView) view.findViewById(R.id.delegatenumberR);
            name             = (TextView) view.findViewById(R.id.delegatenameR);
            area             = (TextView) view.findViewById(R.id.delegateArea);
            addDelegateSales = (Button)   view.findViewById(R.id.addDelegateSales);
            updateDelegate   = (Button)   view.findViewById(R.id.updateDelegate);
            deleteDelegate   = (Button)   view.findViewById(R.id.deleteDelegate);
            image            = (ImageView)view.findViewById(R.id.delegateImageR);


        }
    }


    public delegatesAdapter(List<delegate> delegateListList,DatabaseHelper databaseHelper1) {
        this.delegateList = delegateListList;
        this.databaseHelper = databaseHelper1;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delegateview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        final delegate delegate = delegateList.get(position);


        Uri uri = Uri.parse(delegate.getImageUrl());
        holder.image.setImageURI(uri);
        holder.number.setText(delegate.getNumber() + " ");
        holder.name.setText(delegate.getName());

         area = databaseHelper.getAreaName(delegate.getMainArea());
         holder.area.setText(area.getAreaName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Context context = v.getContext();
                Log.i("testingngnggngngn",delegate.getDelegateId()+" ");

                Intent intent = new Intent(context, ShowDelegateInfo.class);
                intent.putExtra("delegateId",delegate.getDelegateId());
                intent.putExtra("delegateNumber",delegate.getNumber());
                intent.putExtra("delegateName",delegate.getName());
                intent.putExtra("delegteImageUrl",delegate.getImageUrl());
                intent.putExtra("delegateMainAreaName",area.getAreaName());
                intent.putExtra("delegateMainArea",delegate.getMainArea());


                context.startActivity(intent);
            }
        });

        holder.addDelegateSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
         final       Context context = v.getContext();

                Intent intent = new Intent(context, addSales.class);
                intent.putExtra("delegateId",delegate.getDelegateId());
                context.startActivity(intent);


            }
        });
        holder.updateDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Context context = v.getContext();
                Log.i("testingngnggngngn",delegate.getDelegateId()+" ");

                Intent intent = new Intent(context, editDelegate.class);
                intent.putExtra("delegateId",delegate.getDelegateId());
                intent.putExtra("delegateNumber",delegate.getNumber());
                intent.putExtra("delegateName",delegate.getName());
                intent.putExtra("delegateMainAreaName",area.getAreaName());
                intent.putExtra("delegateMainArea",delegate.getMainArea());
                intent.putExtra("delegateImageurl",delegate.getImageUrl());



                context.startActivity(intent);


            }
        });


        holder.deleteDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {

              final  Context context = v.getContext();

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                // Setting Dialog Title
                alertDialog.setTitle("حذف مندوب ");

                // Setting Dialog Message
                alertDialog.setMessage("هل تريد حذف هذا المندوب فعلا ؟");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.question);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        int delegateId = delegate.getDelegateId();
                        databaseHelper.deleteDelegateAccToId(delegateId);
                        Log.i("Deleted","Deleted Successfully");

                        Intent intent = new Intent(context, showDelegates.class);
                        intent.putExtra("deleteIntent",10);

                        context.startActivity(intent);                    }
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
        });



    }





    @Override
    public int getItemCount() {
        return delegateList.size();
    }
}