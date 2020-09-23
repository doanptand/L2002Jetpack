package com.ddona.pokemon.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.ddona.pokemon.R;
import com.ddona.pokemon.adapter.PokemonPagerAdapter;
import com.ddona.pokemon.di_demo.Car;
import com.ddona.pokemon.di_demo.StudentDAO;
import com.ddona.pokemon.model.Pokemon;
import com.ddona.pokemon.viewmodel.PokemonViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import javax.inject.Inject;
import com.ddona.pokemon.databinding.ActivityMainBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    //    private PokemonViewModel viewModel;
    @Inject
    Car car;
    @Inject
    StudentDAO studentDAO;
    private PokemonPagerAdapter adapter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new PokemonPagerAdapter(getSupportFragmentManager());
        binding.vpPokemon.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.vpPokemon);


//        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
//        viewModel.fetchPokemonFromNetWork();
//        viewModel.getNetworkPokemons().observe(this, new Observer<List<Pokemon>>() {
//            @Override
//            public void onChanged(List<Pokemon> pokemons) {
//                Log.d("doanpt", "data changed with size is:" + pokemons.size());
//            }
//        });
//        testOnly();
        testDI();
    }

    private void testDI() {
        car.drive();
        studentDAO.insert();
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
