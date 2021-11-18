package com.example.diary.presentation.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.diary.R
import com.example.diary.databinding.FragmentDetailsBinding
import com.example.diary.domain.models.Event
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class EventDetailsFragment() : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val safeArgs: EventDetailsFragmentArgs by navArgs()
        val eventId = safeArgs.eventId
        Timber.d("eventId = $eventId")
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
//        setData()
    }

    private fun setData() {
//        with(binding) {
//            name.text = event.name
//            dateStart.text = formatDate(event.getStartDate())
//            dateFinish.text = formatDate(event.getFinishDate())
//            description.text = event.description
//        }
    }

    private fun formatDate(date: Date): String {
        return SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault()).format(date)
    }

//    companion object {
//        fun newInstance(event: Event): Fragment = EventDetailsFragment(event)
//    }
}
