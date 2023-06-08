package com.example.android.trackmysleepquality.sleeptracker

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T, V : ViewDataBinding>(viewBinding: V) :
    RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun bind(item: T)
}