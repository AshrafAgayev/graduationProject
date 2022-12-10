package com.example.abbtechgraduationproject.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class BaseRepository {
    fun <T> sendRequest(call: suspend (() -> T)):Flow<T> = flow {
        emit(call.invoke())
    }.flowOn(Dispatchers.IO)
}