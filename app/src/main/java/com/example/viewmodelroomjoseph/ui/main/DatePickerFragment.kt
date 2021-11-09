package com.example.viewmodelroomjoseph.ui.main

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {
    private var listener: DatePickerDialog.OnDateSetListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calen = Calendar.getInstance()
        val year = calen.get(Calendar.YEAR)
        val month = calen.get(Calendar.MONTH)
        val day = calen.get(Calendar.DAY_OF_MONTH)

        return activity?.let { DatePickerDialog(it, listener, year, month, day) }!!
    }

    companion object {
        fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
            val fragment = DatePickerFragment()
            fragment.listener = listener
            return fragment
        }
    }
}