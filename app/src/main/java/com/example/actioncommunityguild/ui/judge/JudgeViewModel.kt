package com.example.actioncommunityguild.ui.judge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JudgeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "審査画面"
    }
    val text: LiveData<String> = _text
}