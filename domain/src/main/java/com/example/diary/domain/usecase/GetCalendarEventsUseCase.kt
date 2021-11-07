package com.example.diary.domain.usecase

import com.example.diary.domain.models.CalendarEventDay
import com.example.diary.domain.models.Event
import com.example.diary.domain.repository.EventRepository
import java.util.*

class GetCalendarEventsUseCase(private val eventRepository: EventRepository) {

    private var prevDate = Date(0)
    suspend fun execute() : List<CalendarEventDay> {
        val events = eventRepository.getAllEvents().sortedBy { it.getStartDate() }
        val calendarEventDay = mutableListOf<CalendarEventDay>()

        events.forEach{ event ->
            if (isNewDay(prevDate, event.getStartDate())) {
                calendarEventDay.add(createNewDay(event))
            } else {
                calendarEventDay.last().events.add(event)
            }
            prevDate = event.getStartDate()
        }
        return calendarEventDay.toList()
    }

    private fun isNewDay(old: Date, new: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = old
        val oldDay = calendar[Calendar.DAY_OF_MONTH]
        val oldYear = calendar[Calendar.YEAR]

        calendar.time = new
        val newDay = calendar[Calendar.DAY_OF_MONTH]
        val newYear = calendar[Calendar.YEAR]

        return (newDay > oldDay) && (newYear >= oldYear)
    }

    private fun createNewDay(event: Event) : CalendarEventDay{
        val calendar = Calendar.getInstance()
        calendar.time = event.getStartDate()
        return CalendarEventDay(calendar, mutableListOf(event))
    }
}
