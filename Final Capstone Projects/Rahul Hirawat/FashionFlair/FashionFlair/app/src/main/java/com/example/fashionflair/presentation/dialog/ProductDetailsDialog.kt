package com.example.fashionflair.presentation.dialog

import android.R
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fashionflair.databinding.ProductDetailsDialogFragmentBinding
import com.example.fashionflair.domain.model.ProductItemModel
import com.example.fashionflair.presentation.viewModels.CartViewModel
import com.example.fashionflair.utils.gone
import com.example.fashionflair.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsDialog : DialogFragment() {
    private lateinit var binding: ProductDetailsDialogFragmentBinding
    private val viewModel by viewModels<CartViewModel>()

    private var productName: String? = null
    private var productImage: String? = null
    private var productStock: Long = -1
    private var productPrice: Long? = null
    private var productId: Long = -1


    companion object {
        const val TAG = "ProductDetailsDialog"
        const val IMAGE_URL = "image_url"
        const val PRODUCT_ID = "product_id"
        const val PRODUCT_NAME = "product_name"
        const val PRODUCT_STOCK = "product_stock"
        const val PRODUCT_PRICE = "product_price"

        fun newInstance(bundle: Bundle): ProductDetailsDialog {
            return ProductDetailsDialog().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

        productName = arguments?.getString(PRODUCT_NAME)
        productImage = arguments?.getString(IMAGE_URL)
        productStock = arguments?.getLong(PRODUCT_STOCK) ?: -1
        productPrice = arguments?.getLong(PRODUCT_PRICE)
        productId = arguments?.getLong(PRODUCT_ID) ?: -1
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            val window = requireDialog().window
            if (window != null) requireDialog().window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        )
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ProductDetailsDialogFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productName.text = productName
        Glide.with(requireContext()).load(productImage).into(binding.productImage)
        binding.productPrice.text = "₹ $productPrice"

        if (productStock < 10) {
            binding.productStockWarning.visible()
            binding.productStockWarning.text = "Only $productStock is left!"
        } else {
            binding.productStockWarning.gone()
        }

        
        binding.addToCartBtn.setOnClickListener {
            if (productName.isNullOrEmpty() || productPrice == null || productImage.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Invalid Product", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val list = viewModel.getCartList()?: arrayListOf()
            list.add(
                0, ProductItemModel(
                    productId,
                    productName!!,
                    productPrice!!,
                    productImage!!,
                    productStock
                )
            )
            viewModel.setCartList(list = list)
            Toast.makeText(requireContext(), "Product Stored in Cart", Toast.LENGTH_SHORT).show()
        }

        binding.shareBtn.setOnClickListener {
            shareProduct(
                productName ?: "Amazing deal",
                "On Sale, available only at ₹ $productPrice"
            )
        }

        binding.backBtn.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    private fun shareProduct(title: String, description: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, description)
        }
        startActivity(Intent.createChooser(shareIntent, "Share product via"))
    }
}