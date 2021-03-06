package com.ddona.pokemon.viewmodel;

import android.app.Application;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.repository.PokemonRepository;
import com.ddona.pokemon.util.Const;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PokemonViewModel extends ViewModel {
    PokemonRepository repository;

    private MutableLiveData<List<Pokemon>> mNetworkPokemons = new MutableLiveData<>();
    private LiveData<List<Pokemon>> mFavoritePokemons = null;

    @ViewModelInject
    public PokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
        this.mFavoritePokemons = repository.getFavoritePokemons();
    }

    public MutableLiveData<List<Pokemon>> getNetworkPokemons() {
        return mNetworkPokemons;
    }

    public LiveData<List<Pokemon>> getFavoritePokemons() {
        return mFavoritePokemons;
    }

    public void insertPokemon(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }

    public void deleteOnePokemon(String name) {
        repository.deleteOnePokemon(name);
    }

    public void deleteAllPokemon() {
        repository.deleteAllPokemon();
    }

    public void fetchPokemonFromNetWork() {
//        http://reactivex.io/documentation/operators.html
        //TODO use RXJava to fetch data
        repository.getPokemons()
                .map(pokemonResponse -> {
                    List<Pokemon> pokemons = pokemonResponse.getResults();
                    for (Pokemon pokemon : pokemons) {
                        String url = pokemon.getUrl();
                        String[] index = url.split("/");
                        String imageName = index[index.length - 1] + ".png";
                        pokemon.setUrl(Const.IMAGE_URL + imageName);
                    }
                    return pokemons;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> mNetworkPokemons.postValue(result)
                );

        //FIXME remove later
//        repository.getPokemons().enqueue(new Callback<PokemonResponse>() {
//            @Override
//            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
//                if (response.isSuccessful()) {
//                    List<Pokemon> pokemons = response.body().getResults();
//                    for (Pokemon pokemon : pokemons) {
//                        String url = pokemon.getUrl();
//                        String[] index = url.split("/");
//                        String imageName = index[index.length - 1] + ".png";
//                        pokemon.setUrl(Const.IMAGE_URL + imageName);
//                    }
//                    mNetworkPokemons.postValue(pokemons);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PokemonResponse> call, Throwable t) {
//
//            }
//        });
    }
}
