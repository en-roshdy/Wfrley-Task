package com.example.wafarlytask.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.databinding.FragmentOrderDetailsBinding
import com.example.wafarlytask.models.order_details_response_model.OrderDetail
import com.example.wafarlytask.models.order_details_response_model.OrderDetailsResponse
import com.example.wafarlytask.ui.OrderElementsAdapter
import com.example.wafarlytask.utils.DateTimeHelper
import com.example.wafarlytask.utils.Helper

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

        binding.btnBack.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        binding.btnHome.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        return binding.root
    }

    private fun observeOrderDetails() {
        ordersViewModel.orderDetailsLive.observe(viewLifecycleOwner) {

            if(it != null){
                binding.progressView.visibility = View.GONE
                binding.mainView.visibility = View.VISIBLE
                handleOrderDetails(it)
            }
        }

    }


    @SuppressLint("SetTextI18n")
    private fun handleOrderDetails(orderDetailsResponse : OrderDetailsResponse){
        binding.tvBillNo.text = "فاتورة رقم ${orderDetailsResponse.orderNumber}"
        binding.tvDate.text = orderDetailsResponse.createdDate
        val createdDate = orderDetailsResponse.createdDate
        binding.tvDate.text = DateTimeHelper.getDateFromDateTime(createdDate)
        binding.tvTime.text ="( ${DateTimeHelper.getTimeFromDateTime(createdDate)} )"
        binding.tvClientName.text = orderDetailsResponse.merchantName
        binding.tvClientPhone.text= orderDetailsResponse.shippingAddresses[0].telephone
        binding.tvTotal.text = Helper.priceWithCurrency(requireContext(),orderDetailsResponse.grandTotal)
        binding.tvClientAddress.text =orderDetailsResponse.shippingAddresses[0].toString()


        binding.tvPaymentMethod.text= if(orderDetailsResponse.paymentMethod == 5) "نقدي" else "--- ${orderDetailsResponse.paymentMethod}"
        binding.tvTotalDiscount.text = Helper.priceWithCurrency(requireContext(),orderDetailsResponse.discountAmount)
        binding.tvTotal .text = Helper.priceWithCurrency(requireContext(),orderDetailsResponse.priceAfterDiscountTotal)
        handleOrderElementsRecycler(orderDetailsResponse.orderDetails)

    }


    private fun handleOrderElementsRecycler(orderDetails: List<OrderDetail>) {
        binding.rvOrderElements.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderElements.adapter = OrderElementsAdapter(orderDetails)
    }


    private fun observeOrderDetailsLoading() {
        ordersViewModel.loadingOrderDetailsLive.observe(viewLifecycleOwner) {

            if(it != false){
            }
        }

    }




}