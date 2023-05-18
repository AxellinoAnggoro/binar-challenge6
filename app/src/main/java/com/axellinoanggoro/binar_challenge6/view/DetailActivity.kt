package com.axellinoanggoro.binar_challenge6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_challenge6.databinding.ActivityDetailBinding
import com.axellinoanggoro.binar_challenge6.model.DataPopularMovie
import com.axellinoanggoro.binar_challenge6.room.DataFavMovie
import com.axellinoanggoro.binar_challenge6.room.FavDatabase
import com.axellinoanggoro.binar_challenge6.view.adapter.MovieAdapter
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: ActivityDetailBinding
    var favDb: FavDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataPopularMovie>("data_detail")
        setViewData(data)

        favDb = FavDatabase.getInstance(this)

        binding.detailFab.setOnClickListener {
            addDataFav(data)
        }
    }

    private fun addDataFav(data : DataPopularMovie?){
        GlobalScope.async {
            val title = data?.title.toString()
            val date = data?.date.toString()
            val overview = data?.desc.toString()
            val imagepath = data?.image.toString()

            favDb?.favDao()?.insertData(DataFavMovie(0,title, date, overview, imagepath))
        }
        finish()
    }

    private fun setViewData(data: DataPopularMovie?) {
        val title = data?.title
        val date = data?.date
        val overview = data?.desc
        val imagepath = data?.image

        binding.detailTitle.text = title
        binding.detailDate.text = date
        binding.detailDsc.text = overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w780$imagepath").into(binding.detailImg)
    }

    override fun onItemClick(data: DataPopularMovie) {
        val move = Intent(this, DetailActivity::class.java)
        move.putExtra("data_detail", data)
        startActivity(move)
    }
}