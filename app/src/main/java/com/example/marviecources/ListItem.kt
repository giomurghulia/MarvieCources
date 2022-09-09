package com.example.marviecources

sealed class ListItem(val viewType: ViewType) {
    enum class ViewType {
        NEW_COURSE,
        ACTIVE_COURSE
    }

    data class NewCourseItem(
        val newCourse: List<NewCourse>
    ) : ListItem(ViewType.NEW_COURSE)

    data class ActiveCourseItem(
        val activeCourse: ActiveCourse
    ) : ListItem(ViewType.ACTIVE_COURSE)
}