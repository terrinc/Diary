package com.example.diary.core.di

import com.example.diary.domain.usecase.GetCalendarEventsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCalendarEventsUseCase(eventRepository = get())
    }
}
