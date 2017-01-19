package com.mvpsmallproject.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvpsmallproject.R;
import com.mvpsmallproject.model.Profile;
import com.mvpsmallproject.model.Ribot;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;

    private List<Ribot> data;

    public Adapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void loadData(List<Ribot> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View screen = LayoutInflater
                .from(context)
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(screen);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ribot ribot = data.get(position);
        Profile profile = ribot.getProfile();
        String first = profile.getName().getFirst();
        TextView textView = holder.textView;
        textView.setText(first);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = RibotDetailActivity.getStartIntent(context);
                context.startActivity(startIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
