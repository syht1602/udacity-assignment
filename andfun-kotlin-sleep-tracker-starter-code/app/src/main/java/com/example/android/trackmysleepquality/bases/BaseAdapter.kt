package com.example.android.trackmysleepquality.bases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.HeaderBinding

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

abstract class BaseAdapter<T : Any, V : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
) :
    ListAdapter<T, BaseViewHolder<*>>(diffUtil) {
    abstract fun getLayoutId(): Int
    abstract fun getViewHolder(binding: Any): BaseViewHolder<V>

    /**
     * Set header name if you want to config header
     */
    private var header: DataItem.Header? = null

    open fun getHeaderLayout(): Int = R.layout.header

    fun setHeader(header: DataItem.Header?) {
        this.header = header
    }

    fun getHeader() = header

    fun isHaveHeader() = header != null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                val headerBinding = DataBindingUtil.inflate(
                    inflater,
                    getHeaderLayout(),
                    parent,
                    false
                ) as HeaderBinding
                return HeaderViewHolder(headerBinding)
            }

            ITEM_VIEW_TYPE_ITEM -> {
                val viewBinding = DataBindingUtil.inflate(
                    inflater,
                    getLayoutId(),
                    parent,
                    false
                ) as V
                return getViewHolder(viewBinding)
            }

            else -> {
                throw ClassCastException("Unknown viewType $viewType")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.SleepNightItem -> ITEM_VIEW_TYPE_ITEM
            else -> throw ClassCastException("Unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                if (header == null) {
                    holder.itemView.visibility = View.GONE
                } else {
                    holder.bind(header!!)
                }
            }

            else -> {
                holder.bind(getItem(position))
            }
        }
    }

    class HeaderViewHolder(private val binding: HeaderBinding) :
        BaseViewHolder<HeaderBinding>(binding) {
        override fun bind(item: Any) {
            if (item is DataItem.Header)
                binding.text.text = item.header
        }
    }

    sealed class DataItem {

        abstract val id: Long

        data class SleepNightItem(val sleepNight: SleepNight) : DataItem() {
            override val id = sleepNight.nightID
        }

        data class Header(val header : String?) : DataItem() {
            override val id = Long.MIN_VALUE
        }
    }
}