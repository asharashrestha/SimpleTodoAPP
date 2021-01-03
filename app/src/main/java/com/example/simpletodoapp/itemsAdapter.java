package com.example.simpletodoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);

    }

    List<String> items;
    OnLongClickListener longClickListener;

    public itemsAdapter(List<String> items, OnLongClickListener longClickListener ) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Use layout inflator to inflate the view

        View todoView = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        //wrap it inside a view holder
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String item = items.get(i);
        viewHolder.bind(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provider easy access to views that represent each row of the list.
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById((android.R.id.text1));
        }

        public void bind(String item) {
            tvItem.setText((item));
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notify the lostener which position was long clicked
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;

                }
            });

        }
    }
}