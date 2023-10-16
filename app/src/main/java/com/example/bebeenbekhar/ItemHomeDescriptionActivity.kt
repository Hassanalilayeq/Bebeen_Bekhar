package com.example.bebeenbekhar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.ActivityItemHomeDescriptionBinding

class ItemHomeDescriptionActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemHomeDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemHomeDescriptionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val newData = intent.getParcelableExtra<SellItem>("data1")
        if (newData != null){
            showData(newData)
        }
    }

    private fun showData(Data: SellItem) {

        Glide.with(this)
            .load(Data.imageUrl)
            .into(binding.imgItem)

        binding.txtTitleDetails.text = Data.itemTitle
        binding.txtDescriptionResultDetails.text = Data.itemDescription
        binding.txtPriceResultDetails.text = Data.itemPrice.toString()
        binding.txtPriceArzResultDetails.text = Data.itemPriceArz
        binding.txtUsegeResultDetails.text = Data.itemState
        binding.txtAddressDetails.text = Data.itemAddress + " " + Data.itemProvince

    }
}