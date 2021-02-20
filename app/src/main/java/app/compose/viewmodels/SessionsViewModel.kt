package app.compose.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import app.compose.data.models.Session
import app.compose.data.repository.SessionsRepository
import java.util.Calendar.DAY_OF_MONTH

class SessionsViewModel(
    private val sessionsRepository: SessionsRepository = SessionsRepository()
) : ViewModel() {


    val sessionDates = mutableStateOf(listOf<Int>())
    val selectedDate = mutableStateOf(0)

    fun selectDate(date: Int) {
        selectedDate.value = date
        daySessions.value = sessions.value.filter { it.startTime.get(DAY_OF_MONTH) == date }
    }


    val loadingSessions = mutableStateOf(false)
    val sessions = mutableStateOf(listOf<Session>())
    val daySessions = mutableStateOf(listOf<Session>())

    private fun getSessions(count: Int? = null) {
        loadingSessions.value = true
        sessions.value = sessionsRepository.getSessions(count)
        sessionDates.value = sessions.value.map { it.startTime.get(DAY_OF_MONTH) }.toSet().sorted()
        selectDate(sessionDates.value.first())
        loadingSessions.value = false
    }

    init {
        getSessions()
    }
}