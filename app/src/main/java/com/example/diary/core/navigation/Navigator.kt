package com.example.diary.core.navigation

import com.example.diary.domain.models.Event

interface Navigator {

    fun navigateToDetailsEvent(event: Event)
}
