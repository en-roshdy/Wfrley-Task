package com.example.wafarlytask.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.FragmentOrderDetailsBinding
import com.example.wafarlytask.models.order_details_response_model.OrderDetailsResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class OrderDetailsFragment : Fragment() {
    private val TAG = "OrderDetailsFragment"
    private var orderId = 0
    private val ordersViewModel: OrdersViewModel by activityViewModels()
    private lateinit var binding : FragmentOrderDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderDetailsBinding.inflate(inflater,container,false)
        orderId = arguments?.getInt("orderId", 0) ?: 0

        observeOrderDetails()
        observeOrderDetailsLoading()
        ordersViewModel.getOrderDetails(orderId)

        return binding.root
    }

    private fun observeOrderDetails() {
        ordersViewModel.orderDetailsLive.observe(viewLifecycleOwner) {

            if(it != null){
                handleOrderDetails(it)
            }
        }

    }


    @SuppressLint("SetTextI18n")
    private fun handleOrderDetails(orderDetailsResponse : OrderDetailsResponse){
        binding.tvBillNo.text = "فاتورة رقم ${orderDetailsResponse.orderNumber}"
        binding.tvDate.text = orderDetailsResponse.createdDate
//        binding.tvTime.text = orderDetailsResponse.
        binding.tvClientName.text = orderDetailsResponse.merchantName
//        binding.tvClientPhone.text= orderDetailsResponse.customerUser.t
    }


    private fun observeOrderDetailsLoading() {
        ordersViewModel.loadingOrderDetailsLive.observe(viewLifecycleOwner) {

            if(it != false){

            }
        }

    }




}