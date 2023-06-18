package com.example.android.trackmysleepquality.sleeptracker

import androidx.recyclerview.widget.DiffUtil
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter(private val listener: SleepNightClickListener) :
    BaseAdapter<SleepNight, ListItemSleepNightBinding, SleepNightAdapter.SleepNightViewHolder>(
        SleepNightDiffCallback()
    ) {
    override fun getLayoutId() = R.layout.list_item_sleep_night
    override fun getViewViewHolder(binding: ListItemSleepNightBinding) =
        SleepNightViewHolder(binding, listener)

    class SleepNightViewHolder(
        private val binding: ListItemSleepNightBinding,
        private val listener: SleepNightClickListener
    ) :
        BaseViewHolder<SleepNight, ListItemSleepNightBinding>(binding) {
        override fun bind(item: SleepNight) {
            binding.sleep = item
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight) =
            oldItem.nightID == newItem.nightID

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight) =
            oldItem == newItem

    }
}

class SleepNightClickListener(val listener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = listener(night.nightID)
}