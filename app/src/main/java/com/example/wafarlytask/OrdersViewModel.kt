package com.example.wafarlytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wafarlytask.data.OrdersRepo
import com.example.wafarlytask.models.order_details_response_model.OrderDetailsResponse
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import com.example.wafarlytask.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val ordersRepo: OrdersRepo) : ViewModel() {

    private var currentPage = 1
    private val mutableData : MutableLiveData<OrdersResponseModel> = MutableLiveData()
    val ordersLiveData: LiveData<OrdersResponseModel> get() = mutableData

    private val showLoadMoreMutable : MutableLiveData<Boolean>  = MutableLiveData()
    val showMoreLive : LiveData<Boolean> = showLoadMoreMutable


    private val loadingOrderDetailsMutable : MutableLiveData<Boolean>  = MutableLiveData()
    val loadingOrderDetailsLive : LiveData<Boolean> = loadingOrderDetailsMutable


    private val orderDetailsMutable : MutableLiveData<OrderDetailsResponse> = MutableLiveData()
    val orderDetailsLive : LiveData<OrderDetailsResponse> = orderDetailsMutable

    fun getData() {
        viewModelScope.launch {
            val allOrders = ordersRepo.getAllOrders(currentPage)
            withContext(Dispatchers.Main){
                mutableData.value = allOrders
                showLoadMoreMutable.value = false
            }
        }
    }

    fun showMoreOrders(){
        if(hasMorePages()) {
            currentPage++
            showLoadMoreMutable.value = true
            getData()
        }
    }

    private fun hasMorePages(): Boolean{
        val itemsCount = mutableData.value?.itemsCount ?: 0
        if (itemsCount != 0){
            val totalPages = itemsCount / Constants.ORDERS_PAGE_SIZE
            return  currentPage < totalPages
        }
        return  false

    }


    // Get Order Details
     fun getOrderDetails(orderId : Int){
        loadingOrderDetailsMutable.value = true
        viewModelScope.launch {
            val orderById = ordersRepo.getOrderById(orderId)
            withContext(Dispatchers.Main){
                orderDetailsMutable.value = orderById
                loadingOrderDetailsMutable.value = false
            }
        }
    }
}