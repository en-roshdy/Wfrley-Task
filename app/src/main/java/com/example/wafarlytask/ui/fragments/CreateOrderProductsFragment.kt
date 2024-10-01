package com.example.wafarlytask.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wafarlytask.CreateOrderViewModel
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.FragmentCreateOrderProductsBinding
import com.example.wafarlytask.ui.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateOrderProductsFragment : Fragment() {
    private  val TAG = "CreateOrderProductsFrag"
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var binding: FragmentCreateOrderProductsBinding
    private lateinit var gridLayoutManager: GridLayoutManager

    private val createOrdersViewModel: CreateOrderViewModel by activityViewModels()

    private var searchKey : String = ""

    private fun setProductsRecyclerView() {
        productsAdapter = ProductsAdapter()
        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProducts.adapter = productsAdapter
        binding.rvProducts.layoutManager = gridLayoutManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreateOrderProductsBinding.inflate(inflater, container, false)
        setProductsRecyclerView()
        observeProducts()

        getProducts()

        binding.productSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                // your text view here
                Log.d(TAG, "onQueryTextChange: $newText -- End")
            productsAdapter.filter.filter(newText ?: "")

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }
        })
        binding.btnCompleteOrder.setOnClickListener {
            findNavController().navigate(R.id.completeOrderFragment)
        }
        return binding.root
    }

    private fun getProducts(){
        createOrdersViewModel.getProducts(searchKey)
    }

    private fun observeProducts() {
        createOrdersViewModel.productsLiveData.observe(viewLifecycleOwner) {
            data ->
            run {
                if (data != null && data.isNotEmpty()) {
                    productsAdapter.addProducts(data)
                }
            }

        }
    }


}