package com.example.hw_14

import com.example.hw_14.Model.LikusData

sealed class ResponseState (val isLoading: Boolean) {
    class Success(val resultList: List<LikusData.Content>, isLoading: Boolean = false): ResponseState(isLoading)
    class Error(val errorBody: String, isLoading: Boolean = false): ResponseState(isLoading)
    class InProcess(isLoading: Boolean = true): ResponseState(isLoading)
}
