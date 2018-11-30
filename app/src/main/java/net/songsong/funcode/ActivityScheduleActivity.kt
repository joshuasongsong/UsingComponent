package net.songsong.funcode

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_schedule.*

class ActivityScheduleActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        setupView()
    }

    val cal = Calendar.getInstance()

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

        cal.set(Calendar.YEAR,year)
        cal.set(Calendar.MONTH,month)
        cal.set(Calendar.DATE,dayOfMonth)

        val time = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)
        TextView_DateHintText.text = time.format(cal.time)
    }

    private val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

        cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
        cal.set(Calendar.MINUTE,minute)

        val time = SimpleDateFormat("HH:mm", Locale.TAIWAN)
        TextView_TimeHint.text = time.format(cal.time)
    }

    private var dateTextViewHandler = View.OnClickListener { v ->

        println("year ${cal.get(Calendar.YEAR)} month ${cal.get(Calendar.MONTH)} date ${cal.get(cal.get(Calendar.DATE))}")
        DatePickerDialog(this,dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private var timeTextViewHandler = View.OnClickListener { v ->

        println("Hour ${cal.get(Calendar.HOUR)} Minute ${cal.get(Calendar.MINUTE)}")

        TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),true).show()
    }

    private var goButtonHandler = View.OnClickListener { v ->

        showConfirmDialog()
    }

    private fun setupView()
    {
        TextView_DateText.setOnClickListener(dateTextViewHandler)
        TextView_TimeText.setOnClickListener(timeTextViewHandler)
        Button_GO.setOnClickListener(goButtonHandler)
    }

    private fun showConfirmDialog()
    {
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN).format(cal.time)

        val builder = AlertDialog.Builder(this)

        //setup dialog builder
        builder.setTitle("Party time confirm")

        builder.setMessage(time.toString())

        builder.setPositiveButton("Confirm",{dialog, which -> println("confirm") })

        builder.setNegativeButton("Cancel",{dialog, which -> println("cancel") })

        //create dialog and show it
        val dialog = builder.create()

        dialog.show()
    }
}
