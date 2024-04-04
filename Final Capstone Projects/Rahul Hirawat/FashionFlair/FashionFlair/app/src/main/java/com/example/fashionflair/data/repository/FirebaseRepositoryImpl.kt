package com.example.fashionflair.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.example.fashionflair.data.FirebaseConstants
import com.example.fashionflair.data.mappers.toProductItemModel
import com.example.fashionflair.data.models.ProductItemDataModel
import com.example.fashionflair.domain.ResponseHandler
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.domain.repository.IFirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val db: FirebaseFirestore
) : IFirebaseRepository {

    override suspend fun getDataFromDatabase(): ResponseHandler<List<ProductItemModel>> {
        val list = arrayListOf<ProductItemDataModel>()
        withContext(Dispatchers.IO) {
            ResponseHandler.Loading
            db.collection(FirebaseConstants.COLLECTION_NAME).get()
                .addOnSuccessListener {
                    for (document in it.documents) {
                        val productName = document.get(FirebaseConstants.PRODUCT_NAME) as String
                        val productId = document.get(FirebaseConstants.PRODUCT_ID) as Long
                        val productImage = document.get(FirebaseConstants.PRODUCT_URL) as String
                        val productStock = document.get(FirebaseConstants.PRODUCT_STOCK) as Long
                        val productPrice = document.get(FirebaseConstants.PRODUCT_PRICE) as Long

                        val data = ProductItemDataModel(
                            productId,
                            productName,
                            productPrice,
                            productImage,
                            productStock
                        )

                        list.add(data)
                    }
                }
                .addOnFailureListener {
                    ResponseHandler.Failure(it.localizedMessage)
                }
        }
        return ResponseHandler.Success(list.map { it.toProductItemModel() })
    }
}