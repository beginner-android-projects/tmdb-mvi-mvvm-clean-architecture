package com.pondoo.kotlinwork.ui.activity.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pondoo.kotlinwork.R
import com.pondoo.kotlinwork.databinding.RecyclerviewHolderBinding
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.domain.model.TvShows
import com.pondoo.kotlinwork.ui.activity.details.DetailsFragment
import com.pondoo.kotlinwork.utils.Config

class MainFragmentAdapter(var context: Context) :
    RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>() {
    var list=ArrayList<TvShows>()
    var favList=ArrayList<FavTvShowItem>()
    private lateinit var onItemClick: (String) -> Unit
    fun setOnItemClickListener(coinOnItemClick: (String) -> Unit) {
        this.onItemClick = coinOnItemClick
    }
    private lateinit var onFavListener: (FavTvShowItem,Boolean) -> Unit
    fun setOnFavListener(favListener: (FavTvShowItem,Boolean) -> Unit) {
        this.onFavListener = favListener
    }

    class ViewHolder(val binding:RecyclerviewHolderBinding,
                     val itemClick: (String) -> Unit,
                     val favListener:(FavTvShowItem,Boolean)->Unit):RecyclerView.ViewHolder(binding.root){
        fun bind(tvShow:TvShows,isInFav:Boolean){
            binding.RvShowsTvTitle.text=tvShow.name
            binding.RvTvShowsArating.text=tvShow.voteAverage.toString()+"/10"
            var url = Config.IMAGE_URL+tvShow.posterPath
            Glide.with(binding.RvShowsIv.context).load(url).into(binding.RvShowsIv)
            if(isInFav){
                binding.RVShowAddInFav.setBackgroundResource(R.drawable.in_fav)
            }else{
                binding.RVShowAddInFav.setBackgroundResource(R.drawable.not_in_fav)
            }
            itemView.setOnClickListener(View.OnClickListener {
                itemClick(tvShow.id.toString())
            })
            binding.RVShowAddInFav.setOnClickListener(View.OnClickListener {
                var item = FavTvShowItem(tvShow.id!!,tvShow.name,tvShow.posterPath,tvShow.voteAverage)
                if(isInFav){
                    favListener(item,true)
                }else{
                    favListener(item,false)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerviewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false),onItemClick,onFavListener
        )
    }

    override fun getItemCount(): Int {

       return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isInFav= controlInFav(list.get(position))
        holder.bind(list.get(position),isInFav)

    }
    fun controlInFav(item:TvShows):Boolean{
        for(i in favList){
                if(i.id==item.id){
                    return true
                }
        }
        return false
    }
    fun updateList(listShows : List<TvShows>){
        list.clear()
        list.addAll(listShows)
        notifyDataSetChanged()
    }
    fun updateFavShows(listFavTvShows:List<FavTvShowItem>){
        favList.clear()
        favList.addAll(listFavTvShows)
        notifyDataSetChanged()
    }

}