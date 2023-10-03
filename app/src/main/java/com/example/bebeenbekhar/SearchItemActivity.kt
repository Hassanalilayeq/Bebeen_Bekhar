package com.example.bebeenbekhar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import com.example.bebeenbekhar.databinding.ActivitySearchItemBinding

class SearchItemActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textFieldcitySelection = binding.txtInputCitySelection

        val items = listOf("همه ولایات", "کابل", "هرات", "قندهار", "بامیان", "غزنی", "مزار شریف")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (textFieldcitySelection.editText as? AutoCompleteTextView)?.setAdapter(adapter)



    }
}