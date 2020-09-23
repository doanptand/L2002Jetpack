package com.ddona.pokemon.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddona.pokemon.R;
import com.ddona.pokemon.adapter.PokemonAdapter;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

public class PokemonFavoriteFragment extends Fragment {
    private RecyclerView rvPokemon;
    private List<Pokemon> pokemons;
    private PokemonAdapter adapter;
    private PokemonViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        rvPokemon = view.findViewById(R.id.rv_pokemon);
        pokemons = new ArrayList<>();
        adapter = new PokemonAdapter(pokemons, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvPokemon.setLayoutManager(layoutManager);
        rvPokemon.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        viewModel.getFavoritePokemons().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> data) {
                pokemons.clear();
                pokemons.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
        setupSwipeItem();
        return view;
    }

    private void setupSwipeItem() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemons.get(position);
                viewModel.deleteOnePokemon(pokemon.getName());
                adapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvPokemon);
    }

}
