package com.ddona.pokemon.di_demo;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class StudentModule {

    @Binds
    public abstract StudentDAO bindStudentDAO(StudentDAOImpl studentDAO);
}
