package com.example.actioncommunityguild.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiveViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "受注画面"
    }
    val text: LiveData<String> = _text
}