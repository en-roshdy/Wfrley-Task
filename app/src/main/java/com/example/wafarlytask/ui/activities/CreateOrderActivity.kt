package com.example.wafarlytask.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wafarlytask.CreateOrderViewModel
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateOrderActivity : AppCompatActivity() {

    private val viewOrdersViewModel: OrdersViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun refreshOrders(){
        viewOrdersViewModel.refreshOrders()
    }
}