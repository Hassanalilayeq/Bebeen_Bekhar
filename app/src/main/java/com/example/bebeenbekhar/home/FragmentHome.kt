package com.example.bebeenbekhar.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bebeenbekhar.ItemEvent
import com.example.bebeenbekhar.ItemHomeDescriptionActivity
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.SearchItemActivity
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.FragmentHomeBinding

class FragmentHome:Fragment(), ItemEvent {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arrayListOf<SellItem>(
            SellItem(R.drawable.pic1.toString(), "موتر فولدر", "در حد نو",300,"یک ساعت پیش کابل"),
            SellItem(R.drawable.pic1.toString(), "ساعت رولیکس", "کار کرده",4000,"یک ساعت پیش کابل"),
            SellItem(R.drawable.pic1.toString(), "کمپیوتر", " نو",30000,"یک ساعت پیش کابل"),
            SellItem(R.drawable.pic1.toString(), "موتر تودی", "در حد نو",250000,"یک ساعت پیش کابل"),
            SellItem(R.drawable.pic1.toString(), "موتر فولدر", "در حد نو",300000,"یک ساعت پیش کابل"),
            SellItem(R.drawable.pic1.toString(), "موتر فولدر", "در حد نو",300,"یک ساعت پیش کابل"),
            SellItem(R.drawable.pic1.toString(), "موتر فولدر", "در حد نو",300,"یک ساعت پیش کابل")
        )

        val myAdapter = HomeAdapter(data, this)
        binding.recycleviewHome.adapter = myAdapter
        binding.recycleviewHome.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        binding.imgSearch.setOnClickListener {
            val intent = Intent(context, SearchItemActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClicked(sellItem: SellItem) {
        val intent = Intent(activity, ItemHomeDescriptionActivity::class.java)
        startActivity(intent)
    }


}