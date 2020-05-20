package com.maxime.android.myproject.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.model.Pokemon;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Pokemon> values;


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

    public void add(int position, Pokemon item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }


    public MyAdapter(List<Pokemon> myDataset) {
        values = myDataset;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_adapter, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder  holder, final int position) {
        final Pokemon currentPokemon = values.get(position);
        holder.txtHeader.setText(currentPokemon.getName());
        holder.txtFooter.setText(currentPokemon.getUrl());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}
