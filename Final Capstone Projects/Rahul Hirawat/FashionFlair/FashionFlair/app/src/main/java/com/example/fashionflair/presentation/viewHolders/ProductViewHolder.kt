package com.example.fashionflair.presentation.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fashionflair.databinding.ProductCardItemBinding
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.presentation.fragment.OnProductClicked

class ProductViewHolder(
    private val binding: ProductCardItemBinding,
    val listener: OnProductClicked
) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(product: ProductItemModel) {
        binding.productName.text = product.productName
        binding.productPrice.text = "₹ ${product.productPrice}"
        Glide.with(binding.root.context).load(product.productImage).into(binding.productImage)

        binding.root.setOnClickListener {
            listener.onProductClicked(product)
        }
    }
}