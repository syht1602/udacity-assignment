package com.example.android.trackmysleepquality.bases

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<V : ViewDataBinding>(viewBinding: V) :
    RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun bind(item: Any)
}