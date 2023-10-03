package com.example.bebeenbekhar

import com.example.bebeenbekhar.data.SellItem

interface ItemEvent {

    fun onItemClicked(sellItem: SellItem)

}