package com.pondoo.kotlinwork.ui.activity.details

import android.app.Activity
import android.content.Context

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.pondoo.kotlinwork.R

import com.pondoo.kotlinwork.databinding.DetailsFragmentBinding
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.domain.model.TvShowDetail

import com.pondoo.kotlinwork.utils.Config
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    var ct: Context?=null
    private val mViewModel by viewModels<DetailsViewModel>()
    private lateinit var favTvList:List<FavTvShowItem>
    lateinit var detailTv : TvShowDetail
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(layoutInflater,container,false)
        val view=binding.root
        ct=activity
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
    private fun init(){
        onClickListeners()
        observeLiveDatas()

    }
    fun onClickListeners(){
        binding.DetailsFragmentBtnFav.setOnClickListener(View.OnClickListener {
            var isInFav=controlInFav(favTvList)
            var favShow=FavTvShowItem(detailTv.id!!,detailTv.name,detailTv.posterPath,detailTv.voteAverage)
            if(isInFav){
                mViewModel.deleteFromFavs(favShow)
                binding.DetailsFragmentBtnFav.setBackgroundResource(R.drawable.not_in_fav)
            }else{
                mViewModel.addInFavs(favShow)
                binding.DetailsFragmentBtnFav.setBackgroundResource(R.drawable.in_fav)
            }
        })
        binding.DetailsFragmentBtnBack.setOnClickListener(View.OnClickListener {
            (ct as Activity).onBackPressed()
        })
    }
    fun controlInFav(favTvList:List<FavTvShowItem>):Boolean{
        for(i in favTvList){
            if(i.id==detailTv.id){
                binding.DetailsFragmentBtnFav.setBackgroundResource(R.drawable.in_fav)
                return true
            }
        }
        binding.DetailsFragmentBtnFav.setBackgroundResource(R.drawable.not_in_fav)
        return false
    }
    fun observeLiveDatas(){
        var id= this.requireArguments().getString("id","")!!
        mViewModel.apiCall(id)
        mViewModel.detail.observe(viewLifecycleOwner, Observer {
            detailTv= it
            mViewModel.getFavoriteShows()
        })
        mViewModel.favShows.observe(viewLifecycleOwner, Observer {
            favTvList= it
            controlInFav(favTvList)
            drawRes()
        })
    }
    fun drawRes(){
        binding.DetailsFragmentTVTitleofMovie.text=detailTv.name
        binding.DetailsFragmentTvDetails.text=detailTv.overview
        binding.DetailsFragmentTvTotalEpisodes.text=detailTv.numberOfEpisodes.toString()
        binding.DetailsFragmentTvSeasonCounts.text=detailTv.numberOfSeasons.toString()
        binding.DetailsFragmentTvOverallRa.text=detailTv.voteAverage.toString()+"/10"
        var url= Config.IMAGE_URL+detailTv.posterPath
        Glide.with(this).load(url).into(binding.DetailsFragmentIVimage)
        var rating=detailTv.voteAverage!!/2
        binding.DetailsFragmentTvGenres.text=detailTv.genres
        binding.DetailsFragmentRatingBar.max=5
        binding.DetailsFragmentRatingBar.stepSize=0.01f
        binding.DetailsFragmentRatingBar.rating=rating.toFloat()
    }
}