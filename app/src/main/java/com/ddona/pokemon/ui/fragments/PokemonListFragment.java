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
import com.ddona.pokemon.databinding.FragmentPokemonBinding;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PokemonListFragment extends Fragment {
    private List<Pokemon> pokemons;
    private PokemonAdapter adapter;
    private PokemonViewModel viewModel;
    private FragmentPokemonBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPokemonBinding.inflate(inflater, container, false);

        pokemons = new ArrayList<>();
        adapter = new PokemonAdapter(pokemons, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.rvPokemon.setLayoutManager(layoutManager);
        binding.rvPokemon.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        viewModel.fetchPokemonFromNetWork();
        viewModel.getNetworkPokemons().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> data) {
                adapter.setData(data);
            }
        });

        setupSwipeItem();
        return binding.getRoot();
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
                viewModel.insertPokemon(pokemon);
                adapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.rvPokemon);
    }
}
