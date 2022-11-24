package com.example.healthypalate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthypalate.databinding.NavListItemBinding
import com.example.healthypalate.ui.models.NavMenuModelItem
import com.example.healthypalate.ui.models.NavModelList

class BottomNavAdapter(val list: List<NavMenuModelItem>, private val listener: BottonNavListener) :
        ListAdapter<NavMenuModelItem, BottomNavHolder.NavMenuViewHolder>(NavModelList.NavItemDiff)
     {

    interface BottonNavListener{
        fun onNavMenuItemClicked(item: NavMenuModelItem)
    }

    @Suppress("unchecked cast")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomNavHolder.NavMenuViewHolder {
        val view = NavListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomNavHolder.NavMenuViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: BottomNavHolder.NavMenuViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}