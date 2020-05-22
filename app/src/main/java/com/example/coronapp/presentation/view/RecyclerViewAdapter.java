package com.example.coronapp.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coronapp.R;
import com.example.coronapp.presentation.model.Corona;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Corona> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Corona item);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }


    public void add(int position, Corona item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }



    public RecyclerViewAdapter(List<Corona> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Corona currentCorona = values.get(position);
        holder.txtHeader.setText(currentCorona.getCountry());
        holder.txtFooter.setText("Click for more info");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentCorona);
            }
        });


    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public void filterList(List<Corona> filteredList) {
        values = filteredList;
        notifyDataSetChanged();
    }


}
