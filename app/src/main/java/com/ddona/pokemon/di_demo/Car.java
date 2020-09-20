package com.ddona.pokemon.di_demo;

import android.util.Log;

import javax.inject.Inject;

public class Car {

    Driver driver;

    @Inject
    //this is constructor injection... parameter will be inject by hilt
    public Car(Driver driver) {
        this.driver = driver;
    }

    public void drive() {
        Log.d("doanpt", "Car is running now and drive by "
                + driver.getName());
    }
}
