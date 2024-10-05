package com.example.wafarlytask.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.ItemProductBinding

import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.utils.Constants
import com.example.wafarlytask.utils.Helper
import com.example.wafarlytask.utils.OrderProductsListener

class ProductsAdapter(private val orderProductsListener: OrderProductsListener) :
    RecyclerView.Adapter<ProductsAdapter.ProductsHolder>(), Filterable {

    private val productsList: ArrayList<ProductModel> = ArrayList()
    private var filteredList: List<ProductModel> = listOf()


    fun addProducts(newProducts: List<ProductModel>) {
        productsList.addAll(newProducts)
        filteredList = productsList
        notifyItemRangeChanged(itemCount, productsList.size)
    }

    class ProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemProductBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        return ProductsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val productModel = filteredList[position]
        holder.binding.tvProductName.text = productModel.name
        holder.binding.tvProductPrice.text =
            Helper.priceWithCurrency(holder.itemView.context,productModel.priceAfterDiscount)
        Glide.with(holder.itemView.context).load(Constants.DOMAIN_URL + productModel.image)
            .into(holder.binding.productImage)

        holder.binding.tvProcutQuantity.text = productModel.orderQuantity.toString()


        /// In Cart
        if (productModel.orderQuantity == 0) {
            holder.binding.btnAddToOrder.visibility = View.VISIBLE
            holder.binding.quantityLinear.visibility = View.GONE
            holder.binding.mainLinear.setBackgroundResource(R.drawable.bg_white_gray_border_5)
        } else {
            holder.binding.btnAddToOrder.visibility = View.GONE
            holder.binding.quantityLinear.visibility = View.VISIBLE
            holder.binding.mainLinear.setBackgroundResource(R.drawable.bg_blue_med_radius_5)

        }

        holder.binding.btnAddToOrder.setOnClickListener {

            if(productModel.salableQuantity <= productModel.orderQuantity){
                Toast.makeText(holder.itemView.context, "الكمية لا تكفي", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
           orderProductsListener. addToOrder(productModel)

            notifyItemChanged(position)
        }

        holder.binding.btnIncreaseQuantity.setOnClickListener {
            if(productModel.salableQuantity <= productModel.orderQuantity){
            Toast.makeText(holder.itemView.context, "الكمية لا تكفي", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
            orderProductsListener. increaseQuantity(productModel)

            notifyItemChanged(position)
        }

        holder.binding.btnDecreaseQuantity.setOnClickListener {

            orderProductsListener. decreaseQuantity(productModel)
            notifyItemChanged(position)
        }




    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraints: CharSequence?): FilterResults {
                val filteredResults = FilterResults()
                val query = constraints.toString().lowercase()

                filteredList = if (query.isEmpty()) {
                    productsList // إذا كانت السلسلة فارغة، إرجاع القائمة الأصلية
                } else {
                    productsList.filter { product ->
                        product.name.lowercase()
                            .contains(query) // استخدام ignoreCase لمطابقة الحالة
                    }
                }

                filteredResults.values = filteredList
                return filteredResults
            }

            override fun publishResults(constraints: CharSequence?, results: FilterResults?) {

                filteredList = results?.values as List<ProductModel>
                notifyDataSetChanged()
            }
        }
    }
}