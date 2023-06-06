package com.example.android.trackmysleepquality.sleeptracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> :
    RecyclerView.Adapter<VH>() {
    var data = listOf<T>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun setLayoutId(): Int

    fun inflateLayout(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_sleep_night, parent, false)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

