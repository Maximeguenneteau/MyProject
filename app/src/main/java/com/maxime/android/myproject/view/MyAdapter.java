package com.maxime.android.myproject.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.model.Pokemon;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
    private  List<Pokemon> PokemonList;
    private  List<Pokemon> pokemonListFull;
    private  OnItemClickListener listener;

    @Override
    public Filter getFilter() {
        return pokemonFilter;
    }


    private Filter pokemonFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pokemon> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(pokemonListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Pokemon pokemon : pokemonListFull){
                    if(pokemon.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(pokemon);
                    }
                }
            }
            FilterResults res = new FilterResults();
            res.values = filteredList;
            return res;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults res) {
            PokemonList.clear();
            PokemonList.addAll((List) res.values);
            notifyDataSetChanged();
        }
    };

    public interface OnItemClickListener {
        void onItemClick(Pokemon item);
    }


public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView txtHeader;
    public TextView txtFooter;
    public View layout;

    public ViewHolder(View v) {
        super(v);
        layout = v;
        txtHeader = (TextView) v.findViewById(R.id.firstLine);
    }
}

    public void add(int position, Pokemon item) {
        PokemonList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        PokemonList.remove(position);
        notifyItemRemoved(position);
    }


    public MyAdapter(List<Pokemon> myDataset, OnItemClickListener listener) {
        this.PokemonList = myDataset;
        this.listener = listener;
        pokemonListFull = new ArrayList<>(PokemonList);
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
        final Pokemon currentPokemon = PokemonList.get(position);
        holder.txtHeader.setText(currentPokemon.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentPokemon);
            }
        });
        }

    @Override
    public int getItemCount() {
        return PokemonList.size();
    }


    }
