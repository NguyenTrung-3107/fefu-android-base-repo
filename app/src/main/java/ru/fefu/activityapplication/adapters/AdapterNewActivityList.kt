package ru.fefu.activityapplication.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activityapplication.models.DataOfNewActivity
import ru.fefu.activityapplication.R

class AdapterNewActivityList(private val activities: List<DataOfNewActivity>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var selected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_activity_item, parent, false)
        return NewActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewActivityViewHolder).bind(activities[position])
    }

    override fun getItemCount(): Int = 3

    inner class NewActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val type = itemView.findViewById<TextView>(R.id.new_activity_item_type)

        @SuppressLint("SetTextI18n")
        fun bind(activity: DataOfNewActivity) {
            type.text = activity.type
            itemView.isSelected = activity.isSelected

            if (itemView.isSelected) {
                itemView.setBackgroundResource(R.drawable.ic_selectec_border)
            } else {
                itemView.setBackgroundResource(R.drawable.ic_border)
            }
            itemView.setOnClickListener {
                activities[adapterPosition].isSelected = true
                if (selected != -1 && selected != adapterPosition) {
                    activities[selected].isSelected = false
                }
                selected = adapterPosition
                notifyDataSetChanged()
            }
        }
    }
}