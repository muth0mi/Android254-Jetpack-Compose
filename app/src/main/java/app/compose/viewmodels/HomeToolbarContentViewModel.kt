package app.compose.viewmodels

import androidx.compose.ui.unit.Duration
import androidx.compose.ui.unit.inMilliseconds
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class HomeToolbarContentViewModel : ViewModel() {
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
                delay(Duration(minutes = 1).inMilliseconds())
            }
        }
    }

    private fun updateCountdown(dDay: Calendar) {
        val daysLeftInMillis: Long = dDay.timeInMillis - Calendar.getInstance().timeInMillis
        if (daysLeftInMillis <= 0) return

        _dayCounter.value = (daysLeftInMillis / Duration(days = 1).inMilliseconds())
        val hoursLeftInMillis: Long = daysLeftInMillis % Duration(days = 1).inMilliseconds()

        _hourCounter.value = (hoursLeftInMillis / Duration(hours = 1).inMilliseconds())
        val minutesLeftInMillis = daysLeftInMillis % Duration(hours = 1).inMilliseconds()

        _minuteCounter.value = minutesLeftInMillis / Duration(minutes = 1).inMilliseconds()
    }
}