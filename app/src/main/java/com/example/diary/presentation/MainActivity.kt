package com.example.diary.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.diary.R
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.domain.models.Event
import com.example.diary.core.navigation.Navigator
import com.example.diary.presentation.calendar.view.CalendarFragment
import com.example.diary.presentation.details.EventDetailsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main), Navigator {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        return this.findNavController(R.id.fragmentContainer).navigateUp()
    }

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 1) {
//            supportFragmentManager.popBackStack()
//            supportActionBar?.setDisplayHomeAsUpEnabled(false)
//            supportActionBar?.setDisplayShowHomeEnabled(false)
//        } else {
//            finish()
//        }
//    }

    override fun navigateToDetailsEvent(event: Event) {
//        supportFragmentManager.beginTransaction()
//            .add(binding.fragmentContainer.id, EventDetailsFragment.newInstance(event))
//            .addToBackStack(EventDetailsFragment::class.java.simpleName)
//            .commit()
    }
}
