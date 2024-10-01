package com.example.wafarlytask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wafarlytask.data.OrdersRepo
import com.example.wafarlytask.models.orders_response.OrdersResponseModel
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


    fun getData() {
        viewModelScope.launch {
            val allOrders = ordersRepo.getAllOrders(currentPage)
            withContext(Dispatchers.Main){
                mutableData.value = allOrders
            }
        }
    }
}