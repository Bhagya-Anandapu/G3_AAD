package com.example.fashionflair.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.domain.repository.ILocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: ILocalRepository
) : ViewModel() {

    fun getCartList(): ArrayList<ProductItemModel>? {
        return repo.getCartList()
    }

    fun setCartList(list: ArrayList<ProductItemModel>?) {
        repo.saveProductToCart(list = list)
    }
}