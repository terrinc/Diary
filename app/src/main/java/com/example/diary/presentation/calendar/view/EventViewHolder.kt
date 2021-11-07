package com.example.diary.presentation.calendar.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.databinding.ViewHolderEventItemBinding
import com.example.diary.domain.models.Event

class EventViewHolder(view: View, private val clickListener: (Event) -> Unit) : RecyclerView.ViewHolder(view) {
    private val binding = ViewHolderEventItemBinding.bind(itemView)

    fun bind(row: Row) {
        with(binding) {
            time.text = hourToInterval(row.hour)
            if (row.event == null) {
                name.text = ""
                root.setOnClickListener { null }
            }
            row.event?.let { event ->
                name.text = event.name
                root.setOnClickListener { clickListener(event) }
            }
        }
    }

    private fun hourToInterval(hour: Int): String = "${hour}:00 - ${hour + 1}:00"
}
