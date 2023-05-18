package com.axellinoanggoro.binar_challenge6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_challenge6.databinding.ActivityFavoriteBinding
import com.axellinoanggoro.binar_challenge6.room.DataFavMovie
import com.axellinoanggoro.binar_challenge6.room.FavDatabase
import com.axellinoanggoro.binar_challenge6.view.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    var favDb : FavDatabase? = null
    lateinit var favAdapter : FavoriteAdapter
//    lateinit var favVm : FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favDb = FavDatabase.getInstance(this)

        getDataFav()

//        favAdapter = FavoriteAdapter(ArrayList())
//        binding.favRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.favRv.adapter = favAdapter
//
//        favVm = ViewModelProvider(this).get(FavViewModel::class.java)
//        favVm.getLiveFav().observe(this, Observer {
//            favAdapter.setData(it as ArrayList<DataFavMovie>)
//        })
    }

    private fun getDataFav() {
        binding.favRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listdata = favDb?.favDao()!!.getDataNote()
            runOnUiThread {
                listdata.let {
                    val adapt = FavoriteAdapter(it)
                    binding.favRv.adapter = adapt
                }
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        GlobalScope.launch {
//            var data = favDb?.favDao()?.getDataFav()
//            runOnUiThread {
//                favAdapter = FavoriteAdapter(data!!)
//                binding.favRv.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                binding.favRv.adapter = favAdapter
//            }
//        }
//    }

}