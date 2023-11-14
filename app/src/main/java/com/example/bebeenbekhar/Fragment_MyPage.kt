package com.example.bebeenbekhar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.databinding.FragmentMypageBinding
import com.example.bebeenbekhar.home.HomeActivity

class Fragment_MyPage(): Fragment() {
    lateinit var binding:FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        direction()

    }

    private fun direction() {

        binding.frameProfile.setOnClickListener {

        }
        binding.frameSms.setOnClickListener {
        }
        binding.frameNotify.setOnClickListener {
        }
        binding.frameLocation.setOnClickListener {
        }
        binding.frameLang.setOnClickListener {
        }
        binding.frameSave.setOnClickListener {
        }
        binding.frameRule.setOnClickListener {
        }
    }

}