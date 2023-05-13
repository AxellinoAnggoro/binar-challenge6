package com.axellinoanggoro.binar_challenge6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_challenge6.databinding.ActivityHomeBinding
import com.axellinoanggoro.binar_challenge6.model.DataPopularMovie
import com.axellinoanggoro.binar_challenge6.viewmodel.MovieViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = this.getSharedPreferences("data_reg", Context.MODE_PRIVATE)

        binding.homeProfile.setOnClickListener {
            var save = pref.edit()
            save.putString("username", "")
            save.apply()
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        val showUsername = pref.getString("username", "username")
        binding.homeTv.text = "Welcome $showUsername"

        val viewModelMovie = ViewModelProvider(this).get(MovieViewmodel::class.java)
        viewModelMovie.callTmdb()
        viewModelMovie.liveDataMovie.observe(this, Observer {
            if (it != null) {
                binding.homeRv.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.homeRv.adapter = MovieAdapter(it, this@HomeActivity)
            }
        })
    }
    override fun onItemClick(data: DataPopularMovie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data_detail", data)
        startActivity(intent)
    }
}