package com.example.actioncommunityguild.ui.newEdit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "新規画面はココやで"
    }
    val text: LiveData<String> = _text
}