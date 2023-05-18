package com.axellinoanggoro.binar_challenge6.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_challenge6.databinding.ItemFavoriteBinding
import com.axellinoanggoro.binar_challenge6.model.ResultPopularMovie
import com.axellinoanggoro.binar_challenge6.room.DataFavMovie
import com.bumptech.glide.Glide

class FavoriteAdapter(private var listFav : List<DataFavMovie>?) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.binding.titleFav.text = listFav!![position].title
        holder.binding.dateFav.text = listFav!![position].date
        holder.binding.dscFav.text = listFav!![position].desc
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFav!![position].image}")
            .into(holder.binding.imgFav)

    }

    override fun getItemCount(): Int {
        return listFav!!.size
    }

    fun setData(list: ArrayList<DataFavMovie>) {
        this.listFav = list
    }

    class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}