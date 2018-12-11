package com.example.sergiotorres.pomodorocoinapp


import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_clock.*
import kotlinx.android.synthetic.main.fragment_clock.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ClockFragment : Fragment() {

    private lateinit var timer: CountDownTimer
    private var isRunning = false
    private var timerLengthSeconds = 0L
    private var timerRemainingSeconds = 0L


    val mockTags = arrayListOf("Work", "Study", "Project")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_clock, container, false)
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, mockTags)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.tagSpinner.adapter = spinnerAdapter
        tagSpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                // val item = parent.getItemAtPosition(position) as String
                val item = spinnerAdapter.getItem(position)
            }
        }

        view.playButton.setOnClickListener { v ->
            isRunning = if (isRunning) {
                pauseTimer()
                view.playButton.text = getText(R.string.RESUME)
                false
            } else {
                runTimer()
                view.playButton.text = getText(R.string.PAUSE)
                true
            }
        }

        view.resetButton.setOnClickListener { v ->
            resetTimer()
            isRunning = false
            view.playButton.text = getText(R.string.START)
        }

        return view
    }
    private fun runTimer() {

    }
    private fun pauseTimer() {

    }
    private fun resetTimer() {

    }




}
