package com.example.healthypalate.ui.getstarted

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.healthypalate.R
import java.util.*

class DateDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {

        var context = requireActivity()
        val fragment = DateOfBirth()

        fragment.processDate(
            year,
            month,
            day
        )

        /*var frag = context.supportFragmentManager.findFragmentById(R.id.dateOfBirth) as DateOfBirth?
        frag?.processDate(
            year,
            month,
            day
        )*/
    }
}


