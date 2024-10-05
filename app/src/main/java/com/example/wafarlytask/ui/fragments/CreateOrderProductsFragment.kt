package com.example.wafarlytask.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wafarlytask.CreateOrderViewModel
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.FragmentCreateOrderProductsBinding
import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.ui.ProductsAdapter
import com.example.wafarlytask.utils.Helper
import com.example.wafarlytask.utils.OrderProductsListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateOrderProductsFragment : Fragment(),OrderProductsListener {
    private  val TAG = "CreateOrderProductsFrag"
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var binding: FragmentCreateOrderProductsBinding
    private lateinit var gridLayoutManager: GridLayoutManager

    private val createOrdersViewModel: CreateOrderViewModel by activityViewModels()

    private var searchKey : String = ""

    private fun setProductsRecyclerView() {
        productsAdapter = ProductsAdapter(this)
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
        observeOrderTotal()
        getProducts()
        binding.productSearchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

             val searchKey = p0?.toString() ?: ""
                productsAdapter.filter.filter(searchKey)

            }

            override fun afterTextChanged(p0: Editable?) {
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
                    binding.mainProgressBar.visibility = View.GONE
                }
            }

        }
    }



    private fun observeOrderTotal(){
        createOrdersViewModel.orderTotalsLiveData.observe(viewLifecycleOwner){
            if (it.totalQuantity ==0.0) {
                binding.btnCompleteOrder.visibility = View.GONE

            }else{
                binding.btnCompleteOrder.visibility = View.VISIBLE
                binding.tvOrderCount.text = "${Helper.roundToTwoDecimalPlaces(it.totalQuantity)}"
                binding.tvOrderPrice.text = Helper.priceWithCurrency(requireContext(),it.totalPrice)

            }
        }
    }

    override fun increaseQuantity(productModel: ProductModel) {
        createOrdersViewModel.increaseQuantity(productModel)

    }

    override fun decreaseQuantity(productModel: ProductModel) {
        createOrdersViewModel.decreaseQuantity(productModel)

    }

    override fun addToOrder(productModel: ProductModel) {
        createOrdersViewModel.addToOrder(productModel)
    }

    override fun removeFromOrder(productModel: ProductModel) {
        createOrdersViewModel.removeFromOrder(productModel)

    }


}