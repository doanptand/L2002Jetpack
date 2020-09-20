package com.ddona.pokemon.di_demo;

import android.util.Log;

import javax.inject.Inject;

public class StudentDAOImpl implements StudentDAO {

    @Inject
    public StudentDAOImpl() {

    }
    @Override
    public void insert() {
        Log.d("doanpt", "Insert a student");
    }
}
