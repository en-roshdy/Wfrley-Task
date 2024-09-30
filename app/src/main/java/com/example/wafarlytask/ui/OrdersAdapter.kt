package com.example.wafarlytask.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wafarlytask.R

class OrdersAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<OrdersAdapter.OrdersHolder>() {


    class OrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_view, parent, false)
        return  OrdersHolder(rootView)
    }

    override fun getItemCount(): Int {
return  10
     }

    override fun onBindViewHolder(holder: OrdersHolder, position: Int) {
holder.itemView.setOnClickListener { onItemClickListener.onItemClick(position) }
    }
}