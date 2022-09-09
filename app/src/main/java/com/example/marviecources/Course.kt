package com.example.marviecources

data class CourseResult(
    val new_courses: List<NewCourse>,
    val active_courses: List<ActiveCourse>
)

data class NewCourse(
    val id: String,
    val icon_type: String,
    val duration: String,
    val title: String,
    val question: String,
    val main_color: String
)

data class ActiveCourse(
    val id: String,
    val booking_time: String,
    val progress: String,
    val title: String,
    val main_color: String,
    val background_color_percent: String,
    val play_button_color_percent: String,
    val image: String
)