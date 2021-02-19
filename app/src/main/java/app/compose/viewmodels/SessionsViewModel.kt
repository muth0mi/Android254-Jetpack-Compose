package app.compose.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.compose.data.models.Session
import app.compose.data.repository.SessionsRepository
import java.util.Calendar.DAY_OF_MONTH

class SessionsViewModel(
    private val sessionsRepository: SessionsRepository = SessionsRepository()
) : ViewModel() {
    val loadingSessions = mutableStateOf(false)
    val sessions = mutableStateOf(listOf<Session>())
    val sessionDates = mutableStateOf(listOf<Int>())
    val selectedDate = mutableStateOf(0)

    init {
        getSessions()
    }

    private fun getSessions() {
        loadingSessions.value = true
        sessions.value = sessionsRepository.getSessions()
        sessionDates.value = sessions.value.map { it.startTime.get(DAY_OF_MONTH) }.toSet().sorted()
        selectDate(sessionDates.value.first())
        loadingSessions.value = false
    }

    fun selectDate(date: Int) {
        selectedDate.value = date
    }


    private val _dayCounter = MutableLiveData("")
    val dayCounter get() = _dayCounter


    fun updateCountdown(dDay: String) {
        _dayCounter.value = dDay
    }
}