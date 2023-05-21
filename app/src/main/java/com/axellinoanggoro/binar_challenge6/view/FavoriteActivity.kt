package com.axellinoanggoro.binar_challenge6.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_challenge6.databinding.ActivityFavoriteBinding
import com.axellinoanggoro.binar_challenge6.model.DataPopularMovie
import com.axellinoanggoro.binar_challenge6.room.FavDatabase
import com.axellinoanggoro.binar_challenge6.view.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), FavoriteAdapter.OnItemClickListener {
    private lateinit var binding: ActivityFavoriteBinding
    private var favDb: FavDatabase? = null
    //    lateinit var favVm : FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favDb = FavDatabase.getInstance(this)

        getDataFav()

        binding.favHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

//        favAdapter = FavoriteAdapter(ArrayList())
//        binding.favRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.favRv.adapter = favAdapter
//
//        favVm = ViewModelProvider(this).get(FavViewModel::class.java)
//        favVm.getLiveFav().observe(this, Observer {
//            favAdapter.setData(it as ArrayList<DataFavMovie>)
//        })
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getDataFav() {
        binding.favRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listdata = favDb?.favDao()!!.getDataNote()
            runOnUiThread {
                listdata.let {
                    val adapt = FavoriteAdapter(it, this@FavoriteActivity)
                    binding.favRv.adapter = adapt
                }
            }
        }
    }

    override fun onItemClick(data: DataPopularMovie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data_detail", data)
        startActivity(intent)
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