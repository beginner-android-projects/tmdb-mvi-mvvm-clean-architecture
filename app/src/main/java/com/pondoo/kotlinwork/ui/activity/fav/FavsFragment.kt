package com.pondoo.kotlinwork.ui.activity.fav

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pondoo.kotlinwork.R
import com.pondoo.kotlinwork.databinding.FavsFragmentBinding
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.ui.activity.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavsFragment : Fragment() {
    private lateinit var favAdapter: FavoritesAdapter
    private lateinit var binding: FavsFragmentBinding
    private val mViewModel  by viewModels<FavsViewModel>()
    lateinit var favTvTvShows:List<FavTvShowItem>
    var ct: Context?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavsFragmentBinding.inflate(layoutInflater,container,false)
        val view=binding.root
        ct=activity
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observe()

        setOnClicks()
    }
    fun init(){
        favAdapter= FavoritesAdapter(
            ct!!,
            mViewModel,this
        )
        binding.FavFragmentRvFavorites.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=favAdapter
        }
        favAdapter.setOnFavListener {
            mViewModel.deleteFromFavs(it)
        }
        favAdapter.setOnItemClickListener {
            var details = DetailsFragment()
            var args = Bundle()
            var id = it
            args.putString("id",id)
            details.arguments=args
            val manager: FragmentManager? = this.fragmentManager
            val transaction: FragmentTransaction = manager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.nothing, R.anim.left_to_right, R.anim.nothing)
            transaction.replace(R.id.nav_host, details)
            transaction.addToBackStack("fav")
            transaction.commit()
        }

    }
    private fun setOnClicks(){
        binding.FavFragmentBtnBack.setOnClickListener(View.OnClickListener {
            (ct as Activity).onBackPressed()
        })
    }
    fun observe(){
        mViewModel.getFavoriteShows()
        mViewModel.favShows.observe(viewLifecycleOwner, Observer {value ->
            value.let {
                favTvTvShows=it
                favAdapter.updateFavShows(favTvTvShows)
            }
        })
    }
}