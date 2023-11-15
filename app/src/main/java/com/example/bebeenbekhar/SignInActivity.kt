package com.example.bebeenbekhar

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.bebeenbekhar.databinding.ActivitySignInBinding
import com.example.bebeenbekhar.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : Base1Activity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.tvRegister.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvForgotPassword.setOnClickListener{
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener { signInUser() }

    }

    private fun signInUser(){
        val email = binding.etSinInEmail.text.toString()
        val password = binding.etSinInPassword.text.toString()
        if(validateForm(email, password)){
            showProgressBar()
            auth.signInWithEmailAndPassword(email, password)
              .addOnCompleteListener {
                    if (it.isSuccessful){
                        hideProgressBar()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }else{
                        showToast(this, "وارد برنامه نشد، دوباره تلاش کنید.")
                        hideProgressBar()
                    }
                }
        }
    }

    private fun validateForm(email:String,password:String):Boolean {
        return when {
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding.tilEmail.error = "یک ایمیل معتبر وارید کنید"
                false
            }
            TextUtils.isEmpty(password)->{
                binding.tilPassword.error = "پاسورد وارد کنید"
                false
            }
            else -> { true }
        }
    }

}