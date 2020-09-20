package com.ddona.pokemon.di;

import com.ddona.pokemon.network.PokemonService;
import com.ddona.pokemon.util.Const;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    public static GsonConverterFactory provideGSONConvertFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public static Retrofit provideRetrofit(GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    public static PokemonService providePokemonService(Retrofit retrofit) {
        return retrofit.create(PokemonService.class);
    }
}
