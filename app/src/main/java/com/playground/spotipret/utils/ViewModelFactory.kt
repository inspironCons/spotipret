package com.playground.spotipret.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.playground.spotipret.repository.PlaylistRepository
import com.playground.spotipret.ui.playlist.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(
    private val any:Any
):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(any as PlaylistRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object{
        @Volatile
        private var INSTANCE:ViewModelFactory? = null

        @JvmStatic
        fun getInstance(any: Any):ViewModelFactory{
            if(INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(any)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}