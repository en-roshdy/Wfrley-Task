package com.example.wafarlytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wafarlytask.data.DataRepo
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrdersViewModel @Inject constructor(private val dataRepo: DataRepo) : ViewModel() {

    private var currentPage = 1
    private val mutableData : MutableLiveData<OrdersResponseModel> = MutableLiveData()
    val ordersLiveData: LiveData<OrdersResponseModel> get() = mutableData


    fun getData() {
        viewModelScope.launch {
            val allOrders = dataRepo.getAllOrders(currentPage)
            mutableData.value = allOrders
        }
    }
}