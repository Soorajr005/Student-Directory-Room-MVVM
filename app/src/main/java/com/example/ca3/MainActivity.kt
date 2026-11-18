
package com.example.ca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            StudentDatabase::class.java,
            "student_database"
        ).build()

        setContent {

            val vm: StudentViewModel = viewModel(
                factory = StudentViewModelFactory(
                    database.studentDao()
                )
            )

            var count by remember {
                mutableIntStateOf(1)
            }

            StudentScreen(
                students = vm.students.collectAsState().value,
                onAddStudent = {
                    vm.addStudent(
                        "Student $count",
                        "B.Tech CSE"
                    )
                    count++
                }
            )
        }
    }
}
