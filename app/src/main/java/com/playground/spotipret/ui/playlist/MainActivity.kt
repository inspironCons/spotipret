package com.playground.spotipret.ui.playlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.playground.spotipret.databinding.ActivityMainBinding
import com.playground.spotipret.utils.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel = obtainViewModel()
        val actionbar = supportActionBar
        actionbar?.title = "Playlist"
        //call api
        viewModel.requestPlaylist()

        viewLoader()
        generatePlaylist()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun generatePlaylist(){
        val adapter = PlaylistAdapter()
        binding?.rvPlaylist?.layoutManager = LinearLayoutManager(this)
        viewModel.getPlaylist().observe(this,{ playlist->
            adapter.setItems(playlist.getOrNull()?: emptyList())
        })
        binding?.rvPlaylist?.adapter = adapter
    }

    private fun viewLoader(){
        viewModel.spinnerShow().observe(this,{ load ->
            if(load){
                binding?.pgLoader?.visibility = View.VISIBLE
            }else{
                binding?.pgLoader?.visibility = View.GONE
            }
        })
    }

    private fun obtainViewModel(): MainViewModel {
        return ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }

}