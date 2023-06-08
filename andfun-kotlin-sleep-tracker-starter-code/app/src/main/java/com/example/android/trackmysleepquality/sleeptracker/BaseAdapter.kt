package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T, V : ViewDataBinding, VH : BaseViewHolder<T, V>>(
    diffUtil: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, VH>(diffUtil) {
    abstract fun getLayoutId(): Int
    abstract fun getViewViewHolder(binding: V): VH
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            parent,
            false
        ) as V
        return getViewViewHolder(binding)
    }

    fun getViewBinding(parent: ViewGroup): V {
        val inflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            parent,
            false
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}