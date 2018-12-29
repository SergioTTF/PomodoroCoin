package com.example.sergiotorres.pomodorocoinapp


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.support.v4.app.Fragment
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private var tagSelected = ""
    private var isRunning = false
    private var reseted = true
    private var timerLength = 1500000L
    private var remainingTime = 0L
    private var notificationManager:NotificationManager? = null
    private var notificationIntent:Intent? = null
    private var contentIntent:PendingIntent? = null
    companion object {
        private val NOTIFICATION_CHANNEL_ID = "Timer_Channel"
    }


    val mockTags = arrayListOf("Work", "Study", "Project")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_clock, container, false)
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, mockTags)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.tagSpinner.adapter = spinnerAdapter
        view.tagSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                tagSelected = spinnerAdapter.getItem(position)

            }
        }
        view.radioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.PomodoroOption -> timerLength = 1500000L
                R.id.BreakOption -> timerLength = 600000L
            }
            resetTimer()
        }

        view.playButton.setOnClickListener { v ->
            isRunning = if (isRunning) {
                pauseTimer()
                view.playButton.text = getText(R.string.RESUME)
                false
            } else if (reseted) {
                runTimer()
                view.playButton.text = getText(R.string.PAUSE)
                changeTimerButton(true)
                reseted = false
                true
            } else {
                resumeTimer()
                view.playButton.text = getText(R.string.PAUSE)
                true
            }
        }

        view.resetButton.setOnClickListener { v ->
            resetTimer()
            isRunning = false
            reseted = true
            view.playButton.text = getText(R.string.START)
            changeTimerButton(false)
        }

        view.timerText.text = formatTime(timerLength)

        this.notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        this.notificationIntent = Intent(context, MainActivity::class.java)
        this.contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)
        createNotifyChannel()
        return view
    }
    private fun runTimer() {
        timer = getTimer(timerLength,1000).start()
    }
    private fun pauseTimer() {
        timer.cancel()
    }
    private fun resetTimer() {
        if (isRunning)
            timer.cancel()
        remainingTime = timerLength
        timerText.text = formatTime(timerLength)
    }
    private fun resumeTimer() {
        timer = getTimer(remainingTime, 1000).start()
        timerText.text = formatTime(timerLength)
    }

    private fun getTimer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                remainingTime = millisUntilFinished
                val timeRemaining = formatTime(millisUntilFinished)
                timerText.text = timeRemaining
            }

            override fun onFinish() {
                remainingTime = timerLength
                changeTimerButton(false)
                timerText.text = "00:00"
                playButton.text = getText(R.string.START)
                val notification = NotificationCompat.Builder(context!!, NOTIFICATION_CHANNEL_ID)
                        .setContentTitle("Pomodoro Done!")
                        .setContentText("completed a session on $tagSelected")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                        .build()
                notificationManager?.notify(1, notification)
                val vibrator: Vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(500)
            }
        }
    }

    private fun formatTime(timeInMillis:Long):String {
        val minutes:Any = if (timeInMillis >=600000) {
            timeInMillis/60000
        } else {
            "0" + (timeInMillis/60000).toInt()
        }
        val seconds:Any = if ((timeInMillis % 60000)/1000 > 9) {
            (timeInMillis % 60000)/1000
        } else {
            "0" + (timeInMillis % 60000)/1000
        }
        return "$minutes:$seconds"
    }
    private fun changeTimerButton(active:Boolean) {
        if (active) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                playButton.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.selected_rounded_button))
            } else {
                playButton.background = context!!.resources.getDrawable(R.drawable.selected_rounded_button)
            }
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                playButton.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.roundedbutton))
            } else {
                playButton.background = context!!.resources.getDrawable(R.drawable.roundedbutton)
            }
        }

    }

    private fun createNotifyChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O &&
                notificationManager?.getNotificationChannel(NOTIFICATION_CHANNEL_ID)==null) {
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "timer", NotificationManager.IMPORTANCE_LOW)
            channel.apply {
                description = "desc"
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }




}
