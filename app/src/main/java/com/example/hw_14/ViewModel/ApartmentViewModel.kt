package com.example.hw_14.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_14.Model.RetrofitObject
import com.example.hw_14.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ApartmentViewModel : ViewModel() {
    private val _likusflow = MutableStateFlow<ResponseState>(ResponseState.InProcess())
    val likusflow: StateFlow<ResponseState> get() = _likusflow

    fun infoGet() {
        viewModelScope.launch {
            val response = RetrofitObject.getProducts().infoGet()
            if (response.isSuccessful) {
                _likusflow.emit(ResponseState.Success(response.body()?.content ?: emptyList()))
            } else {
                _likusflow.emit(ResponseState.Error(response.errorBody().toString()))
            }
        }
    }
}