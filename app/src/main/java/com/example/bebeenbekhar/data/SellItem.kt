package com.example.bebeenbekhar.data

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellItem(

    var itemDistrict : Int,
    val imageUrl: Int,
    val itemType: String,
    val itemTitle: String,
    val itemState: String,
    val itemDescription: String,
    val itemPrice: String,
    val itemPriceArz : String,
    val phoneNumber: String,
    val itemAddress: String,
    val itemProvince : String,
    val sell_Item_Added_Date: String

): Parcelable
