package com.example.bebeenbekhar.category


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bebeenbekhar.Add_Item_Fragment
import com.example.bebeenbekhar.R
import com.example.bebeenbekhar.databinding.FragmentDivisionAddItemBinding
import com.example.bebeenbekhar.utils.SENT_DATA_KEY_TITLE


class Fragment_Division_Add_Items : Fragment() {

    lateinit var binding: FragmentDivisionAddItemBinding
    lateinit var bundle: Bundle
    lateinit var fragment: Add_Item_Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDivisionAddItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.frameEstate.setOnClickListener {
            val stateTitle = binding.txtStateTitle.text.toString()

            bundle = Bundle()
            bundle.putString(SENT_DATA_KEY_TITLE, stateTitle)
            val fragment = Add_Item_Fragment()
            fragment.arguments = bundle

            replaceFragment(fragment)
        }

        binding.frameVehicle.setOnClickListener {
            val vehicleTitle = binding.txtVihicleTitle.text.toString()
            bundle = Bundle()
            bundle.putString(SENT_DATA_KEY_TITLE, vehicleTitle)
            fragment = Add_Item_Fragment()
            fragment.arguments = bundle
            replaceFragment(fragment)
        }

        binding.frameElectric.setOnClickListener {
            val electricTitle = binding.txtElectricTitle.text.toString()
            bundle = Bundle()
            bundle.putString(SENT_DATA_KEY_TITLE, electricTitle)
            fragment = Add_Item_Fragment()
            fragment.arguments = bundle
            replaceFragment(fragment)

        }

        binding.frameHouse.setOnClickListener {
            val houseTitle = binding.txtHouseToolTitle.text.toString()
            bundle = Bundle()
            bundle.putString(SENT_DATA_KEY_TITLE, houseTitle)
            fragment = Add_Item_Fragment()
            fragment.arguments = bundle

            replaceFragment(fragment)
        }

        binding.framePersonal.setOnClickListener {
            val personalTitle = binding.txtPrivateTools.text.toString()

            bundle = Bundle()
            bundle.putString(SENT_DATA_KEY_TITLE, personalTitle)
            fragment = Add_Item_Fragment()
            fragment.arguments = bundle
            replaceFragment(fragment)
        }

        binding.frameEntertain.setOnClickListener {
            val entertainTitle = binding.txtEntertaintTitle.text.toString()
            bundle = Bundle()
            bundle.putString(SENT_DATA_KEY_TITLE, entertainTitle)
            fragment = Add_Item_Fragment()
            fragment.arguments = bundle
            replaceFragment(fragment)
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainerView, fragment)
        transaction.commit()
    }

}
