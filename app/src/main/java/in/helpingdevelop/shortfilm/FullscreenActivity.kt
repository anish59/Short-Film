package com.example.programmer.realapp

import `in`.helpingdevelop.shortfilm.R
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.framgia.android.emulator.EmulatorDetector
import com.halilibo.bettervideoplayer.BetterVideoPlayer
import java.io.BufferedReader
import java.io.InputStreamReader
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.view.Menu
import android.view.Window


class FullscreenActivity : AppCompatActivity()  {
    private val mHideHandler = Handler()
    lateinit var mBetterVideoPlayer: BetterVideoPlayer
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mBetterVideoPlayer.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        val actionBar = supportActionBar
        actionBar?.show()
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { view, motionEvent ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_full_screen)
        checkWith(true)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val mIntent = intent
        val current_position = mIntent.getIntExtra("current_position", 0)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mVisible = true
        mBetterVideoPlayer = findViewById(R.id.bvp)!!
        mBetterVideoPlayer.setSource(Uri.parse("https://shopkingapp.info/images/teaser.mp4"))
        mBetterVideoPlayer.showControls()
        mBetterVideoPlayer.enableSwipeGestures()
        mBetterVideoPlayer.enableSwipeGestures(this@FullscreenActivity.window)
        mBetterVideoPlayer.setAutoPlay(true)
        mBetterVideoPlayer.seekTo(current_position)
        mBetterVideoPlayer.hideToolbar()
//        mBetterVideoPlayer.start()
        // Set up the user interaction to manually show or hide the system UI.
        /*mBetterVideoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });*/

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        val actionBar = supportActionBar
        actionBar?.hide()
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @SuppressLint("InlinedApi")
    private fun show() {
        // Show the system bar
        mBetterVideoPlayer.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    private fun checkWith(telephony: Boolean) {
        EmulatorDetector.with(this)
                .setCheckTelephony(telephony)
                .addPackageName("com.bluestacks")
                .setDebug(true)
                .detect { isEmulator ->
                    runOnUiThread {
                        if (radioVersion() == "") {
                            createDialog()
                        } else if (isEmulator) {
                            createDialog()
                        } else {
                            if (getCpuTemp().toDouble() == 0.0) {
                                Toast.makeText(applicationContext,
                                        "This is an emulator.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
    }

    fun getCpuTemp(): Float {
        val process: Process
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp")
            process.waitFor()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val line = reader.readLine()
            return java.lang.Float.parseFloat(line) / 1000.0f
        } catch (e: Exception) {
            e.printStackTrace()
            return 0.0f
        }

    }


    fun createDialog() {
        val builder = AlertDialog.Builder(this@FullscreenActivity)
        builder.setMessage("This app doesn't support emulators.")
        builder.setPositiveButton("OK") { dialog, which ->
            finish()
            System.exit(0)
        }
        builder.setCancelable(false)
        builder.show()
    }

    fun getsecCpuTemp(): Float {
        val process: Process
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp")
            process.waitFor()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val line = reader.readLine()
            return java.lang.Float.parseFloat(line) / 1000.0f
        } catch (e: Exception) {
            e.printStackTrace()
            return 0.0f
        }

    }

    fun radioVersion(): String {
        return Build.getRadioVersion()
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [.AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [.AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }

    public override fun onPause() {
        super.onPause()
        mBetterVideoPlayer.pause()
    }

}