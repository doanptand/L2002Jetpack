package com.ddona.pokemon.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ddona.pokemon.R;
import com.ddona.pokemon.db.PokemonDatabase;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.model.PokemonResponse;
import com.ddona.pokemon.network.PokemonModule;
import com.ddona.pokemon.viewmodel.PokemonViewModel;
import com.ddona.pokemon.viewmodel.PokemonViewModelFactory;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this, new PokemonViewModelFactory(getApplication()))
                .get(PokemonViewModel.class);
        viewModel.fetchPokemonFromNetWork();
        viewModel.getNetworkPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                Log.d("doanpt","data changed with size is:" + pokemons.size());
            }
        });
//        testOnly();
    }

    private void testOnly() {
        //TODO read to understand about ViewModel
//        TextView tvScore = findViewById(R.id.score);
//        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
//
//        viewModel.getScore().observe(this, number -> {
//            tvScore.setText(number + " ");
//        });
//
//        Button btnSet = findViewById(R.id.btn_set);
//        btnSet.setOnClickListener(v -> viewModel.setScore(new Random().nextInt()));
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
