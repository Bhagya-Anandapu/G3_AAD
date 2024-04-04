package com.example.fashionflair.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fashionflair.databinding.CartItemViewBinding
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.presentation.viewHolders.CartViewHolder

class CartAdapter(
    private val list: ArrayList<ProductItemModel>
) : RecyclerView.Adapter<CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.setData(list[position])
    }
}