package com.example.bebeenbekhar


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.bebeenbekhar.databinding.ActivityAddItemBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Add_Item_Activity : AppCompatActivity() {
    lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textField = binding.txtIputPriceLabel
        val items = listOf("افغانی", "توافقی")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        val textFieldAddress = binding.txtInputStateAddress
        val itemOfAddress = listOf("اول", "دوم", "سوم", "چهارم", "پنجم", "ششم",
            "هفتم", "هشتم", "نهم", "دهم", "یازدهم", "دوازدهم", "سیزدهم", "چهاردهم","پانزدهم",
            "شانزدهم", "هفده هم", "هژدهم", "نزدهم", "بیستم", "بیست یکم", "بیست دوم")
        val addressAdapter = ArrayAdapter(this, R.layout.list_item, itemOfAddress)
        (textFieldAddress.editText as? AutoCompleteTextView)?.setAdapter(addressAdapter)


    }
}