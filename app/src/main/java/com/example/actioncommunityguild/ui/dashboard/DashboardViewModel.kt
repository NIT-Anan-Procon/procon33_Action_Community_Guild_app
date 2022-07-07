package com.example.actioncommunityguild.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ダッシュボード画面はココやで"
    }
    val text: LiveData<String> = _text
}