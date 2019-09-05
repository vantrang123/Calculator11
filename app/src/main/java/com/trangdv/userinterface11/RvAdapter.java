package com.trangdv.userinterface11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CalculatorViewHolder> {

    private List<String> results = new ArrayList<String>();


    public RvAdapter(List<String> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public CalculatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);

        return new CalculatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculatorViewHolder holder, int position) {

        holder.tvResult.setText(results.get(position));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class CalculatorViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvResult;
        public CalculatorViewHolder(@NonNull View view) {
            super(view);
            tvResult = view.findViewById(R.id.tv_results);
        }
    }
}
