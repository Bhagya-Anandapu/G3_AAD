package com.example.fashionflair.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fashionflair.databinding.ProductCardItemBinding
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.presentation.fragment.OnProductClicked
import com.example.fashionflair.presentation.viewHolders.ProductViewHolder

class ProductListingAdapter(
    private val list: List<ProductItemModel>,
    private val listener: OnProductClicked
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}