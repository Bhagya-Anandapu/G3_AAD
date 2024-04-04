package com.example.fashionflair.domain

sealed class ResponseHandler<T> {
    data class Success<T>(val data: T) : ResponseHandler<T>()
    data class Failure(val errorMessage: String) : ResponseHandler<String>()
    data object Loading : ResponseHandler<Nothing>()
}