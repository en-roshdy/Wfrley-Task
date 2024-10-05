package com.example.wafarlytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wafarlytask.data.OrderProductsHandler
import com.example.wafarlytask.data.CreateOrderRepo
import com.example.wafarlytask.models.OrderTotalsModel
import com.example.wafarlytask.models.create_order_model.CreateOrderBodyModel
import com.example.wafarlytask.models.create_order_model.CreateOrderResponse
import com.example.wafarlytask.models.product_model.ProductModel
import com.example.wafarlytask.utils.Constants
import com.example.wafarlytask.utils.DateTimeHelper
import com.example.wafarlytask.utils.RetrofitObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CreateOrderViewModel @Inject constructor(private val createOrderRepo: CreateOrderRepo) :
    ViewModel() {

    private val orderProductsHandler = OrderProductsHandler()

    private val productsMutable = MutableLiveData<List<ProductModel>>()
    val productsLiveData: LiveData<List<ProductModel>> get() = productsMutable

    private val createOrderMutable = MutableLiveData<CreateOrderResponse>()
    val createOrderLiveData: LiveData<CreateOrderResponse> get() = createOrderMutable

    val orderProductsLiveData: LiveData<List<ProductModel>>get()  = orderProductsHandler.orderProductsLiveData

    fun increaseQuantity(productModel: ProductModel) {
        orderProductsHandler.increaseQuantity(productModel)
    }

    fun decreaseQuantity(productModel: ProductModel) {
        orderProductsHandler.decreaseQuantity(productModel)
    }

    fun addToOrder(productModel: ProductModel) {
        orderProductsHandler.addToOrder(productModel)
    }

    fun removeFromOrder(productModel: ProductModel) {
        orderProductsHandler.removeFromOrder(productModel)
    }

    val orderTotalsLiveData : LiveData<OrderTotalsModel> get() = orderProductsHandler.orderTotalsLiveData


    fun getProducts(name: String) {

        productsMutable.value = ArrayList()

        viewModelScope.launch {
            val products = createOrderRepo.getProducts(name)
            withContext(Dispatchers.Main) {
                productsMutable.value = products
            }
        }
    }


    fun createOrder() {
        var createOrderResponse = CreateOrderResponse()
        viewModelScope.launch {
            try {
                val orderBodyModel = CreateOrderBodyModel(
                    227,
                    Constants.CUSTOMER_ID,
                    Constants.CUSTOMER_SERVICE_USER_ID,
                    Constants.ORDER_DELIVERY_METHOD,
                    orderProductsHandler.orderList(),
                    Constants.ORDER_DELIVERY_METHOD,
                    DateTimeHelper.getCurrentDateTime(),
                    orderProductsHandler.orderTotalsModel.totalPrice.roundToInt(),
                    Constants.STORE_ID
                )

                val response = createOrderRepo.createOrder(orderBodyModel)
                createOrderResponse = response

            } catch (e: HttpException) {


                createOrderResponse.errorString = RetrofitObject.errorToString(e)

            } catch (e: IOException) {
                createOrderResponse.throwable = e

            } catch (e: Exception) {

                createOrderResponse.throwable = e
            }

            withContext(Dispatchers.Main) {

                createOrderMutable.value = createOrderResponse
            }

        }
    }

    fun clearOrderProducts() {
        orderProductsHandler.clearOrderProducts()
    }

    private val TAG = "CreateOrderViewModel"


}