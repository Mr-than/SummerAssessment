package com.example.summerassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.summerassessment.R
import com.example.summerassessment.ui.mainactivity.MainActivity

class HomePageVpAdapter(private val context: FragmentActivity, private val adapter:HomeRecommendAdapter): RecyclerView.Adapter<HomePageVpAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val a: RecyclerView = itemView.findViewById(R.id.home_page_vp_item_rv)
        private val re: SwipeRefreshLayout =
            itemView.findViewById(R.id.home_page_vp_item_rv_refresh)

        init {
            re.setOnRefreshListener {
                adapter.refresh()
            }
            adapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        re.isRefreshing = false
                    }
                    is LoadState.Loading -> {
                        re.isRefreshing = true
                    }
                    is LoadState.Error -> {
                        val state = it.refresh as LoadState.Error
                        re.isRefreshing = false
                        Toast.makeText(context, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate( R.layout.home_page_vp_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position==1) {
            holder.a.layoutManager = LinearLayoutManager(context as MainActivity)
            holder.a.adapter = adapter
        }
    }

    override fun getItemCount(): Int =5
}