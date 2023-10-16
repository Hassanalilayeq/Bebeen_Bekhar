package com.example.bebeenbekhar.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bebeenbekhar.ItemEvent
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.SellItemLayoutBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class HomeAdapter(private val data: ArrayList<SellItem>, val itemEvent: ItemEvent) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    lateinit var binding: SellItemLayoutBinding



    inner class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


        fun bindView(sellItem: SellItem){

            Glide.with(binding.root)
                .load(R.drawable.pic1)
                .transform(RoundedCornersTransformation(16, 4))
                .into(binding.imgItem)

            binding.txtTitle.text = sellItem.itemTitle
            binding.txtType.text = sellItem.itemState
            binding.txtPrice.text =  sellItem.itemPrice.toString() +" افغانی"
            binding.txtAddress.text = sellItem.itemAddress


            itemView.setOnClickListener {
                itemEvent.onItemClicked(sellItem)
            }
        }

    }

    fun setData(newData: ArrayList<SellItem>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    fun addSellItem(sellItem: SellItem){
        data.add(0, sellItem)
        notifyItemInserted(0)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.bindView(data[position])

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        binding = SellItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding.root)
    }
    override fun getItemCount(): Int {
        return data.size
    }



}