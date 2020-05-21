package com.maxime.android.myproject.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maxime.android.myproject.R;
import com.maxime.android.myproject.model.Ability;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    private  List<Ability> AbilityList;
    private  List<Ability> AbilityListFull;
    private  OnItemClickListener listener;

    public Filter getFilter() {
        return AbilityFilter;
    }

    private Filter AbilityFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Ability> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(AbilityListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Ability ability : AbilityListFull){
                    if(ability.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(ability);
                    }
                }
            }
            FilterResults res = new FilterResults();
            res.values = filteredList;
            return res;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults res) {
            AbilityList.clear();
            AbilityList.addAll((List) res.values);
            notifyDataSetChanged();
        }
    };

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
        AbilityList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        AbilityList.remove(position);
        notifyItemRemoved(position);
    }


    public MyAdapter2(List<Ability> myDataset, OnItemClickListener listener) {

        this.AbilityList = myDataset;
        this.listener = listener;
        AbilityListFull = new ArrayList<>(AbilityList);

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
        final Ability currentAbility = AbilityList.get(position);
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
        return AbilityList.size();
    }


}
