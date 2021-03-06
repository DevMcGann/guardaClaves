package com.gsoft.argentina.guardaclaves.ui.firstTime

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gsoft.argentina.guardaclaves.R
import com.gsoft.argentina.guardaclaves.data.local.SharedPref
import com.gsoft.argentina.guardaclaves.databinding.FragmentFirstTimeBinding


class FirstTimeFragment : Fragment(R.layout.fragment_first_time){

    private lateinit var binding: FragmentFirstTimeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference = SharedPref(this.requireContext())

        val firstTime = sharedPreference.getFromPrefs("first")
        binding = FragmentFirstTimeBinding.bind(view)

        if (firstTime.isNullOrEmpty()){
            sharedPreference.savePref("first", "true")

        }else{
            findNavController().navigate(R.id.action_firstTimeFragment_to_loginFragment)
        }

        binding.bFirstSet.setOnClickListener(){
            val pinText = binding.tFirstPin.text.toString()
            if(pinText !== null && pinText.length > 3){
                sharedPreference.savePref("pin", pinText)
                findNavController().navigate(R.id.action_firstTimeFragment_to_loginFragment)
            }
        }
    }
}