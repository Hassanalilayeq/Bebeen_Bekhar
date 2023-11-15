package com.example.bebeenbekhar

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.bebeenbekhar.databinding.ActivitySignUpBinding
import com.example.bebeenbekhar.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : Base1Activity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.tvLoginPage.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        binding.btnSignUp.setOnClickListener { registerUser() }
    }

    private fun registerUser(){
        val name = binding.etSinUpName.text.toString()
        val email = binding.etSinUpEmail.text.toString()
        val password = binding.etSinUpPassword.text.toString()
        if(validateForm(name, email, password)){
            showProgressBar()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        showToast(this, "User Id created successfully")
                        hideProgressBar()
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    }else{
                        showToast(this, "حساب شما ساخته نشد، دوباره تلاش کنید.")
                        hideProgressBar()
                    }
                }
        }
    }

    private fun validateForm(name:String, email:String,password:String):Boolean {
        return when {
            TextUtils.isEmpty(name)->{
                binding.tilName.error = "نام و نام خانوادگی خود را وارد کنید."
                false
            }
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding.tilEmail.error = "ایمیل معتبر وارد کنید."
                false
            }
            TextUtils.isEmpty(password)->{
                binding.tilPassword.error = "پاسورد خود را وارد کنید."
                false
            }
            else -> { true }
        }
    }

}