package com.example.wafarlytask.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.ItemOrderViewBinding
import com.example.wafarlytask.models.orders_response.Item
import com.example.wafarlytask.utils.DateTimeHelper
import com.example.wafarlytask.utils.Helper

class OrdersAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<OrdersAdapter.OrdersHolder>() {

    private val ordersList: ArrayList<Item> = ArrayList()

    fun addOrders(newOrdersList: List<Item>) {
        ordersList.addAll(newOrdersList)
        notifyItemRangeChanged(ordersList.size, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_view, parent, false)
        return OrdersHolder(rootView)
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    override fun onBindViewHolder(holder: OrdersHolder, position: Int) {
        val orderItem = ordersList[position]
        val binding = holder.binding
        binding.tvDateTime.text = DateTimeHelper.getDataTimeText(orderItem.createdDate)
        binding.tvOrderPrice.text = Helper.priceWithCurrency(holder.itemView.context ,orderItem.priceAfterDiscountTotal)

        binding.tvProductsQuantity.text = "  "

        holder.itemView.setOnClickListener { onItemClickListener.onItemClick(orderItem.id) }
    }


    class OrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemOrderViewBinding = ItemOrderViewBinding.bind(itemView)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}