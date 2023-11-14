package com.example.bebeenbekhar.category


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.databinding.FragmentDivisionBinding
import com.example.bebeenbekhar.home.FragmentHome


class Fragment_Division: Fragment() {

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



        binding.frameEstate.setOnClickListener {
           replaceFragment(FragmentHome())
        }

        binding.frameVehicle.setOnClickListener {
            replaceFragment(FragmentHome())
        }

        binding.frameElectric.setOnClickListener {
            replaceFragment(FragmentHome())
        }

        binding.frameHouse.setOnClickListener {
            replaceFragment(FragmentHome())
        }

        binding.framePersonal.setOnClickListener {
            replaceFragment(FragmentHome())
        }

        binding.frameEntertain.setOnClickListener {
            replaceFragment(FragmentHome())
        }


    }

    fun replaceFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}