package com.example.wafarlytask.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewOrdersActivity : AppCompatActivity() {

//    private val viewModel: OrdersViewModel by viewModels()
    private  val TAG = "ViewOrdersActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_view_orders)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        viewModel.ordersLiveData.observe(this) {
//            Log.d(TAG, "onCreate: orders ${it.toString()}")
//        }
    }
}