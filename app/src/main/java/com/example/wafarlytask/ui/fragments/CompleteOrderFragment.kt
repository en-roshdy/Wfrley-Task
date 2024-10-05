package com.example.wafarlytask.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wafarlytask.CreateOrderViewModel
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.FragmentCompleteOrderBinding
import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.ui.CreateOrderElementsAdapter
import com.example.wafarlytask.ui.OrderElementsAdapter
import com.example.wafarlytask.ui.activities.CreateOrderActivity
import dagger.hilt.android.AndroidEntryPoint



class CompleteOrderFragment : Fragment() {

    private val TAG = "CompleteOrderFragment"

    private lateinit var binding: FragmentCompleteOrderBinding

    private val createOrdersViewModel: CreateOrderViewModel by activityViewModels()

    private var validateAmount = false
    private var validateClientName = false
    private var validateClientPhone = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteOrderBinding.inflate(inflater, container, false)
        handleValidation()
        observeOrderProducts()
        observeCreateOrder()
        binding.btnCreateOrder.setOnClickListener { createOrdersViewModel.createOrder() }
        return binding.root
    }

    private fun handleElementsRecyclerView(products : List<ProductModel>){
        binding.rvOrderElements.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderElements.adapter = CreateOrderElementsAdapter(products)
    }

    private fun observeOrderProducts() {
        createOrdersViewModel.orderProductsLiveData.observe(viewLifecycleOwner) {
            run {
                if (it != null && it.isNotEmpty()) {
                    handleElementsRecyclerView(it)
                }
            }

        }
    }

    private fun handleValidation() {
        binding.etPayedAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateAmount = p0.toString() != "null" && p0.toString() != ""
                canCompleteOrder()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.etClientName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                validateClientName = p0.toString() != "null" && p0.toString() != ""
                canCompleteOrder()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.etClientPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                validateClientPhone = p0.toString() != "null" && p0.toString() != ""
                Log.d(TAG, "onTextChanged: $p0")
                canCompleteOrder()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun canCompleteOrder() {

        Log.d(
            TAG,
            "canCompleteOrder: amount $validateAmount , name $validateClientName , phone $validateClientPhone"
        )

        binding.btnCreateOrder.isEnabled =
            validateAmount && validateClientName && validateClientPhone
    }

    private fun observeCreateOrder(){
        createOrdersViewModel.createOrderLiveData.observe(viewLifecycleOwner){
            if(it != null ){
                Log.d(TAG, "observeCreateOrder: ${it.errorString}")
                if(it.getErrors() != null){
                    /// Handle Error
                    Toast.makeText(requireContext(), it.getErrors()?.errors?.get(0) ?: "", Toast.LENGTH_SHORT).show()

                    return@observe
                }

                if(it.id != 0 ){
                    (requireActivity() as CreateOrderActivity).refreshOrders()
                    successDialog()
                    requireActivity().onBackPressedDispatcher.onBackPressed()

                }
            }
        }
    }



    private fun successDialog(){
        val builder =
            AlertDialog.Builder(requireContext())

        @SuppressLint("InflateParams")
        val loginDialogView: View =
           requireActivity(). layoutInflater.inflate(R.layout.dialog_success, null)
        builder.setView(loginDialogView)
        val successDialog = builder.create()

        successDialog.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        successDialog.show()

        createOrdersViewModel.getProducts("")
        createOrdersViewModel.clearOrderProducts()

    }

}