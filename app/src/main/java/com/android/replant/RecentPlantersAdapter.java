package com.android.replant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecentPlantersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<RecentPlantersModel> planters;

    public RecentPlantersAdapter(Context context, ArrayList<RecentPlantersModel> planters) {
        this.context = context;
        this.planters = planters;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_planters, parent, false);
        return new RecentPlantersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setImage(((RecentPlantersViewHolder) holder).recent_planter_picture, planters.get(position).getRecentPlanterPicture());
        setTitle(((RecentPlantersViewHolder) holder).recent_planter_name, planters.get(position).getRecentPlanterName());
        setCountry(((RecentPlantersViewHolder) holder).recent_planter_country, planters.get(position).getRecentPlanterCountry());
        setPrice(((RecentPlantersViewHolder) holder).recent_planter_total, planters.get(position).getRecentPlanterTotal());
        setOnClick(((RecentPlantersViewHolder) holder).recent_planter_parent);
    }

    private void setImage(final ImageView imageView, int image) {
        imageView.setBackgroundResource(image);
    }

    private void setTitle(TextView textView, String plantTitle) {
        textView.setText(plantTitle);
    }

    private void setCountry(TextView textView, String plantCountry) {
        textView.setText(plantCountry);
    }

    private void setPrice(TextView textView, String plantPrice) {
        textView.setText(plantPrice);
    }

    private void setOnClick(RelativeLayout button) {
        //button.setOnClickListener(view -> context.startActivity(new Intent(context, HomeDetailActivity.class)));
    }

    @Override
    public int getItemCount() {
        return planters.size();
    }
}
