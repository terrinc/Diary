package com.example.diary.presentation.calendar.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R
import com.example.diary.domain.models.Event
import timber.log.Timber
import java.util.*

class EventsAdapter(
    private val onEventClick: (Event) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    private var eventList: List<Event> = listOf()
    private var rows: List<Row> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_event_item, parent, false)
        return EventViewHolder(view, onEventClick)
    }

    override fun onBindViewHolder(viewHolder: EventViewHolder, position: Int) {
        viewHolder.bind(rows[position])
    }

    override fun getItemCount(): Int {
        return NUM_ROWS
    }

    private fun createBlankRow(): List<Row> {
        val rows = mutableListOf<Row>()
        for (hour in 0..NUM_ROWS) {
            rows.add(Row(hour, null))
        }
        return rows
    }

    fun bindEvents(events: List<Event>) {
        eventList = events
        rows = createBlankRow()
        events.forEach { event ->
            rows.forEach { row ->
                concatRowWithEvent(row, event)
            }
        }
        notifyDataSetChanged()
    }

    private fun concatRowWithEvent(row: Row, event: Event): Row {
        val hourStart = Calendar.getInstance()
        hourStart.timeInMillis = event.getStartDate().time
        hourStart.set(Calendar.HOUR_OF_DAY, row.hour)
        hourStart.set(Calendar.MINUTE, 0)
        hourStart.set(Calendar.SECOND, 0)
        hourStart.set(Calendar.MILLISECOND, 0)

        val hourFinish = Calendar.getInstance()
        hourFinish.timeInMillis = hourStart.timeInMillis
        hourFinish.set(Calendar.HOUR_OF_DAY, row.hour + DURATION_IN_HOURS)

        Timber.d(
            "row.hour = ${row.hour} \n" +
                "startHour = ${hourStart.time} \neventStart: ${event.getStartDate()}\n" +
                "finishHour = ${hourFinish.time} \neventFinish: ${event.getFinishDate()}\n"
        )

        if ((hourStart.time <= event.getFinishDate() && hourFinish.time >= event.getStartDate()) ||
            (hourStart.time <= event.getFinishDate() && hourFinish.time >= event.getFinishDate())) {
            row.event = event
        }
        return row
    }

    companion object {
        private const val NUM_ROWS = 24
        private const val DURATION_IN_HOURS = 1
    }
}

data class Row(val hour: Int, var event: Event?)
