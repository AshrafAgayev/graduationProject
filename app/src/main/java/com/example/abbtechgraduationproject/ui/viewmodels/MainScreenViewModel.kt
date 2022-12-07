package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(var repo:FoodsRepository) : ViewModel(){

     var foodsList =MutableLiveData<List<Foods>>()
    var foodsOnCart =MutableLiveData<List<FoodsOnCart>>()


    init {
        getAll()
        getAllFoodsFromCart()
    }

    fun getAll(){
        CoroutineScope(Dispatchers.Main).launch{
            foodsList.value = repo.getAll()
        }
    }

    fun getImage(imageName:String){
        CoroutineScope(Dispatchers.Main).launch {
            repo.getImage(imageName)
        }
    }

    fun getAllFoodsFromCart(){
        CoroutineScope(Dispatchers.Main).launch{
            repo.getAllFoodsFromCart()
        }
    }

}