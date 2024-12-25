package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class AddStudentActivity : AppCompatActivity() {

    private var imageView: ImageView? = null
    private var name: EditText? = null
    private var id: EditText? = null
    private var phone: EditText? = null
    private var address: EditText? = null
    private var isChecked: Boolean? = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initHeader()

        imageView = findViewById(R.id.student_image_view)
        name = findViewById(R.id.name_edit_text)
        id = findViewById(R.id.id_edit_text)
        phone = findViewById(R.id.phone_edit_text)
        address = findViewById(R.id.address_edit_text)

        var checkBox = findViewById<CheckBox>(R.id.checked_button)
        checkBox.setOnClickListener {
            isChecked = checkBox.isChecked
        }

        var saveButton = findViewById<Button>(R.id.save)
        saveButton.setOnClickListener {
            addStudent()
            finish()
        }

        var cancelButton = findViewById<Button>(R.id.cancel)
        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun addStudent() {
        val student = Student(name?.text.toString(), id?.text.toString(), phone?.text.toString(), address?.text.toString(), isChecked!!)
        Model.getInstance().addStudent(student)
    }

    private fun initHeader() {
        val headerTitle = findViewById<TextView>(R.id.header_title)
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.visibility = ImageView.VISIBLE

        headerTitle.text = "Add Student"

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}