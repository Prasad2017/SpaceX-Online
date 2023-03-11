package com.example.spacex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacex.adapter.RocketDetailAdapter
import com.example.spacex.databinding.ActivityMainBinding
import com.example.spacex.viewmodel.RocketListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RocketListViewModel
    lateinit var rocketAdapter: RocketDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        viewModel = ViewModelProvider(this)[RocketListViewModel::class.java]

        viewModel.getRocketList()
        viewModel.observeRocketListData().observe(this, Observer { movieList ->
            rocketAdapter.setRocketList(movieList)
        })

    }

    private fun prepareRecyclerView() {

        rocketAdapter = RocketDetailAdapter()
        binding.rvRockets.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = rocketAdapter
            hasFixedSize() // Improve performance (use only with fixed size items)
        }
    }


}