package com.exercise1.select.api

enum class SelectQueryEnum {
  TOTAL_REQUESTS_SPECIFIED_RANGE,
  TOTAL_REQUESTS_PER_DAY_AND_TIME_RANGE,
  MOST_COMMON_SERVICE_REQUEST_PER_TYPE_AND_TIME_RANGE,
  AVERAGE_COMPLETION_TIME_PER_SERVICE_REQUEST_AND_TIME_RANGE,
  MOST_COMMON_REQUEST_IN_SPECIFIED_GPS_COORDINATES,
  TOP_FIVE_SSA_PER_DAY_FOR_SPECIFIED_TIME_RANGE,
  LICENCE_PLATES_ABANDONED_MORE_THAN_ONCE,
  SECOND_MOST_COMMON_COLOR_ABANDONED_VEHICLES,
  NUMBER_OF_PREMISES_BAITED_LESS_THAN,
  NUMBER_OF_PREMISES_WITH_GARBAGE_LESS_THAN,
  NUMBER_OF_PREMISES_WITH_RATS_LESS_THAN,
  POLICE_DISTRICTS_HANDLED_MORE_THAN_ONE_POT_HOLES_AND_RODENT_BAITING,
  EVENTS_IN_SPECIFIED_STREET,
  EVENTS_IN_SPECIFIED_ZIP_CODE;
}
