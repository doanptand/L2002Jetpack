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
        User user = new User("doanptand",
                "doanpt93cntt@gmail.com",
                "https://image.thanhnien.vn/768/uploaded/ngocthanh/2020_07_13/ngoctrinhmuonsinhcon1_swej.jpg");
        binding.setUser(user);

    }
}