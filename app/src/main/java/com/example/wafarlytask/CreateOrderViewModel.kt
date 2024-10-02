package com.example.wafarlytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wafarlytask.data.ApiService
import com.example.wafarlytask.data.CreateOrderRepo
import com.example.wafarlytask.data.OrdersRepo
import com.example.wafarlytask.models.orders_response.Item
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModel @Inject constructor(private val createOrderRepo: CreateOrderRepo) : ViewModel(){

    private val productsMutable = MutableLiveData<List<ProductModel>>()
    val productsLiveData: LiveData<List<ProductModel>> get() = productsMutable


    private val orderProductsList : ArrayList<ProductModel> = ArrayList()

    private val orderProductsMutable = MutableLiveData<List<ProductModel>>()
     val  orderProductsLiveData : LiveData<List<ProductModel>> = orderProductsMutable


    fun addToOrder(productModel : ProductModel){
        orderProductsList.add(productModel)
        orderProductsMutable.value = orderProductsList
    }

    fun removeFromOrder(productModel: ProductModel){
        orderProductsList.removeAt(orderProductsList.indexOf(productModel))
        orderProductsMutable.value = orderProductsList
    }

    fun increaseQuantity(productModel: ProductModel){
        productModel.orderQuantity ++
        orderProductsMutable.value = orderProductsList
    }

    fun decreaseQuantity(productModel: ProductModel){
        productModel.orderQuantity --
        if(productModel.orderQuantity == 0)
            orderProductsList.removeAt(orderProductsList.indexOf(productModel))
        orderProductsMutable.value = orderProductsList
    }


    fun getProducts(name : String) {
        viewModelScope.launch{
            val products = createOrderRepo.getProducts(name)
            withContext(Dispatchers.Main){
                productsMutable.value = products
            }
        }
    }


}