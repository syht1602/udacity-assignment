package com.example.android.trackmysleepquality.sleeptracker

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : BaseAdapter<SleepNight, SleepNightAdapter.SleepNightViewHolder>() {
    override fun setLayoutId() = R.layout.list_item_sleep_night

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SleepNightViewHolder {
        return SleepNightViewHolder(inflateLayout(parent))
    }

    class SleepNightViewHolder(view: View) : BaseViewHolder<SleepNight>(view) {
        private val qualityImage: ImageView = view.findViewById(R.id.qualityImage)
        private val sleepLength: TextView = view.findViewById(R.id.sleepLength)
        private val qualityString: TextView = view.findViewById(R.id.qualityString)
        private val resource = view.context.resources
        override fun bind(item: SleepNight) {
            sleepLength.text =
                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resource)
            qualityString.text =
                convertNumericQualityToString(item.sleepQuality, resource)
            qualityImage.setImageResource(
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
}