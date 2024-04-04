package com.example.fashionflair.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fashionflair.R
import com.example.fashionflair.databinding.FragmentCartBinding
import com.example.fashionflair.presentation.adapters.CartAdapter
import com.example.fashionflair.presentation.viewModels.CartViewModel
import com.example.fashionflair.utils.gone
import com.example.fashionflair.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private val viewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.getCartList() != null && viewModel.getCartList()?.isNotEmpty() == true) {
            cartAdapter = CartAdapter(viewModel.getCartList()!!)
            binding.cartRv.adapter = cartAdapter
            binding.cartRv.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        } else {
            binding.cartRv.gone()
            binding.emptyTv.visible()
        }

        binding.checkoutBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Thanks for shopping with us!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_home)
        }
    }
}