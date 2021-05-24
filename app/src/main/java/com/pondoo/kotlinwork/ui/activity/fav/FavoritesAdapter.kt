package com.pondoo.kotlinwork.ui.activity.fav

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pondoo.kotlinwork.R
import com.pondoo.kotlinwork.databinding.RecyclerviewHolderBinding
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.ui.activity.details.DetailsFragment
import com.pondoo.kotlinwork.utils.Config

class FavoritesAdapter (var context: Context, var mViewModel: FavsViewModel,var fragment: Fragment) :
        RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    var favList = ArrayList<FavTvShowItem>()
    private lateinit var onItemClick: (String) -> Unit
    fun setOnItemClickListener(coinOnItemClick: (String) -> Unit) {
        this.onItemClick = coinOnItemClick
    }
    private lateinit var onRemoveFromFavListener: (FavTvShowItem) -> Unit
    fun setOnFavListener(onRemoveFromFavListener: (FavTvShowItem) -> Unit) {
        this.onRemoveFromFavListener = onRemoveFromFavListener
    }
    class ViewHolder(val binding: RecyclerviewHolderBinding,
                     val itemClick: (String) -> Unit,
                     val onRemoveFromFav:(FavTvShowItem)->Unit) : RecyclerView.ViewHolder(binding.root){
        fun bind(favTvShow:FavTvShowItem){
            binding.RvShowsTvTitle.text=favTvShow.title
            binding.RvTvShowsArating.text=favTvShow.averageRating.toString()+"/10"
            var url = Config.IMAGE_URL + favTvShow.posterPath
            Glide.with(binding.RvShowsIv.context).load(url).into(binding.RvShowsIv)
            binding.RVShowAddInFav.setBackgroundResource(R.drawable.in_fav)
            binding.RVShowAddInFav.setOnClickListener {
                onRemoveFromFav(favTvShow)
            }
            itemView.setOnClickListener{
                itemClick(favTvShow.id.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerviewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                ,onItemClick
                ,onRemoveFromFavListener
        )
    }

    override fun getItemCount(): Int {
        return favList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favList.get(position))
    }
    fun updateFavShows(listFavTvShows:List<FavTvShowItem>){
        favList.clear()
        favList.addAll(listFavTvShows)
        notifyDataSetChanged()
    }

}
