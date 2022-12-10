package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun launch(block: suspend CoroutineScope.()->Unit){
        viewModelScope.launch(Dispatchers.IO,CoroutineStart.DEFAULT,block)
    }
}