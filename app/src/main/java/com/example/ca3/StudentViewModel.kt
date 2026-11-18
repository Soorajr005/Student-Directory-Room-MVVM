package com.example.ca3
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val dao: StudentDao
) : ViewModel() {

    val students = dao.getAllStudents().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addStudent(name: String, course: String) {
        viewModelScope.launch {
            dao.insertStudent(
                StudentEntity(
                    name = name,
                    course = course
                )
            )
        }
    }
}
