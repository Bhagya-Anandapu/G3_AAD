package com.example.fashionflair.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fashionflair.databinding.FragmentHomeBinding
import com.example.fashionflair.domain.model.HomeSectionModel
import com.example.fashionflair.domain.model.HomeSectionType
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.presentation.adapters.HomeParentAdapter
import com.example.fashionflair.presentation.dialog.ProductDetailsDialog
import com.example.fashionflair.presentation.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnProductClicked {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameTv.text = viewModel.getName() ?: "Guest"

        val childProductList = arrayListOf(
            ProductItemModel(
                1L,
                "Fit Jeans",
                549L,
                "https://t3.ftcdn.net/jpg/04/83/25/50/360_F_483255019_m1r1ujM8EOkr8PamCHF85tQ0rHG3Fiqz.jpg",
                4L
            ),
            ProductItemModel(
                2L,
                "Regular Fit Shirt",
                289L,
                "https://m.media-amazon.com/images/I/71OKaQZbj5L._SX679_.jpg",
                99L
            ),
            ProductItemModel(
                3L,
                "Straight Kurta Shirt",
                649L,
                "https://m.media-amazon.com/images/I/61Zjr852N3L._SY879_.jpg",
                40L
            ),
            ProductItemModel(
                4L,
                "Stripe Men Solid Single Breasted Blazer Black",
                1299L,
                "https://m.media-amazon.com/images/I/51q+DmlE0YL._SX679_.jpg",
                50L
            ),
            ProductItemModel(
                5L,
                "Men's Slim Fit Formal Trousers",
                872L,
                "https://m.media-amazon.com/images/I/61nYE7h2N9L._SY879_.jpg",
                9L
            ),
            ProductItemModel(
                6L,
                "Mens Cargo Pants",
                600L,
                "https://m.media-amazon.com/images/I/61xk9aNZWkL._SY879_.jpg",
                90L
            ),
        )
        val parentList = arrayListOf(
            HomeSectionModel(
                bannerText = "Offer of 20% discount!",
                sectionType = HomeSectionType.OFFER_BANNER
            ),
            HomeSectionModel(
                productList = childProductList,
                sectionType = HomeSectionType.PRODUCT_LIST
            ),
            HomeSectionModel(sectionType = HomeSectionType.FOOTER)
        )

        val parentAdapter = HomeParentAdapter(parentList, listener = this)

        binding.parentRv.adapter = parentAdapter
        binding.parentRv.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
    }


    private fun openProductDetailsDialog(
        id: Long,
        img: String,
        price: Long,
        name: String,
        stock: Long
    ) {
        if (childFragmentManager.findFragmentByTag(ProductDetailsDialog.TAG) == null) {
            val bundle = Bundle()
            bundle.putString(
                ProductDetailsDialog.IMAGE_URL,
                img
            )
            bundle.putString(ProductDetailsDialog.PRODUCT_NAME, name)
            bundle.putLong(ProductDetailsDialog.PRODUCT_STOCK, stock)
            bundle.putLong(ProductDetailsDialog.PRODUCT_PRICE, price)
            bundle.putLong(ProductDetailsDialog.PRODUCT_ID, id)
            val productDetailsDialog = ProductDetailsDialog.newInstance(bundle)
            productDetailsDialog.show(
                childFragmentManager,
                ProductDetailsDialog.TAG
            )
        }
    }

    override fun onProductClicked(product: ProductItemModel) {
        openProductDetailsDialog(
            product.productId,
            product.productImage,
            product.productPrice,
            product.productName,
            product.productStock
        )
    }
}

interface OnProductClicked {
    fun onProductClicked(product: ProductItemModel)
}



