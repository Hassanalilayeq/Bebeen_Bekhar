package com.example.bebeenbekhar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.bebeenbekhar.databinding.ActivitySplashBinding
import com.example.bebeenbekhar.home.HomeActivity

private val TIME_DELAY: Long = 1000
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val handler = Handler(Looper.getMainLooper())

        val runnable = Runnable {
             val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        handler.postDelayed(runnable, TIME_DELAY)
    }
}