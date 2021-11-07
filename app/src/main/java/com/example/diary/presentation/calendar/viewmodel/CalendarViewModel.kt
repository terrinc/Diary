package com.example.diary.presentation.calendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.domain.usecase.GetCalendarEventsUseCase
import com.example.diary.model.CalendarDayEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CalendarViewModel(private val getCalendarEventsUseCase: GetCalendarEventsUseCase) : ViewModel() {

    private val eventsMutable = MutableLiveData<List<CalendarDayEvents>>(emptyList())
    val allEvents: LiveData<List<CalendarDayEvents>> get() = eventsMutable

    private val dayEventsMutable = MutableLiveData<CalendarDayEvents?>()
    val dayEvents: LiveData<CalendarDayEvents?> get() = dayEventsMutable

    fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            eventsMutable.postValue(
                getCalendarEventsUseCase.execute().map { CalendarDayEvents(it.date, it.events) })
        }
    }

    fun selectDay(date: Calendar) {
        dayEventsMutable.postValue(eventsMutable.value?.find { date == it.date })
    }
}
