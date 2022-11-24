package com.example.healthypalate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.healthypalate.R
import com.example.healthypalate.ui.getstarted.GetStarted

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            val intent = Intent(this@SplashActivity, LogIn::class.java)
            startActivity(intent)
        }, 2000)

    }
}

