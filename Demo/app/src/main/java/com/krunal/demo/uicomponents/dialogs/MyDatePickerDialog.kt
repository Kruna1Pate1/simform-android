package com.krunal.demo.uicomponents.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class MyDatePickerDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mCalendar: Calendar = Calendar.getInstance()
        val year = mCalendar.get(Calendar.YEAR)
        val month = mCalendar.get(Calendar.MONTH)
        val dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), null, year, month, dayOfMonth).apply {
            datePicker.maxDate = Calendar.getInstance().timeInMillis
        }
    }
}