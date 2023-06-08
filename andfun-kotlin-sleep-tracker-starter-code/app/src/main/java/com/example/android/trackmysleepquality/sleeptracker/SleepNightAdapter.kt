package com.example.android.trackmysleepquality.sleeptracker

import androidx.recyclerview.widget.DiffUtil
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter :
    BaseAdapter<SleepNight, ListItemSleepNightBinding, SleepNightAdapter.SleepNightViewHolder>(
        SleepNightDiffCallback()
    ) {
    override fun getLayoutId() = R.layout.list_item_sleep_night
    override fun getViewViewHolder(binding: ListItemSleepNightBinding) =
        SleepNightViewHolder(binding)

    class SleepNightViewHolder(private val binding: ListItemSleepNightBinding) :
        BaseViewHolder<SleepNight, ListItemSleepNightBinding>(binding) {
        override fun bind(item: SleepNight) {
            val resource = binding.root.context.resources
            binding.sleepLength.text =
                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resource)
            binding.qualityString.text =
                convertNumericQualityToString(item.sleepQuality, resource)
            binding.qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
        }
    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight) =
            oldItem.nightID == newItem.nightID

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight) =
            oldItem == newItem

    }
}
