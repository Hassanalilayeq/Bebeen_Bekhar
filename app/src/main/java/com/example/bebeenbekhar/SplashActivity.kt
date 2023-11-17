package com.example.bebeenbekhar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.bebeenbekhar.databinding.ActivitySplashBinding
import com.example.bebeenbekhar.home.HomeActivity
import com.example.bebeenbekhar.utils.BaseActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private val TIME_DELAY: Long = 2000
class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    @RequiresApi(34)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


            val auth = Firebase.auth
            if (auth.currentUser != null) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {

                val handler = Handler(Looper.getMainLooper())

                val runnable = Runnable {
                    val intent = Intent(this@SplashActivity, SignInActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
//            overridePendingTransition(R.anim.slide_to_left, R.anim.slide_from_left)
                    startActivity(intent)
                    finish()
                }
                handler.postDelayed(runnable, TIME_DELAY)
            }

    }
}