package com.example.wafarlytask.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wafarlytask.OrdersViewModel
import com.example.wafarlytask.R
import com.example.wafarlytask.databinding.FragmentViewOrdersBinding
import com.example.wafarlytask.ui.OrdersAdapter
import com.example.wafarlytask.ui.activities.CreateOrderActivity
import com.roshdy.alskab.utils.EndlessRecyclerViewScrollListener


class ViewOrdersFragment : Fragment(), OrdersAdapter.OnItemClickListener {

    private lateinit var binding: FragmentViewOrdersBinding
    private lateinit var ordersAdapter: OrdersAdapter
    private val ordersViewModel: OrdersViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewOrdersBinding.inflate(inflater, container, false)
        setRecyclerView()

        binding.addNewOrderClick.setOnClickListener {
            startActivity(Intent(requireContext(), CreateOrderActivity::class.java))
        }

        observeOrders()
        observeShowMoreProgress()
        ordersViewModel.getData()
        return binding.root
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvOrders.layoutManager = linearLayoutManager
        ordersAdapter = OrdersAdapter(this)
        binding.rvOrders.adapter = ordersAdapter
        binding.rvOrders.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                ordersViewModel.showMoreOrders()
            }

        })
        binding.rvOrders.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun observeOrders() {
        ordersViewModel.ordersLiveData.observe(viewLifecycleOwner) { data ->
            run {
                if (data.items.isNotEmpty()) {

                    ordersAdapter.addOrders(data.items)

                } else {
                    /// No Orders

                }
            }
        }
    }

    private fun observeShowMoreProgress() {
        ordersViewModel.showMoreLive.observe(viewLifecycleOwner) { loadMore ->
            run {
                if (loadMore == true) {
                    binding.loadMoreProgress.visibility = View.VISIBLE
                } else {
                    binding.loadMoreProgress.visibility = View.GONE

                }
            }
        }
    }

    private fun navigateToDetails(orderId: Int) {
        val bundle = Bundle()
        bundle.putInt("orderId", orderId)
        findNavController().navigate(R.id.action_viewOrdersFragment_to_orderDetailsFragment,bundle)

    }

    override fun onItemClick(position: Int) {
        Log.d(TAG, "onItemClick: position $position")
        navigateToDetails(position)
    }

    private val TAG = "ViewOrdersFragment"

}