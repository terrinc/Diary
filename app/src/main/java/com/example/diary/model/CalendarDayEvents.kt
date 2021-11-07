package com.example.diary.model

import com.applandeo.materialcalendarview.EventDay
import com.example.diary.R
import com.example.diary.domain.models.Event
import java.util.*

class CalendarDayEvents(val date: Calendar, val events: MutableList<Event>): EventDay(date, R.drawable.ic_event)
