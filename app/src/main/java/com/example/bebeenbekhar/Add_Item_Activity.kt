package com.example.bebeenbekhar


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.ActivityAddItemBinding
import com.example.bebeenbekhar.home.FragmentHome
import com.example.bebeenbekhar.home.HomeActivity

class Add_Item_Activity() : AppCompatActivity() {
    val fragmentHome = FragmentHome()
    lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgBackStack.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddItem.setOnClickListener {

            val Item_price = binding.edtPriceTools.text.toString()
            val item_title = binding.edtTitleChoose.text. toString()
            val item_State = binding.edtStateTools.text.toString()
            val itemPriceArz = binding.spinnerArz.selectedItem.toString()
            val phoneNumber = binding.edtPhoneNumber.text.toString()
            val item_Description = binding.edtDescripeTools.text.toString()
            val item_Address = binding.edtAddress.text.toString()
            val item_Address1 = binding.spinnerProvince.selectedItem.toString()
            val item_District = binding.spinnerState.selectedItem.toString()

            val newItem = SellItem(0, R.drawable.pic1,
                item_title, item_State,item_Description, Item_price, itemPriceArz, phoneNumber,
                item_Address + " " + item_Address1 , item_District , "")


            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("data", newItem)
            startActivity(intent)

        }



    }

}