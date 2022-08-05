package com.example.hw_14.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_14.Model.LikusData
import com.example.hw_14.Model.RetrofitObject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


class ApartmentViewModel : ViewModel() {
    private val _likusFlow = MutableSharedFlow<LikusData>()
    val likusFlow: SharedFlow<LikusData> get() = _likusFlow

    fun infoGet() {
        viewModelScope.launch {
            val response = RetrofitObject.getProducts().infoGet()
            val body: LikusData? = response.body()
            if (response.isSuccessful && body!=null){
                _likusFlow.emit(body)
            }
        }
    }
}