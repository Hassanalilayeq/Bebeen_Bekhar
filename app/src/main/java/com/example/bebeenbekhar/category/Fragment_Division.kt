package com.example.bebeenbekhar.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.databinding.FragmentDivisionBinding
import com.example.bebeenbekhar.home.HomeActivity

class Fragment_Division(): Fragment() {

    lateinit var binding: FragmentDivisionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDivisionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeFragment()

    }

    fun changeFragment(){

        binding.frameEstate.setOnClickListener {
            replaceFragment(Fragment_Estate())
        }

        binding.frameVehicle.setOnClickListener {
            replaceFragment(Fragment_vehicle())
        }

        binding.frameElectric.setOnClickListener {
            replaceFragment(Fragment_Electric())
        }

        binding.frameHouse.setOnClickListener {
            replaceFragment(Fragment_House_Tools())
        }

        binding.framePersonal.setOnClickListener {
            replaceFragment(Fragment_Personal_Devices())
        }

        binding.frameEntertain.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.frameDire.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun replaceFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, fragment)
        transaction.commit()
    }

}