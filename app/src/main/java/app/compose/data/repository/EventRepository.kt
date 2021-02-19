package app.compose.data.repository

import app.compose.data.models.Session
import app.compose.data.models.Speaker
import com.github.javafaker.Faker
import java.util.*
import java.util.Calendar.HOUR_OF_DAY

class EventRepository {
    fun getEventDates(): List<Calendar> {
        val day1 = Calendar.getInstance()
        day1.set(2021, Calendar.MARCH, 19, 8, 0, 0)
        val day2 = Calendar.getInstance()
        day2.set(2021, Calendar.MARCH, 20, 8, 0, 0)
        val day3 = Calendar.getInstance()
        day3.set(2021, Calendar.MARCH, 21, 8, 0, 0)
        return listOf(day1, day2, day3)
    }
}

class SessionsRepository(
    private val eventRepository: EventRepository = EventRepository(),
    private val faker: Faker = Faker()
) {
    fun getSessions(count: Int? = null): List<Session> {
        val sessions = arrayListOf<Session>()
        eventRepository.getEventDates().forEach { day ->
            listOf(8, 10, 12, 14, 16).forEach { hourOfDay ->
                day.set(HOUR_OF_DAY, hourOfDay)
                val session = Session(
                    startTime = day,
                    durationInMinutes = 90,
                    venue = faker.rickAndMorty().location(),
                    title = faker.hacker().adjective(),
                    description = faker.rickAndMorty().quote(),
                    speaker = Speaker(faker.rickAndMorty().character()),
                    banner = listOf(
                        "https://ihub.co.ke/media/cache/imager/blog_main_image/wp-content/uploads/2018/07/droidcon-banner.png",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzDCG8QFfgwd0HXvR1LnmI1AIoB-0QJN8zTw&usqp=CAU",
                        "https://ihub.co.ke/media/cache/imager/blog_main_image/wp-content/uploads/2019/07/EALUUguWkAEHX3A.jpeg"
                    ).random()
                )
                sessions.add(session)
            }
        }
        return if (count == null) sessions else sessions.sortedBy { it.startTime }.subList(0, count)
    }
}