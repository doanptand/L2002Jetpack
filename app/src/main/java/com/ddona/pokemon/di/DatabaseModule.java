package com.ddona.pokemon.di;

import android.app.Application;

import androidx.room.Room;

import com.ddona.pokemon.db.PokemonDao;
import com.ddona.pokemon.db.PokemonDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {
    @Provides
    public static PokemonDatabase providePokeMonDB(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                PokemonDatabase.class, "pokemon")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()//FIXME this is only for test, remove on production
                .build();
    }

    @Provides
    public static PokemonDao providePokemonDao(PokemonDatabase database) {
        return database.getPokomonDao();
    }
}
