package com.example.wafarlytask.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.ItemOrderElementBinding
import com.example.wafarlytask.models.order_details_response_model.OrderDetail
import com.example.wafarlytask.utils.Constants
import com.example.wafarlytask.utils.Helper

class OrderElementsAdapter(private val orderDetailsList: List<OrderDetail>) :
    RecyclerView.Adapter<OrderElementsAdapter.OrderElementsHolder>() {

    class OrderElementsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemOrderElementBinding = ItemOrderElementBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderElementsHolder {
        return OrderElementsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_element, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return orderDetailsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderElementsHolder, position: Int) {
        val binding = holder.binding
        val product = orderDetailsList[position].product
        val context = holder.itemView.context

        Glide.with(holder.itemView.context).load("${Constants.DOMAIN_URL}${product.image}").into(binding.productImage)
        binding.tvProductName.text = product.name
        binding.tvProductsQuantityPrice.text =
            "${Helper.priceWithCurrency(context,orderDetailsList[position].rowPriceAfterDiscount)} * ${Helper.roundToTwoDecimalPlaces(orderDetailsList[position].quantity)} قطعة"
        binding.tvTotalPrice.text =
            Helper.priceWithCurrency(holder.itemView.context, orderDetailsList[position].rowTotal)
    }
}