package com.exercise1.initdb.shared

import com.exercise1.domain.database.shared.Activity
import com.exercise1.domain.database.shared.ActivityRepository
import org.springframework.stereotype.Component

@Component
class DataToActivityService(
  private val activityRepository: ActivityRepository
) {
  fun convertAndSave(
    line: Array<String?>,
    currentActivityPosition: Int,
    mostRecentActionPosition: Int,
    eventId: Long
  ): Activity? {
    val current = line[currentActivityPosition]
    val mostRecent = line[mostRecentActionPosition]
    current ?: mostRecent ?: return null
    val activity = Activity(
      eventId,
      current,
      mostRecent
    )
    return activityRepository.save(activity)
  }
}
