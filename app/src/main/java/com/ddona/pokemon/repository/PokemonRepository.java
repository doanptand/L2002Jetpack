package com.ddona.pokemon.repository;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ddona.pokemon.db.PokemonDao;
import com.ddona.pokemon.db.PokemonDatabase;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.network.PokemonModule;
import com.ddona.pokemon.network.PokemonService;

import java.util.List;

import retrofit2.Call;

public class PokemonRepository {
    private PokemonDao pokemonDao;
    private PokemonService pokemonService;

    public PokemonRepository(Application application) {
        this.pokemonDao = PokemonDatabase.getPokemonDatabase(application).getPokomonDao();
        this.pokemonService = PokemonModule.getInstance();
    }

    public Call<PokemonResponse> getPokemons() {
        return pokemonService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon) {
        pokemonDao.insertPokemon(pokemon);
    }

    public void deleteOnePokemon(String name) {
        pokemonDao.deleteOnePokemon(name);
    }

    public void deleteAllPokemon() {
        pokemonDao.deleteAllPokemons();
    }

    public LiveData<List<Pokemon>> getFavoritePokemons() {
        return pokemonDao.getAllPokemons();
    }
}
