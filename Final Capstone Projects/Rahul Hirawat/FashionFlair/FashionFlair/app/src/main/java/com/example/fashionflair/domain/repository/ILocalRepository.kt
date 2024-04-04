package com.example.fashionflair.domain.repository

import android.net.Uri
import com.example.fashionflair.domain.model.ProductItemModel

interface ILocalRepository {
    fun getName(): String?
    fun getEmail(): String
    fun setName(name: String)
    fun setEmail(email: String)
    fun saveProductToCart(list: ArrayList<ProductItemModel>?)
    fun getCartList(): ArrayList<ProductItemModel>?
    fun setProfilePicture(uri: Uri)
    fun getProfilePicture(): Uri?
    fun clearData()
}