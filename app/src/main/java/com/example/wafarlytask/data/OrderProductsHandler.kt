package com.example.wafarlytask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wafarlytask.models.OrderTotalsModel
import com.example.wafarlytask.models.create_order_model.OrderDetail
import com.example.wafarlytask.models.product_model.ProductModel

class OrderProductsHandler {


    private val orderProductsList: ArrayList<ProductModel> = ArrayList()

    private val orderProductsMutable = MutableLiveData<List<ProductModel>>()
    val orderProductsLiveData: LiveData<List<ProductModel>> = orderProductsMutable




    private val totalsModelMutable = MutableLiveData<OrderTotalsModel>()
    val orderTotalsLiveData : LiveData<OrderTotalsModel> get() = totalsModelMutable

     val orderTotalsModel = OrderTotalsModel(0.0,0.0)


    fun addToOrder(productModel: ProductModel) {
        if(productModel.salableQuantity >= productModel.orderQuantity +1) {
            productModel.orderQuantity = 1
            orderProductsList.add(productModel)
            orderProductsMutable.value = orderProductsList
            calculateOrderTotal()
        }
    }

    fun removeFromOrder(productModel: ProductModel) {
        orderProductsList.removeAt(orderProductsList.indexOf(productModel))
        orderProductsMutable.value = orderProductsList
        calculateOrderTotal()
    }

    fun increaseQuantity(productModel: ProductModel) {
        if(productModel.salableQuantity >= productModel.orderQuantity +1) {
            productModel.orderQuantity++
            orderProductsMutable.value = orderProductsList
            calculateOrderTotal()
        }
    }


    fun decreaseQuantity(productModel: ProductModel) {
        productModel.orderQuantity--
        if (productModel.orderQuantity == 0)
            orderProductsList.removeAt(orderProductsList.indexOf(productModel))
        orderProductsMutable.value = orderProductsList
        calculateOrderTotal()
    }

    fun clearOrderProducts(){
        orderProductsList.clear()
        orderProductsMutable.value = orderProductsList
        calculateOrderTotal()
    }




    // Calculate Total Price
    private  fun calculateOrderTotal() {

        val total = orderProductsMutable.value?.sumOf { it.priceAfterDiscount * it.orderQuantity } ?: 0.0
        val totalQuant = orderProductsList.sumOf {  it.orderQuantity }.toDouble()
        orderTotalsModel.totalPrice = total
        orderTotalsModel.totalQuantity = totalQuant
        totalsModelMutable.value = orderTotalsModel
    }

     fun orderList() : List<OrderDetail>{
        val ordersList = ArrayList<OrderDetail>()
        if(orderProductsMutable.value?.isNotEmpty() == true){
            for(product in orderProductsMutable.value!!){
                ordersList.add(
                    OrderDetail(
                    product.id,
                    product.orderQuantity.toDouble(),
                    product.price,
                    product.price * product.orderQuantity

                )
                )
            }
        }
        return  ordersList
    }
}