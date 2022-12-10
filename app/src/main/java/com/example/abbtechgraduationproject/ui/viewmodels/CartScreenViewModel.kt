package com.example.abbtechgraduationproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.network.FoodRepo
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import com.example.abbtechgraduationproject.utils.toSafeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(var repo: FoodsRepository, val mRepo: FoodRepo) :
    BaseViewModel() {

    private val _foodsOnCart = MutableLiveData<List<FoodsOnCart>>()
    val foodsOnCart: LiveData<List<FoodsOnCart>> = _foodsOnCart

//    private val _food = MutableStateFlow(listOf(FoodsOnCart()))
//    val food: StateFlow<List<FoodsOnCart>> = _food

    var progress: Boolean = false
    var status = ""


    init {

        getFromCart(USERNAME)
    }

    fun getFromCart(userName: String) {

        CoroutineScope(Dispatchers.Main).launch {

            try {
                _foodsOnCart.value = repo.getFromCart(userName)
            }catch (e:Exception){
                _foodsOnCart.value = listOf()
            }
        }
    }


//    fun getCart(userName: String) = launch {
//        try {
//
//            mRepo.getFromCart(userName)
//                .onStart {
//                    progress = true
//                }
//                .catch { e ->
//                    status = e.localizedMessage
//                }
//                .onCompletion {
//                    progress = false
//                }
//                .collect {
//                    _food.emit(it.foodsOnCart.toSafeList())
//                }
//        } catch (e: Exception) {
//            _food.emit(emptyList())
//            Log.e("EXCEPTION", "$e")
//        }
//    }


    fun deleteFromCart(id: Int?, userName: String?) = launch {
        repo.deleteFromCart(id, userName)
    }
}