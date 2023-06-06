package com.example.android.trackmysleepquality.sleeptracker

<<<<<<< Updated upstream
import android.graphics.Color
import android.view.LayoutInflater
=======
import android.view.View
>>>>>>> Stashed changes
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight

<<<<<<< Updated upstream
class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val night = data[position]
        holder.textView.text = night.sleepQuality.toString()
        if (night.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)
        } else {
            holder.textView.setTextColor(Color.BLACK)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
=======
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
>>>>>>> Stashed changes
    }
}
<<<<<<< Updated upstream

class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

=======
>>>>>>> Stashed changes
