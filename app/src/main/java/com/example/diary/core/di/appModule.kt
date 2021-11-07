package com.example.diary.core.di

import com.example.diary.presentation.calendar.viewmodel.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel {
        CalendarViewModel(getCalendarEventsUseCase = get())
    }
}
