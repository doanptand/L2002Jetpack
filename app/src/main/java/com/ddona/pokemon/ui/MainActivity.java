package com.ddona.pokemon.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ddona.pokemon.R;
import com.ddona.pokemon.db.PokemonDatabase;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.network.PokemonModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FIXME only for test remove in product
        //TODO get list and display it
//
//        PokemonModule.getInstance().getPokemons().enqueue(new Callback<PokemonResponse>() {
//            @Override
//            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
//                if (response.isSuccessful()) {
//                    Log.d("doanpt", "result:" + response.body().getCount());
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
