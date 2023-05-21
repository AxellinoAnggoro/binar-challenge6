package com.axellinoanggoro.binar_challenge6.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_challenge6.databinding.ItemFavoriteBinding
import com.axellinoanggoro.binar_challenge6.model.DataPopularMovie
import com.axellinoanggoro.binar_challenge6.room.DataFavMovie
import com.axellinoanggoro.binar_challenge6.room.FavDatabase
import com.axellinoanggoro.binar_challenge6.view.FavoriteActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FavoriteAdapter(private var listFav: List<DataFavMovie>?, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var dbFav : FavDatabase? = null

    interface OnItemClickListener {
        fun onItemClick(data: DataPopularMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleFav.text = listFav!![position].title
        holder.binding.dateFav.text = listFav!![position].date
        holder.binding.dscFav.text = listFav!![position].desc
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFav!![position].image}")
            .into(holder.binding.imgFav)

        holder.itemView.setOnClickListener {
            val image = listFav!![position].image
            val title = listFav!![position].title
            val date = listFav!![position].date
            val desc = listFav!![position].desc
            val detailFav = DataPopularMovie(image, title, date, desc)

            listener.onItemClick(detailFav)
        }

        holder.binding.deleteFav.setOnClickListener {
            dbFav = FavDatabase.getInstance(it.context)
            GlobalScope.async {
                dbFav?.favDao()?.deleteFav(listFav!![position])
                (holder.itemView.context as FavoriteActivity).runOnUiThread{
                    (holder.itemView.context as FavoriteActivity).getDataFav()
                }

            }
        }

    }

    override fun getItemCount(): Int {
        return listFav!!.size
    }

    inner class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}