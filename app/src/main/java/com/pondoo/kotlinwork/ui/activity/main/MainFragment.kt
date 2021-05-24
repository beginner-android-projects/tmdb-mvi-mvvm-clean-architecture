package com.pondoo.kotlinwork.ui.activity.main

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pondoo.kotlinwork.R
import com.pondoo.kotlinwork.databinding.MainFragmentBinding
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.domain.model.TvShows
import com.pondoo.kotlinwork.ui.activity.details.DetailsFragment
import com.pondoo.kotlinwork.ui.activity.fav.FavsFragment
import com.pondoo.kotlinwork.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val mViewModel  by viewModels<MainViewModel>()

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainAdapter: MainFragmentAdapter
    var favTvTvShows=ArrayList<FavTvShowItem>()
    var ct: Context?=null
    var list=ArrayList<TvShows>()
    var wait=false
    var search=""
    var i=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        ct = activity
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
    fun init(){
        setOnClickListeners()
        observeLiveDatas()

    }
    fun setOnClickListeners(){
        Utils.loading(binding.MainFragmentMainLayout,binding.MainFragmentLottie)
        mainAdapter= MainFragmentAdapter(ct!!)
        mainAdapter.setOnItemClickListener {item ->
            var details = DetailsFragment()
            var args = Bundle()
            var id = item
            args.putString("id",id)
            details.arguments=args
            val manager: FragmentManager? = this.fragmentManager
            val transaction: FragmentTransaction = manager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.nothing, R.anim.left_to_right, R.anim.nothing)
            transaction.addToBackStack("main")
            transaction.add(R.id.nav_host, details)
            transaction.hide(this)
            transaction.addToBackStack("main")
            transaction.commit()
        }
        mainAdapter.setOnFavListener { item,boolean->
            if(boolean){
                mViewModel.deleteFromFavs(item)
            }else{
                mViewModel.addInFavs(item)
            }
        }
        binding.MainFragmentRvBase.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=mainAdapter
        }
        binding.MainFragmentEtSearch.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(s.toString())
            }

        })
        binding.MainFragmentRvBase.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && wait==false&& search.equals("")) {
                    wait=true
                    //if The View triggered to bottom of view, We are getting new TV Show list from API.
                    i++
                    mViewModel.apiCall(i)
                }
            }
        })
        binding.MainFragmentBtnFavs.setOnClickListener(View.OnClickListener {
            val manager: FragmentManager? = this.fragmentManager
            val transaction: FragmentTransaction = manager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.right_to_left, R.anim.nothing, R.anim.left_to_right, R.anim.nothing)
            transaction.add(R.id.nav_host, FavsFragment())
            transaction.hide(this)
            transaction.addToBackStack("main")
            transaction.commit()
        })
    }
    fun observeLiveDatas(){
        mViewModel.apiCall(i)
        mViewModel.data.observe(viewLifecycleOwner, Observer {value ->
            value.let {
                list.addAll(it)
                wait = false
                if(i==1) Utils.loaded(binding.MainFragmentMainLayout, binding.MainFragmentLottie)
                mainAdapter.updateList(list)
                mViewModel.getFavoriteShows()
            }
        })
        mViewModel.favShows.observe(viewLifecycleOwner, Observer {value ->
            value.let {
                favTvTvShows.clear()
                favTvTvShows.addAll(it)
                Utils.loaded(binding.MainFragmentMainLayout, binding.MainFragmentLottie)
                mainAdapter.updateFavShows(favTvTvShows)

            }
        })
    }
    private fun search(s:String){
        search=s
        var temp=ArrayList<TvShows>()
        for(i in list) {
            if (i.name!!.toLowerCase().contains(s.toLowerCase())) {
                temp.add(i)
            }
        }
        if(s==""){
            mainAdapter!!.updateList(list)
        }else {
            mainAdapter!!.updateList(temp)
        }
    }
    override fun onResume() {
        super.onResume()
        mViewModel.getFavoriteShows()
    }
}