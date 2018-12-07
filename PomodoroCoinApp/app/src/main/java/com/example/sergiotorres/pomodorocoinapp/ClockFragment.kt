package com.example.sergiotorres.pomodorocoinapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_clock.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ClockFragment : Fragment() {
    val mockTags = arrayListOf("Work", "Study", "Project")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, mockTags)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tagSpinner.adapter = spinnerAdapter
        return inflater.inflate(R.layout.fragment_clock, container, false)
    }


}
