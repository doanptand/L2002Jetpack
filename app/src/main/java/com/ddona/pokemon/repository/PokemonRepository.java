package com.ddona.pokemon.repository;


import androidx.lifecycle.LiveData;

import com.ddona.pokemon.db.PokemonDao;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.network.PokemonService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokemonRepository {
    private PokemonDao pokemonDao;
    private PokemonService pokemonService;

    @Inject
    public PokemonRepository(PokemonDao pokemonDao, PokemonService pokemonService) {
        this.pokemonDao = pokemonDao;
        this.pokemonService = pokemonService;
    }

    public Observable<PokemonResponse> getPokemons() {
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
