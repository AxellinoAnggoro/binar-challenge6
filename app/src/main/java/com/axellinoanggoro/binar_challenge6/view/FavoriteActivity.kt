package com.axellinoanggoro.binar_challenge6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_challenge6.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}