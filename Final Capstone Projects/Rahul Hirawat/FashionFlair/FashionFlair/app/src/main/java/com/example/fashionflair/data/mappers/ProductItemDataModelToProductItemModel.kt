package com.example.fashionflair.data.mappers

import com.example.fashionflair.data.models.ProductItemDataModel
import com.example.fashionflair.domain.model.ProductItemModel

fun ProductItemDataModel.toProductItemModel(): ProductItemModel {
    return ProductItemModel(
        this.productId,
        this.productName,
        this.productPrice,
        this.productImage,
        this.productStock
    )
}