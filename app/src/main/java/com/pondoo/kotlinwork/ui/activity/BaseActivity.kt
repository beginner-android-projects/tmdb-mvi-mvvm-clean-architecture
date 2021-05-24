package com.pondoo.kotlinwork.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pondoo.kotlinwork.R
import com.pondoo.kotlinwork.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        this.supportActionBar!!.hide()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else super.onBackPressed()
    }
}