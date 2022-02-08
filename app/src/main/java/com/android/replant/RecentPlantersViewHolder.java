package com.android.replant;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentPlantersViewHolder extends RecyclerView.ViewHolder {
    public ImageView recent_planter_picture;
    public RelativeLayout recent_planter_parent;
    public TextView recent_planter_name, recent_planter_country, recent_planter_total;

    public RecentPlantersViewHolder(@NonNull View itemView) {
        super(itemView);
        recent_planter_picture = itemView.findViewById(R.id.item_recent_planter_picture);
        recent_planter_parent  = itemView.findViewById(R.id.item_recommended_parent);
        recent_planter_name   = itemView.findViewById(R.id.item_recent_planter_name);
        recent_planter_total   = itemView.findViewById(R.id.item_recent_planter_total);
        recent_planter_country = itemView.findViewById(R.id.item_recent_planter_country);
    }
}