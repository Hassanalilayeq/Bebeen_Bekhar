package com.example.bebeenbekhar.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bebeenbekhar.ItemEvent
import com.example.bebeenbekhar.ItemHomeDescriptionActivity
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.FragmentHomeBinding

class FragmentHome: Fragment(), ItemEvent {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayListOf<SellItem>(
            SellItem(0,R.drawable.pic1, "موتر فولدر", "نو","سالم بدون تکر","300", "افغانی","987987650", "برچی","کابل"," "),
            SellItem(0,R.drawable.pic1, "موتر ", "کارکرده","بدون خراش ","500000","افغانی", "987987650", "خیرخانه","کابل"," "),
            SellItem(0,R.drawable.pic1, "موتر bmv", " نو","رنگ سیاه ماشین متوسط","650000", "افغانی", "987987650", "شار نو","کابل"," "),
            SellItem(0,R.drawable.pic1, "موتر فولدر", "استفاده شده","سالم","309000", "افغانی", "987987650", "دارالامان","کابل"," "),

        )

        val myAdapter = HomeAdapter(data, this)
        binding.recycleviewHome.adapter = myAdapter
        binding.recycleviewHome.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)



        binding.edtSearch.addTextChangedListener {edtinputText->
            if (edtinputText!!.isNotEmpty()){
                val cloneList = data.clone() as ArrayList<SellItem>
                val fileredList = cloneList.filter {sellItem->
                    sellItem.itemTitle.contains(edtinputText)
                }
                myAdapter.setData(ArrayList(fileredList))

            }else{
                myAdapter.setData(data.clone() as ArrayList<SellItem>)
            }
        }

    }


    override fun onItemClicked(sellItem: SellItem) {
        val intent = Intent(activity, ItemHomeDescriptionActivity::class.java)
        intent.putExtra("data1",sellItem)
        startActivity(intent)
    }


}
