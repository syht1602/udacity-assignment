package com.example.android.trackmysleepquality.sleeptracker

import androidx.recyclerview.widget.DiffUtil
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.bases.BaseAdapter
import com.example.android.trackmysleepquality.bases.BaseViewHolder
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepNightAdapter(private val listener: SleepNightClickListener) :
    BaseAdapter<BaseAdapter.DataItem, ListItemSleepNightBinding>(
        SleepNightDiffCallback()
    ) {
    override fun getLayoutId() = R.layout.list_item_sleep_night
    override fun getViewHolder(binding: Any): BaseViewHolder<ListItemSleepNightBinding> =
        SleepNightViewHolder(binding as ListItemSleepNightBinding, listener)

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<SleepNight>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> {
                    if (isHaveHeader()) {
                        listOf(getHeader()!!)
                    } else {
                        listOf()
                    }
                }

                else -> {
                    if (isHaveHeader()) {
                        listOf(getHeader()!!) + list.map { DataItem.SleepNightItem(it) }
                    } else {
                        list.map { DataItem.SleepNightItem(it) }
                    }
                }
            }

            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    class SleepNightViewHolder(
        private val binding: ListItemSleepNightBinding,
        private val listener: SleepNightClickListener,
    ) :
        BaseViewHolder<ListItemSleepNightBinding>(binding) {
        override fun bind(item: Any) {
            if (item is DataItem.SleepNightItem) {
                binding.sleep = item.sleepNight
                binding.listener = listener
                binding.executePendingBindings()
            }
        }
    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem) =
            oldItem == newItem

    }
}

class SleepNightClickListener(val listener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = listener(night.nightID)
}