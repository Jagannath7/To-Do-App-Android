package com.example.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_tasks.*
import java.text.SimpleDateFormat
import java.util.*

const val DB_NAME = "todo.db"
class Tasks : AppCompatActivity(), View.OnClickListener {

    lateinit var myCalendar: Calendar

    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            DB_NAME
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)


        dateEdt.setOnClickListener(this)
        timeEdt.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dateEdt -> {
                setDateListener()
            }
            R.id.timeEdt -> {
                setTimeListener()
            }
        }
    }

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()
        timeSetListener =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, minute)
                updateTime()
            }

        val timePickerDialog = TimePickerDialog(
            this, timeSetListener,myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),false
        )
        timePickerDialog.show()
    }

    private fun setDateListener() {
        myCalendar = Calendar.getInstance()
        dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }

            val datePickerDialog = DatePickerDialog(
                this, dateSetListener, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()


        }

    private fun updateDate() {
        val myFormat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myFormat)
        dateEdt.setText(sdf.format(myCalendar.time))
    }

    private fun updateTime() {
        val myFormat = "h:mm a "
        val sdf = SimpleDateFormat(myFormat)
        timeEdt.setText(sdf.format(myCalendar.time))
    }

}
