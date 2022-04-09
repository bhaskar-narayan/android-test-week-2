package com.bhaskar.androidtest2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.ActivityMainBinding
import com.bhaskar.androidtest2.objects.FragmentPosition.position
import com.bhaskar.androidtest2.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]

        with (binding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.mainBackButton -> onBackPressed()
                    R.id.mainCloseButton -> this@MainActivity.finish()
                }
            }
            position.observe(this@MainActivity) {
                mainProgressBar.progress = it
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}