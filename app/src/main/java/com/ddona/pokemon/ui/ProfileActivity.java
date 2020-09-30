package com.ddona.pokemon.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.ddona.pokemon.R;
import com.ddona.pokemon.databinding.ActivityProfileBinding;
import com.ddona.pokemon.model.User;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        User user = new User("doanptand", "doanpt93cntt@gmail.com", "");
        binding.setUser(user);
        binding.getUser().setUsername("Doanpt ");

    }
}