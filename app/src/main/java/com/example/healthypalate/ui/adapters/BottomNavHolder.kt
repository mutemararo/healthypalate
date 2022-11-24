package com.example.healthypalate.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.healthypalate.databinding.NavListItemBinding
import com.example.healthypalate.ui.models.NavMenuModelItem

abstract class BottomNavHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView){

    abstract fun bind(nav_item: T)

    class NavMenuViewHolder(val binding: NavListItemBinding,
    val listenerParameter: BottomNavAdapter.BottonNavListener
    ): BottomNavHolder<NavMenuModelItem>(binding.root){
        override fun bind(nav_item: NavMenuModelItem) {
            binding.run {
                navModel = nav_item
                listener = listenerParameter
                executePendingBindings()
            }
        }

    }
}