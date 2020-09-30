package com.ddona.pokemon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ddona.pokemon.databinding.ItemPokemonBinding;
import com.ddona.pokemon.model.Pokemon;

public class PokemonListAdapter extends ListAdapter<Pokemon, PokemonListAdapter.ViewHolder> {
    private static final DiffUtil.ItemCallback<Pokemon> DIFF_CALLBACK = new DiffUtil.ItemCallback<Pokemon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.equals(newItem);
        }
    };

    public PokemonListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPokemonBinding binding
                = ItemPokemonBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPokemonBinding binding;

        public ViewHolder(ItemPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Pokemon pokemon) {
            binding.setPokemon(pokemon);
            binding.executePendingBindings();
        }
    }
}
