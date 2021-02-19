package app.compose.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.compose.data.repository.EventRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class CountdownViewModel(eventRepository: EventRepository = EventRepository()) : ViewModel() {
    val dayCounter = mutableStateOf<Long>(0)
    val hourCounter = mutableStateOf<Long>(0)
    val minuteCounter = mutableStateOf<Long>(0)

    init {
        val firstDay = eventRepository.getEventDates().minByOrNull { it.timeInMillis }!!
        viewModelScope.launch {
            while (true) {
                updateCountdown(firstDay)
                delay(TimeUnit.MINUTES.toMillis(1))
            }
        }
    }

    private fun updateCountdown(dDay: Calendar) {
        val daysLeftInMillis: Long = dDay.timeInMillis - Calendar.getInstance().timeInMillis
        if (daysLeftInMillis <= 0) return

        dayCounter.value = daysLeftInMillis / TimeUnit.DAYS.toMillis(1)
        val hoursLeftInMillis: Long = daysLeftInMillis % TimeUnit.DAYS.toMillis(1)

        hourCounter.value = hoursLeftInMillis / TimeUnit.HOURS.toMillis(1)
        val minutesLeftInMillis = daysLeftInMillis % TimeUnit.HOURS.toMillis(1)

        minuteCounter.value = minutesLeftInMillis / TimeUnit.MINUTES.toMillis(1)
    }
}