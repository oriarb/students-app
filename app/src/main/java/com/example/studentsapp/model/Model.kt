package com.example.studentsapp.model

import android.util.Log

class Model private constructor() {
    private val students: MutableList<Student> = mutableListOf()

    fun addStudent(student: Student) {
        students.add(student)
        Log.d("Student", "Student added: $student")
    }

    fun removeStudent(position: Int) {
        students.removeAt(position)
        Log.d("Student", "Student removed at position: $position")
    }

    fun updateStudent(position: Int, student: Student) {
        students[position] = student
        Log.d("Student", "Student updated at position: $position")
    }

    fun getStudents(): MutableList<Student> {
        return students
    }

    companion object {
        private var instance: Model? = null

        fun getInstance(): Model {
            if (instance == null) {
                instance = Model()
            }
            return instance!!
        }
    }
}