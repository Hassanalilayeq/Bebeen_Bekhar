package com.example.bebeenbekhar.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.SellItemLayoutBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.util.Locale

class HomeAdapter(private var data: ArrayList<SellItem>, val itemEvent: ItemEvent) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var binding: SellItemLayoutBinding
    inner class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(sellItem: SellItem){
            // take photo of items by Glide library
            Glide.with(binding.root)
                .load(R.drawable.home)
                .transform(RoundedCornersTransformation(16, 4))
                .into(binding.imgItem)
            binding.txtTitle.text = sellItem.itemTitle
            binding.txtType.text = sellItem.itemState
            binding.txtPrice.text =  sellItem.itemPrice +" "+ sellItem.itemPriceArz
            binding.txtAddress.text = sellItem.itemAddress

            itemView.setOnClickListener {
                itemEvent.onItemClicked(sellItem)
            }
        }
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data[position]
        holder.bindView(item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        binding = SellItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding.root)
    }
    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: ArrayList<SellItem>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    interface ItemEvent {
        fun onItemClicked(sellItem: SellItem)

    }

}