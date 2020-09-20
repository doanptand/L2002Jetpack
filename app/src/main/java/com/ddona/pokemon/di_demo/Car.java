package com.ddona.pokemon.di_demo;

import android.util.Log;

import javax.inject.Inject;

public class Car {

    @Inject
    public Car() {

    }

    public void drive() {
        Log.d("doanpt", "Car is running now");
    }
}
