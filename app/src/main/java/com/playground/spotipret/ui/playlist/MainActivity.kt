package com.playground.spotipret.ui.playlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.playground.spotipret.databinding.ActivityMainBinding
import com.playground.spotipret.repository.PlaylistRepository
import com.playground.spotipret.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    private val repository = PlaylistRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel = obtainViewModel(repository)
        val actionbar = supportActionBar
        actionbar?.title = "Playlist"

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

    private fun obtainViewModel(repository: PlaylistRepository): MainViewModel {
        val factory = ViewModelFactory.getInstance(repository)
        return ViewModelProvider(this,factory)[MainViewModel::class.java]
    }

}