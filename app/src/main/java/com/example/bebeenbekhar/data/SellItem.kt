package com.example.bebeenbekhar.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellItem(

    val sellItemId: Int = 0,
    val imageUrl: Int,
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
