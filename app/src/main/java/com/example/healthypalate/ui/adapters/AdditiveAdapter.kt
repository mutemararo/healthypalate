package com.example.healthypalate.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.healthypalate.R
import com.example.healthypalate.databinding.AdditiveItemBinding
import com.example.healthypalate.ui.models.Additive

class AdditiveAdapter(val list : MutableList<Additive>): ListAdapter<Additive, AdditiveAdapter.AdditiveViewHolder>(DiffUtil) {

    class AdditiveViewHolder(val binding: AdditiveItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(additive: Additive){
            binding.apply {
                additiveName.text = additive.name
                additiveCode.text = additive.code
                additiveFunction.text = additive.function
                additiveWarnings.text = additive.warnings
                additiveDetails.text = additive.details
                additiveCommonFoods.text = additive.foods
                additiveCategory.text = additive.category


                iconExpand.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                iconExpand.setOnClickListener {
                    if (expandedLayout.visibility == View.GONE) {
                        expandedLayout.visibility = View.VISIBLE
                        TransitionManager.beginDelayedTransition(cardview, AutoTransition())
                        iconExpand.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
                    } else if (expandedLayout.visibility == View.VISIBLE) {
                        expandedLayout.visibility = View.GONE
                        TransitionManager.beginDelayedTransition(cardview, AutoTransition())
                        iconExpand.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditiveViewHolder {
        val view = AdditiveItemBinding.inflate(LayoutInflater.from(parent.context))
        return AdditiveViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdditiveViewHolder, position: Int) {
        var currentItem = list[position]
        holder.bind(currentItem)
    }

    companion object
    {
        val DiffUtil = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<Additive>() {
            override fun areItemsTheSame(oldItem: Additive, newItem: Additive): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Additive, newItem: Additive): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.code == newItem.code
            }

        }
    }
}