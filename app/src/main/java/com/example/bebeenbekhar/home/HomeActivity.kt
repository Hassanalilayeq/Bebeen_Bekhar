package com.example.bebeenbekhar.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.Add_Item_Activity
import com.example.bebeenbekhar.category.Fragment_Division
import com.example.bebeenbekhar.Fragment_MyPage
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.databinding.ActivityHomeBinding


class HomeActivity() : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstRun()
        binding.bottomNavigationMain.setOnItemSelectedListener {

            when(it.itemId){
                R.id.menu_home ->{replaceFragment(FragmentHome())}
                R.id.menu_list ->{ replaceFragment(Fragment_Division()) }
                R.id.menu_add_item -> {
                    val intent = Intent(this, Add_Item_Activity::class.java)
                    startActivity(intent)
                }
                R.id.menu_profile -> { replaceFragment(Fragment_MyPage()) }
                else -> false
            }

            true
        }

    }

    fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, fragment)
        transaction.commit()
    }

    fun firstRun(){
        replaceFragment(FragmentHome())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_home
    }


}