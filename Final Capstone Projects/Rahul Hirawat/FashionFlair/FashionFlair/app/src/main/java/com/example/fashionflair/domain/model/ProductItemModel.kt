package com.example.fashionflair.domain.model

data class ProductItemModel(
    val productId: Long,
    val productName: String,
    val productPrice: Long,
    val productImage: String,
    val productStock: Long
)
