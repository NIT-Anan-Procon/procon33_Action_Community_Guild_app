package com.example.actioncommunityguild.ui.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RequestViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "依頼画面"
    }
    val text: LiveData<String> = _text
}