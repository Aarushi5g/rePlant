package com.android.replant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kotlin.collections.MapAccessorsKt;

public class HomeFragment extends Fragment {
    private Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecentPlantersAdapter recentPlantersAdapter;
    private ArrayList<RecentPlantersModel> recentPlanters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapterType(view);
        setAdapter();

        RelativeLayout plant_for_me = view.findViewById(R.id.plant_forme);
        plant_for_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPlantData() {
        recentPlanters = new ArrayList<>();

        recentPlanters.add(new RecentPlantersModel("Erica", "Indonesia", "7", R.drawable.image1));
        recentPlanters.add(new RecentPlantersModel("Angelica", "Russia", "13", R.drawable.image2));
        recentPlanters.add(new RecentPlantersModel("Joseph", "Italy", "15", R.drawable.image3));
        recentPlanters.add(new RecentPlantersModel("John", "India", "16", R.drawable.image4));
    }

    private void setAdapterType(View view) {
        recyclerView    = view.findViewById(R.id.recyclerView);
        layoutManager   = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        initPlantData();
        //todo 1. Add the new object to the parameter list.
        recentPlantersAdapter = new RecentPlantersAdapter(mContext, recentPlanters);
        recyclerView.setAdapter(recentPlantersAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}