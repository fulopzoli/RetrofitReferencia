package com.example.as.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.as.Const.Url;
import com.example.as.Model.ResultsEntity;
import com.example.as.UI.Activity.Reszlet;
import com.example.as.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PokemonRecyAdapter extends RecyclerView.Adapter<PokemonRecyAdapter.myholder> implements Filterable {
    private List<ResultsEntity> lista;
    private List<ResultsEntity> fullList;
    private Context context;
    private ItemBinding itemBinding;



    public void setLista(List<ResultsEntity> lista) {
        this.lista = lista;
        fullList = new ArrayList<>(lista);
        notifyDataSetChanged();
    }

    public PokemonRecyAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        itemBinding = ItemBinding.inflate(inflater, parent, false);
        ViewGroup.LayoutParams layoutParams = itemBinding.getRoot().getLayoutParams();
        layoutParams.width = (int) (parent.getWidth() * 0.3);
        itemBinding.getRoot().setLayoutParams(layoutParams);
        return new myholder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder holder, int position) {
        String[] id = lista.get(position).getUrl().split("/");

        //nagybetűre cserélés
        char char1 = Character.toUpperCase(lista.get(position).getName().charAt(0));
        StringBuilder builder = new StringBuilder(lista.get(position).getName());
        builder.setCharAt(0, char1);
        lista.set(position, lista.get(position)).setName(builder.toString());


        holder.itemBinding.Progressitem.setVisibility(View.VISIBLE);
        holder.itemBinding.Pokenev.setText(lista.get(position).getName());

        Glide.with(context).load(Url.BASEPICURL + id[id.length - 1] + ".png").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.itemBinding.Progressitem.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.itemBinding.Progressitem.setVisibility(View.INVISIBLE);
                return false;
            }
        }).into(holder.itemBinding.pokemonkep);


    }


    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    @Override
    public Filter getFilter() {
        return Keres;
    }

    private Filter Keres = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<ResultsEntity> szurtList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                szurtList.addAll(fullList);
            } else {
                String szoveg = constraint.toString().toLowerCase();
                for (ResultsEntity item : fullList) {
                    if (item.getName().toLowerCase().contains(szoveg)) {
                        szurtList.add(item);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = szurtList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            lista.clear();
            lista.addAll((List<ResultsEntity>) results.values);
            notifyDataSetChanged();
        }
    };

    public class myholder extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;

        public myholder(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.pokemonkep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lista.get(getAdapterPosition()).getUrl();
                    Intent intent = new Intent(context, Reszlet.class);
                    intent.putExtra(Reszlet.POKEMONID, lista.get(getAdapterPosition()).getUrl());
                    context.startActivity(intent);
                }
            });

        }
    }
}


