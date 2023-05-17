package com.axellinoanggoro.binar_challenge6.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_challenge6.databinding.ItemFavoriteBinding
import com.axellinoanggoro.binar_challenge6.model.DataFavMovie
import com.axellinoanggoro.binar_challenge6.model.DataPopularMovie
import com.axellinoanggoro.binar_challenge6.model.ResultPopularMovie
import com.bumptech.glide.Glide

class FavoriteAdapter(private var listFav : List<ResultPopularMovie>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.binding.titleFav.text = listFav[position].originalTitle
        holder.binding.dateFav.text = listFav[position].releaseDate
        holder.binding.dscFav.text = listFav[position].overview
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFav[position].posterPath}")
            .into(holder.binding.imgFav)
    }

    override fun getItemCount(): Int {
        return listFav.size
    }

    class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}