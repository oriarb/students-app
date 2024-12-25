package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class StudentRecyclerViewActivity : AppCompatActivity() {

    private var students: MutableList<Student>? = null
    private var adapter: StudentsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initHeader()

        students = Model.getInstance().getStudents()
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)
        recyclerView.adapter = adapter

        val addNewStudent = findViewById<FloatingActionButton>(R.id.add_student_button)
        addNewStudent.setOnClickListener {
           val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@StudentRecyclerViewActivity, EditStudentActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("studentName", students?.get(position)?.name)
                intent.putExtra("studentId", students?.get(position)?.id)
                intent.putExtra("studentPhone", students?.get(position)?.phone)
                intent.putExtra("studentAddress", students?.get(position)?.address)
                intent.putExtra("studentChecked", students?.get(position)?.isChecked)
                startActivity(intent)
            }
        }
    }

    private fun initHeader() {
        val headerTitle = findViewById<TextView>(R.id.header_title)
        headerTitle.text = "Students List"
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }

}