package com.example.bebeenbekhar.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.Add_Item_Activity
import com.example.bebeenbekhar.category.Fragment_Division
import com.example.bebeenbekhar.Fragment_MyPage
import com.example.bebeenbekhar.ItemEvent
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.ActivityHomeBinding


class HomeActivity() : AppCompatActivity(), ItemEvent {
    lateinit var binding: ActivityHomeBinding
    lateinit var myAdapter : HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firstRun()
        binding.bottomNavigationMain.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(FragmentHome())
                }

                R.id.menu_list -> {
                    replaceFragment(Fragment_Division())
                }

                R.id.menu_add_item -> {
                    val intent = Intent(this, Add_Item_Activity::class.java)
                    startActivity(intent)
                }

                R.id.menu_profile -> {
                    replaceFragment(Fragment_MyPage())
                }

                else -> false
            }

            true
        }


        val newItem = intent.getParcelableExtra<SellItem>("data")
        if(newItem != null ) {

            val imgUrl = R.drawable.pic1
            val newItemTitle = newItem.itemTitle.toString()
            val newItemState = newItem.itemState.toString()
            val newItemDescription = newItem.itemDescription.toString()
            val newItemPrice = newItem.itemPrice.toString()
            val newItemPriceArz = newItem.itemPriceArz
            val newPhoneNumber = newItem.phoneNumber.toString()
            val newItemAddress = newItem.itemAddress.toString()
            val newItemProvince = newItem.itemProvince.toString()
            val newItem_Date_Added_Item = newItem.sell_Item_Added_Date.toString()


            val newSellItem = SellItem(
                0, imgUrl, newItemTitle, newItemState,
                newItemDescription, newItemPrice, newItemPriceArz ,newPhoneNumber, newItemAddress,
                newItemProvince, newItem_Date_Added_Item
            )

            val myAdapter = HomeAdapter(arrayListOf(newSellItem), this)
            myAdapter.addSellItem(newSellItem)
        }
    }


    fun firstRun() {
        replaceFragment(FragmentHome())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_home


    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onItemClicked(sellItem: SellItem) {

    }


}