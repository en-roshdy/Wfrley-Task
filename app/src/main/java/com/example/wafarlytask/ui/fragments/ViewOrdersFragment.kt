package com.example.wafarlytask.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.FragmentViewOrdersBinding
import com.example.wafarlytask.ui.OrdersAdapter
import com.example.wafarlytask.ui.activities.CreateOrderActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewOrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewOrdersFragment : Fragment() , OrdersAdapter.OnItemClickListener{

    private lateinit var binding: FragmentViewOrdersBinding
    private lateinit var ordersAdapter: OrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewOrdersBinding.inflate(inflater, container, false)
        setRecyclerView()

        binding.addNewOrderClick.setOnClickListener {
            startActivity(Intent(requireActivity(),CreateOrderActivity::class.java))
        }
        return binding.root
    }

    private fun setRecyclerView() {
        binding.rvOrders.layoutManager = LinearLayoutManager(context)
        ordersAdapter = OrdersAdapter(this)
        binding.rvOrders.adapter = ordersAdapter
        binding.rvOrders.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun navigateToDetails( orderId : Int){
        findNavController().navigate(R.id.action_viewOrdersFragment_to_orderDetailsFragment)

    }

    override fun onItemClick(position: Int) {
        Log.d(TAG, "onItemClick: position $position")
        navigateToDetails(position)
    }
    private  val TAG = "ViewOrdersFragment"

}