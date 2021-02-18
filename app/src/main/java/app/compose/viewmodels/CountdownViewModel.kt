package app.compose.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

class CountdownViewModel : ViewModel() {
    private val _dayCounter = MutableLiveData<Long>(0)
    val dayCounter get() = _dayCounter

    private val _hourCounter = MutableLiveData<Long>(0)
    val hourCounter get() = _hourCounter

    private val _minuteCounter = MutableLiveData<Long>(0)
    val minuteCounter get() = _minuteCounter

    init {
        val dDay = Calendar.getInstance()
        dDay.set(2021, Calendar.FEBRUARY, 19, 8, 0, 0)
        viewModelScope.launch {
            while (true) {
                updateCountdown(dDay)
                delay(TimeUnit.MINUTES.toMillis(1))
            }
        }
    }

    private fun updateCountdown(dDay: Calendar) {
        val daysLeftInMillis: Long = dDay.timeInMillis - Calendar.getInstance().timeInMillis
        if (daysLeftInMillis <= 0) return

        _dayCounter.value = daysLeftInMillis / TimeUnit.DAYS.toMillis(1)
        val hoursLeftInMillis: Long = daysLeftInMillis % TimeUnit.DAYS.toMillis(1)

        _hourCounter.value = hoursLeftInMillis / TimeUnit.HOURS.toMillis(1)
        val minutesLeftInMillis = daysLeftInMillis % TimeUnit.MINUTES.toMillis(1)

        _minuteCounter.value = minutesLeftInMillis / TimeUnit.MINUTES.toMillis(1)
    }
}