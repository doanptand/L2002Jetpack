package com.ddona.pokemon.di_demo;

import java.util.Dictionary;

import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
//https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
public class Driver {

    @Inject
    public Driver() {

    }
    public String getName() {
        return "Doan";
    }
}
