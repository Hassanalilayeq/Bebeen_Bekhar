package com.example.bebeenbekhar.home


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bebeenbekhar.ItemHomeDescriptionActivity
import com.example.bebeenbekhar.data.SellItem
import com.example.bebeenbekhar.databinding.FragmentHomeBinding
import com.example.bebeenbekhar.net.ApiService
import com.example.bebeenbekhar.utils.BASE_URL
import com.example.bebeenbekhar.utils.SEND_DATA_TO_DESCRIPTION_ACTIVITY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FragmentHome : Fragment(), HomeAdapter.ItemEvent {
    lateinit var binding: FragmentHomeBinding
    lateinit var myAdapter: HomeAdapter
    lateinit var apiService: ApiService
    private lateinit var originalData: ArrayList<SellItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        getDataFromApi()

    }


    private fun getDataFromApi() {
        apiService.getAllSellitems().enqueue(object : Callback<List<SellItem>> {
            override fun onResponse(
                call: Call<List<SellItem>>, response: Response<List<SellItem>>
            ) {
                originalData = ArrayList(response.body()!!)
                setDataToRecyclerView(originalData)
                binding.edtSearch.addTextChangedListener {editText ->

//                    val arrayList = originalData as ArrayList<SellItem>
                    if (editText!!.isNotEmpty()){
//                        val cloneList: ArrayList<SellItem> = arrayList.clone() as ArrayList<SellItem>
                        val filteredList = originalData.filter { sellitem ->
                             sellitem.itemTitle.contains(editText)
                        }
                        myAdapter.setData(ArrayList(filteredList))
                    }else{
                        myAdapter.setData(originalData)
                    }
                }
            }

            override fun onFailure(call: Call<List<SellItem>>, t: Throwable) {
                Log.v("testApi", t.message!!)
            }
        })
    }

    private fun setDataToRecyclerView(data: List<SellItem>) {
        val myData = ArrayList(data)
        myAdapter = HomeAdapter(myData.clone() as ArrayList<SellItem>, this)
        binding.recycleviewHome.adapter = myAdapter
        binding.recycleviewHome.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }


    override fun onItemClicked(sellItem: SellItem) {
        val intent = Intent(activity, ItemHomeDescriptionActivity::class.java)
        intent.putExtra(SEND_DATA_TO_DESCRIPTION_ACTIVITY, sellItem)
        startActivity(intent)

    }


}
