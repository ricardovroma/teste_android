package com.example.ricardo.myapplication.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ricardo.myapplication.R;
import com.example.ricardo.myapplication.adapters.ProfileWithdrawalAdapter;
import com.example.ricardo.myapplication.api.ApiClient;
import com.example.ricardo.myapplication.api.ProfileWithdrawal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private View view;
    private DatePicker datepiker;
    private TextView date;
    private SimpleDateFormat dateFormatter;
    private Calendar newCalendar;
    private DatePickerDialog datePickerDialog;
    private ApiClient api;
    private ProgressBar progress;
    private RecyclerView rv_profile_withdrawal;
    private TextView no_results;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if(appCompatActivity != null && appCompatActivity.getSupportActionBar() != null)
            appCompatActivity.getSupportActionBar().setTitle("Valores");

        newCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        api = new ApiClient();

        view = inflater.inflate(R.layout.fragment_main, container, false);

        no_results = (TextView) view.findViewById(R.id.no_results);
        rv_profile_withdrawal = (RecyclerView) view.findViewById(R.id.rv_profile_withdrawal);
        rv_profile_withdrawal.setLayoutManager(new LinearLayoutManager(getActivity()));

        progress = (ProgressBar) view.findViewById(R.id.progress);
        date = (TextView) view.findViewById(R.id.date);
        date.setText(dateFormatter.format(newCalendar.getTime()));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        date.setText(dateFormatter.format(newDate.getTime()));
                        getData(dateFormatter.format(newDate.getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        getData(dateFormatter.format(newCalendar.getTime()));
        return view;
    }

    private void getData(String date) {
        progress.setVisibility(View.VISIBLE);
        no_results.setVisibility(View.GONE);
        rv_profile_withdrawal.setAdapter(new ProfileWithdrawalAdapter(null));
        api.service.getProfileWithdrawal(date).enqueue(new Callback<ArrayList<ProfileWithdrawal>>() {
            @Override
            public void onResponse(Call<ArrayList<ProfileWithdrawal>> call, Response<ArrayList<ProfileWithdrawal>> response) {
                if (response.isSuccessful()) {
                    progress.setVisibility(View.GONE);

                    if(response.body().size() > 0) {
                        rv_profile_withdrawal.setAdapter(new ProfileWithdrawalAdapter(response.body()));
                    } else {
                        no_results.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProfileWithdrawal>> call, Throwable t) {
                t.printStackTrace();
                progress.setVisibility(View.GONE);
            }
        });
    }

}
