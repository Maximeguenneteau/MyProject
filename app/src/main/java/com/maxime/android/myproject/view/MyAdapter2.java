package com.maxime.android.myproject.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.model.Ability;

import java.util.List;


public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private  List<Ability> values;
    private  OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Ability item);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Ability item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }


    public MyAdapter2(List<Ability> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;

    }


    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_adapte_bis, parent, false);

        ViewHolder vh2 = new ViewHolder(v);
        return vh2;
    }


    @Override
    public void onBindViewHolder(ViewHolder  holder, final int position) {
        final Ability currentAbility = values.get(position);
        holder.txtHeader.setText(currentAbility.getName());
        holder.txtFooter.setText(currentAbility.getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentAbility);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}
