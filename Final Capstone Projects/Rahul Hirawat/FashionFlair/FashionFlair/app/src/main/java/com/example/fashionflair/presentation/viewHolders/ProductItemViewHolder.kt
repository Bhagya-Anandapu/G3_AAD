package com.example.fashionflair.presentation.viewHolders

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fashionflair.databinding.HomeProductListViewBinding
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.presentation.adapters.ProductListingAdapter
import com.example.fashionflair.presentation.fragment.OnProductClicked

class ProductItemViewHolder(
    private val binding: HomeProductListViewBinding,
    private val listener: OnProductClicked
) : RecyclerView.ViewHolder(binding.root) {

    fun setData(list: List<ProductItemModel>) {

        //Set the recycler view
        val productAdapter = ProductListingAdapter(list, listener)
        binding.productRv.adapter = productAdapter
        binding.productRv.layoutManager =
            GridLayoutManager(binding.root.context, 2, RecyclerView.VERTICAL, false)
        productAdapter.notifyDataSetChanged()
    }
}