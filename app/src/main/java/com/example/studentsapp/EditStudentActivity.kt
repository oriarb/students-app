package com.example.studentsapp

import android.os.Bundle
import android.view.View
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

class EditStudentActivity : AppCompatActivity() {

    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var isCheckedBox: CheckBox? = null
    private var editButton: Button? = null
    private var saveButton: Button? = null
    private var deleteButton: Button? = null
    private var cancelButton: Button? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initHeader()
        initViews()
        initFields()
        initListeners()
    }

    private fun initHeader() {
        val headerTitle = findViewById<TextView>(R.id.header_title)
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.visibility = ImageView.VISIBLE

        headerTitle.text = "Edit Student"

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.name_edit_text)
        idEditText = findViewById(R.id.id_edit_text)
        phoneEditText = findViewById(R.id.phone_edit_text)
        addressEditText = findViewById(R.id.address_edit_text)
        isCheckedBox = findViewById(R.id.checked_button)
        editButton = findViewById(R.id.edit)
        saveButton = findViewById(R.id.save)
        deleteButton = findViewById(R.id.delete)
        cancelButton = findViewById(R.id.cancel)
    }

    private fun initFields() {
        position = intent.getIntExtra("position", -1)
        val studentName = intent.getStringExtra("studentName")
        val studentId = intent.getStringExtra("studentId")
        val studentPhone = intent.getStringExtra("studentPhone")
        val studentAddress = intent.getStringExtra("studentAddress")
        val studentChecked = intent.getBooleanExtra("studentChecked", false)
        populateFields(studentName, studentId, studentPhone, studentAddress, studentChecked)
        toggleEditing(false)
    }

    private fun initListeners() {
        editButton?.setOnClickListener {
            toggleEditing(true)
        }

        saveButton?.setOnClickListener {
            updateStudent()
            toggleEditing(false)
        }

        cancelButton?.setOnClickListener {
            toggleEditing(false)
        }

        deleteButton?.setOnClickListener {
            Model.getInstance().removeStudent(position)
            finish()
        }
    }

    private fun populateFields(name: String?, id: String?, phone: String?, address: String?, isChecked: Boolean) {
        nameEditText?.setText(name)
        idEditText?.setText(id)
        phoneEditText?.setText(phone)
        addressEditText?.setText(address)
        isCheckedBox?.isChecked = isChecked
    }

    private fun toggleEditing(enable: Boolean) {
        editButton?.visibility = if (enable) View.GONE else View.VISIBLE
        saveButton?.visibility = if (enable) View.VISIBLE else View.GONE
        cancelButton?.visibility = if (enable) View.VISIBLE else View.GONE
        deleteButton?.visibility = if (enable) View.VISIBLE else View.GONE

        nameEditText?.isEnabled = enable
        idEditText?.isEnabled = enable
        phoneEditText?.isEnabled = enable
        addressEditText?.isEnabled = enable
        isCheckedBox?.isEnabled = enable
    }

    private fun updateStudent() {
        val updatedStudent = Student(
            nameEditText?.text.toString(),
            idEditText?.text.toString(),
            phoneEditText?.text.toString(),
            addressEditText?.text.toString(),
            isCheckedBox?.isChecked ?: false
        )
        Model.getInstance().updateStudent(position, updatedStudent)
    }
}