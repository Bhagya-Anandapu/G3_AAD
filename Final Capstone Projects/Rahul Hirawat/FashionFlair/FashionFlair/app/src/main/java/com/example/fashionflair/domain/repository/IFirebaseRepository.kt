package com.example.fashionflair.domain.repository

import com.example.fashionflair.domain.ResponseHandler
import com.example.fashionflair.domain.model.ProductItemModel

interface IFirebaseRepository {
    suspend fun getDataFromDatabase(): ResponseHandler<List<ProductItemModel>>
}