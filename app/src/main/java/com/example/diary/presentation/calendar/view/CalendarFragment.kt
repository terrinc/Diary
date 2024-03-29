package com.example.diary.presentation.calendar.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.diary.R
import com.example.diary.databinding.FragmentCalendarBinding
import com.example.diary.core.navigation.Navigator
import com.example.diary.model.CalendarDayEvents
import com.example.diary.presentation.calendar.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException
import java.util.*


class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private val binding by viewBinding(FragmentCalendarBinding::bind)

    private val calendarViewModel by viewModel<CalendarViewModel>()

    private val eventsAdapter = EventsAdapter { event -> navigator.navigateToDetailsEvent(event) }

    private lateinit var navigator: Navigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Navigator) {
            navigator = context
        } else {
            throw IllegalStateException("Activity must implement Navigator")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            calendarViewModel.loadEvents()

            initRecycler()
        }
        calendarViewModel.allEvents.observe(this.viewLifecycleOwner) {
            initCalendar(it)
        }
        calendarViewModel.dayEvents.observe(this.viewLifecycleOwner) {
            if (it == null) {
                eventsAdapter.bindEvents(emptyList())
            } else {
                eventsAdapter.bindEvents(it.events)
            }
        }
    }

    private fun initCalendar(events: List<CalendarDayEvents>) {
        val currentDate: Calendar = Calendar.getInstance()
        binding.calendar.setDate(currentDate)
        binding.calendar.setEvents(events)
        binding.calendar.setOnDayClickListener { calendarViewModel.selectDay(it.calendar) }
        calendarViewModel.selectDay(currentDate)
    }

    private fun initRecycler() {
        binding.recycler.adapter = eventsAdapter
    }

    companion object {
        fun newInstance(): Fragment = CalendarFragment()
    }
}
