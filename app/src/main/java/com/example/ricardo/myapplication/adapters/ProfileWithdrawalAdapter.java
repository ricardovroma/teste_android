package com.example.ricardo.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ricardo.myapplication.R;
import com.example.ricardo.myapplication.api.ProfileWithdrawal;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ricardo on 17/08/16.
 */
public class ProfileWithdrawalAdapter extends RecyclerView.Adapter<ProfileWithdrawalViewHolder> {

    private ArrayList<ProfileWithdrawal> items;

    public ProfileWithdrawalAdapter(ArrayList<ProfileWithdrawal> items) {
        this.items = items;
    }

    @Override
    public ProfileWithdrawalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile_withdrawal, parent, false);

        return new ProfileWithdrawalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileWithdrawalViewHolder holder, int position) {
        NumberFormat currentFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        ProfileWithdrawal item = items.get(position);
        holder.account.setText(item.account);
        holder.balance.setText(currentFormat.format(Double.parseDouble(item.balance)));
        holder.retrieval.setText(currentFormat.format(Double.parseDouble(item.retrieval)));

        holder.total.setText(
                currentFormat.format(Double.parseDouble(item.balance) - Double.parseDouble(item.retrieval))
        );
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }
}

class ProfileWithdrawalViewHolder extends RecyclerView.ViewHolder {

    TextView account;
    TextView balance;
    TextView retrieval;
    TextView total;

    public ProfileWithdrawalViewHolder(View itemView) {
        super(itemView);

        account = (TextView) itemView.findViewById(R.id.account);
        balance = (TextView) itemView.findViewById(R.id.balance);
        retrieval = (TextView) itemView.findViewById(R.id.retrieval);
        total = (TextView) itemView.findViewById(R.id.total);
    }
}