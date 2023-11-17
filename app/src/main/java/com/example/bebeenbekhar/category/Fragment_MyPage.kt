package com.example.bebeenbekhar.category

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.SignInActivity
import com.example.bebeenbekhar.databinding.ChangeProfileDialogBinding
import com.example.bebeenbekhar.databinding.FragmentMypageBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Fragment_MyPage(): Fragment() {
    lateinit var binding:FragmentMypageBinding
    private lateinit var auth: FirebaseAuth
    lateinit var  imageProfile : ImageView
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        imageProfile = binding.imgProfile
        sharedPreferences = requireActivity().getSharedPreferences("profileData", Context.MODE_PRIVATE)


        binding.txtChangeProfile.setOnClickListener {
            val fullName = binding.txtName.text.toString()
            val number = binding.txtNumber.text.toString()
            val dialog = AlertDialog.Builder(context).create()
            val changeDialogBinding = ChangeProfileDialogBinding.inflate(layoutInflater)
            dialog.setView(changeDialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()
            changeDialogBinding.edtDialogName.setText(fullName)
            changeDialogBinding.edtDialogNumber.setText(number)

            changeDialogBinding.btnsubmit.setOnClickListener {
                if (changeDialogBinding.edtDialogName.text!!.isNotEmpty() &&
                    changeDialogBinding.edtDialogNumber.text!!.isNotEmpty() &&
                    changeDialogBinding.edtDialogNumber.length() == 10){
                    val nameEdit = changeDialogBinding.edtDialogName.text.toString()
                    val phoneEdit = changeDialogBinding.edtDialogNumber.text.toString()
                    val editor = sharedPreferences.edit()
                    editor.putString("name", nameEdit)
                    editor.putString("number", phoneEdit)
                    editor.apply()
                    dialog.dismiss()
                    binding.txtNumber.text = sharedPreferences.getString("number", "")
                    binding.txtName.text = sharedPreferences.getString("name", "")
                }
                else{
                    Toast.makeText(context, "لطفا نام و شماره خود را وارد کنید.", Toast.LENGTH_SHORT).show()
                }
            }
            changeDialogBinding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
        }

        val newName = sharedPreferences.getString("name", "")!!
        binding.txtName.text = newName
        val newNumber = sharedPreferences.getString("number", "")!!
        binding.txtNumber.text = newNumber

        direction()

        imageProfile.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)			//image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageProfile.setImageURI(data?.data)
    }

    private fun direction() {

        binding.frameProfile.setOnClickListener {

        }
        binding.frameSms.setOnClickListener {
        }
        binding.frameNotify.setOnClickListener {
        }
        binding.frameLocation.setOnClickListener {
        }
        binding.frameLang.setOnClickListener {
        }
        binding.frameSave.setOnClickListener {
        }
        binding.frameRule.setOnClickListener {
        }
        binding.logoutCardView.setOnClickListener {
            if (auth.currentUser != null){
                auth.signOut()
                startActivity(Intent(activity, SignInActivity::class.java))
            }
        }


    }

}