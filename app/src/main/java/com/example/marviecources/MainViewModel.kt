package com.example.marviecources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {
    private val _listItems = MutableStateFlow<List<ListItem>>(emptyList())
    val listItems get() = _listItems.asStateFlow()

    fun getCourse() {
        viewModelScope.launch {
            try {
                val response = repository.getCourse()

                _listItems.value = buildList(response)

            } catch (e: Exception) {
            }
        }
    }

    private fun buildList(course: CourseResult): List<ListItem> {
        val items = mutableListOf<ListItem>()

        items.add(ListItem.NewCourseItem(course.new_courses))

        items.addAll(course.active_courses.map {
            ListItem.ActiveCourseItem(it)
        })
        return items
    }
}