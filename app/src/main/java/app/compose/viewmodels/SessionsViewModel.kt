package app.compose.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionsViewModel : ViewModel() {
    private val _dayCounter = MutableLiveData("")
    val dayCounter get() = _dayCounter


    fun updateCountdown(dDay: String) {
        _dayCounter.value = dDay
    }
}