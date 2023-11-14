package com.example.bebeenbekhar.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.Add_Item_Fragment
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

            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(FragmentHome())

                }

                R.id.menu_list -> {
                    replaceFragment(Fragment_Division())
                }

                R.id.menu_add_item -> {
                    replaceFragment(Add_Item_Fragment())
                }

                R.id.menu_profile -> {
                    replaceFragment(Fragment_MyPage())
                }

            }

            true
        }
    }


    fun firstRun() {
        replaceFragment(FragmentHome())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_home


    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home->{
                onBackPressed()
            }
        }
        return true
    }

}