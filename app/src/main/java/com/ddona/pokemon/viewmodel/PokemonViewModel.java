package com.ddona.pokemon.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.repository.PokemonRepository;
import com.ddona.pokemon.util.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends ViewModel {
    private PokemonRepository repository;

    private MutableLiveData<List<Pokemon>> mNetworkPokemons = new MutableLiveData<>();
    private LiveData<List<Pokemon>> mFavoritePokemons = null;

    public PokemonViewModel(Application application) {
        this.repository = new PokemonRepository(application);
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
        repository.getPokemons().enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    List<Pokemon> pokemons = response.body().getResults();
                    for (Pokemon pokemon : pokemons) {
                        String url = pokemon.getUrl();
                        String[] index = url.split("/");
                        String imageName = index[index.length - 1] + ".png";
                        pokemon.setUrl(Const.IMAGE_URL + imageName);
                    }
                    mNetworkPokemons.postValue(pokemons);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
        });
    }
}
