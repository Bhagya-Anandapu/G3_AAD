package com.example.fashionflair.presentation.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fashionflair.databinding.CartItemViewBinding
import com.example.fashionflair.domain.model.ProductItemModel

class CartViewHolder(
    private val binding: CartItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setData(product: ProductItemModel) {
        binding.productName.text = product.productName
        binding.productPrice.text = "₹ ${product.productPrice}"
        Glide.with(binding.root.context).load(product.productImage).into(binding.productImage)
    }
}