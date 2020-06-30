package com.osama.bait;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osama.bait.database.DatabaseHelper;
import com.osama.bait.database.area;
import com.osama.bait.database.delegate;
import com.osama.bait.database.delegateSales;

import java.util.List;

public class monthlycomessionAdapter  extends RecyclerView.Adapter<monthlycomessionAdapter.MyViewHolder> {

    private List<delegateSales> delegateSalesList;
    DatabaseHelper databaseHelper ;
    double lastAmount = 0 ;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {


        TextView textviewTitle,number,name,month,year,firstArea,secondArea,thirdArea,fourthArea,fifthArea,comessoin;

        public MyViewHolder(View view)
        {
            super(view);
            month        = (TextView) view.findViewById(R.id.delegateMonth);
            year         = (TextView) view.findViewById(R.id.delegateYear);
            number       = (TextView) view.findViewById(R.id.delegateNumber);
            name         = (TextView) view.findViewById(R.id.delegateName);
            firstArea    = (TextView) view.findViewById(R.id.delegateFirstArea);
            secondArea   = (TextView) view.findViewById(R.id.delegateSecondtArea);
            thirdArea    = (TextView) view.findViewById(R.id.delegateThirdtArea);
            fourthArea   = (TextView) view.findViewById(R.id.delegateFourthArea);
            fifthArea    = (TextView) view.findViewById(R.id.delegateFiveththArea);
            comessoin    = (TextView) view.findViewById(R.id.monthalcomession);
            textviewTitle= (TextView) view.findViewById(R.id.textviewTitle);


        }
    }


    public monthlycomessionAdapter(List<delegateSales> delegateSalesList,DatabaseHelper databaseHelper1,double lastAmount) {
        this.delegateSalesList = delegateSalesList;
        this.databaseHelper    = databaseHelper1;
        this.lastAmount        = lastAmount ;


    }

    @Override
    public monthlycomessionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delegatescomession, parent, false);

        return new monthlycomessionAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(monthlycomessionAdapter.MyViewHolder holder, int position)
    {

        final delegateSales delegateSales = delegateSalesList.get(position);

        int delegateId = delegateSales.getDelegateId() ;
        delegate delegate = databaseHelper. getDelegateInfo(delegateId);
        holder.number.append(delegate.getNumber()+" ");
        holder.name.append(delegate.getName()+" ");
        holder.month.append("التاريخ " +delegateSales.getDate());



        int areaId = delegateSalesList.get(0).getAreaId();
        holder.firstArea.append(" : " +delegateSales.getFirstArea()+" ");
        holder.secondArea.append(" : " +delegateSales.getSecondArea()+" ");
        holder.thirdArea.append(" : " +delegateSales.getThirdArea()+" ");
        holder.fourthArea.append(" : " +delegateSales.getForuthArea()+" ");
        holder.fifthArea.append(" : " +delegateSales.getFifthArea()+" ");
        try {
            holder.textviewTitle.append(delegate.getName());

        }
        catch (Exception e)
        {}
        holder.comessoin.append(lastAmount+" ");

 }




    @Override
    public int getItemCount() {
        return delegateSalesList.size();
    }
}