package com.example.bebeenbekhar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.ActivityItemHomeDescriptionBinding
import com.example.bebeenbekhar.utils.SEND_DATA_TO_DESCRIPTION_ACTIVITY

class ItemHomeDescriptionActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemHomeDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemHomeDescriptionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val newData = intent.getParcelableExtra<SellItem>(SEND_DATA_TO_DESCRIPTION_ACTIVITY)
        if (newData != null){
            showData(newData)
        }
        else{
            Toast.makeText(this, "Error :)", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showData(showData: SellItem) {

        Glide.with(this)
            .load(R.drawable.home)
            .into(binding.imgItem)

        binding.txtTitleItems.text = showData.itemTitle
        binding.txtDescriptionResultDetails.text = showData.itemDescription
        binding.txtListType.text = showData.itemType
        binding.txtPriceResult.text = showData.itemPrice + " "+ showData.itemPriceArz
        binding.txtUsegeResult.text = showData.itemState
        binding.txtAddress.text = showData.itemAddress + " " + showData.itemProvince
        binding.txtDateIssuse.text = showData.sell_Item_Added_Date

        val phone = showData.phoneNumber
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data  = Uri.parse("tel:$phone")
            }
            startActivity(intent)


        }

    }
}