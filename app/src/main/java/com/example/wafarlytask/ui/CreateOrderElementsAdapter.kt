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
import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.utils.Constants
import com.example.wafarlytask.utils.Helper




class CreateOrderElementsAdapter(private val orderDetailsList: List<ProductModel>) :
    RecyclerView.Adapter<CreateOrderElementsAdapter.CreateOrderElementsHolder>() {

    class CreateOrderElementsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemOrderElementBinding = ItemOrderElementBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateOrderElementsHolder {
        return CreateOrderElementsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_element, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return orderDetailsList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CreateOrderElementsHolder, position: Int) {
        val binding = holder.binding
        val product = orderDetailsList[position]
        val context = holder.itemView.context

        Glide.with(holder.itemView.context).load("${Constants.DOMAIN_URL}${product.image}").into(binding.productImage)
        binding.tvProductName.text = product.name
        binding.tvProductsQuantityPrice.text =
            "${Helper.priceWithCurrency(context,orderDetailsList[position].priceAfterDiscount)} * ${Helper.roundToTwoDecimalPlaces(orderDetailsList[position].orderQuantity.toDouble())} قطعة"

        binding.tvTotalPrice.text =
            Helper.priceWithCurrency(context,product.orderQuantity*product.priceAfterDiscount)
    }
}