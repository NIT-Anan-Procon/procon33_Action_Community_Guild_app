package com.example.actioncommunityguild.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "動画視聴画面"
    }
    val text: LiveData<String> = _text
}