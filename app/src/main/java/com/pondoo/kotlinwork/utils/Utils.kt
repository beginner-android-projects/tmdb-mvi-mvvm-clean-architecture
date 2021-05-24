package com.pondoo.kotlinwork.utils

import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView

object Utils {
    fun loading(mainLayout: ViewGroup, lottie: ViewGroup){
        lottie.visibility= View.VISIBLE
        mainLayout.visibility= View.GONE
    }
    fun loaded(mainLayout: ViewGroup, lottie: ViewGroup){
        lottie.visibility= View.GONE
        mainLayout.visibility= View.VISIBLE
    }

}