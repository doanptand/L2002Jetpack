package com.ddona.pokemon.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ddona.pokemon.ui.fragments.PokemonFavoriteFragment;
import com.ddona.pokemon.ui.fragments.PokemonListFragment;

public class PokemonPagerAdapter extends FragmentPagerAdapter {
    public PokemonPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PokemonListFragment();
            case 1:
                return new PokemonFavoriteFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    final String[] titles = new String[]{"Pokemon", "Favorite"};

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
