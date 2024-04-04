package com.example.fashionflair.data.repository

import android.net.Uri
import com.example.fashionflair.data.local.SharedPreferenceHelper
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.domain.repository.ILocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : ILocalRepository {
    override fun getName(): String? {
        return sharedPreferenceHelper.getName()
    }

    override fun getEmail(): String {
        return sharedPreferenceHelper.getEmail()
    }

    override fun setName(name: String) {
        sharedPreferenceHelper.setName(name)
    }

    override fun setEmail(email: String) {
        sharedPreferenceHelper.setEmail(email)
    }

    override fun saveProductToCart(list: ArrayList<ProductItemModel>?) {
        sharedPreferenceHelper.saveProductToCart(list)
    }

    override fun getCartList(): ArrayList<ProductItemModel>? {
        return sharedPreferenceHelper.getCartList()
    }

    override fun setProfilePicture(uri: Uri) {
        sharedPreferenceHelper.setProfilePicture(uri)
    }

    override fun getProfilePicture(): Uri? {
        return sharedPreferenceHelper.getProfilePicture()
    }

    override fun clearData() {
        sharedPreferenceHelper.clearData()
    }
}