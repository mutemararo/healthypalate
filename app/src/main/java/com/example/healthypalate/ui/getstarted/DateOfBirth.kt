package com.example.healthypalate.ui.getstarted

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentDateOfBirthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class DateOfBirth : Fragment(), DatePickerDialog.OnDateSetListener {

    private var binding: FragmentDateOfBirthBinding? = null

    private lateinit var dayEt: EditText
    private lateinit var monthEt: EditText
    private lateinit var yearEt: EditText

    private var userId = FirebaseAuth.getInstance().currentUser?.uid
    private var dobRef = FirebaseDatabase.getInstance().getReference("users/$userId/date_of_birth")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val frag = FragmentDateOfBirthBinding.inflate(inflater, container, false)
        binding = frag
        return frag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            dobFrag = this@DateOfBirth

            selectDob.setOnClickListener {
                showDialog()
            }
        }
        dayEt = binding!!.dayInput
        monthEt = binding!!.monthInput
        yearEt = binding!!.yearInput

    }

    private fun showDialog() {
        /*val dateDialog = DateDialog()
        dateDialog.show(parentFragmentManager, "HelloDialog")*/
        val calendar = Calendar.getInstance()
        var dateDialog = context?.let {
            DatePickerDialog(
                it, this@DateOfBirth,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
        }
        dateDialog?.show()
    }

    fun processDate(year: Int, month: Int, day: Int){

        var yearStr = year.toString()
        var monthStr = month.toString()
        var dayStr = day.toString()

        dayEt.setText(dayStr, TextView.BufferType.SPANNABLE)
        monthEt.setText(monthStr, TextView.BufferType.SPANNABLE)
        yearEt.setText(yearStr, TextView.BufferType.SPANNABLE)

        Toast.makeText(requireActivity(), "$day/$month/$year", Toast.LENGTH_LONG).show()
    }
    fun gotoHeight(){

        if (dayEt.text.isNotBlank() &&
            monthEt.text.isNotBlank() &&
            yearEt.text.isNotBlank()){

            dobRef.setValue("${dayEt.text.toString()}/${monthEt.text.toString().trim()}/${yearEt.text.toString().trim()}")
            findNavController().navigate(R.id.action_dateOfBirth_to_height)
        }else{
            Toast.makeText(requireActivity(), "enter all fields", Toast.LENGTH_SHORT).show()
        }

    }
    fun backGender()
    {
        findNavController().navigate(R.id.action_dateOfBirth_to_gender)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        processDate(p1, p2, p3)
    }
}