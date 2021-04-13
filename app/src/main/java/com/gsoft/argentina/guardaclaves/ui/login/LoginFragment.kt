package com.gsoft.argentina.guardaclaves.ui.login

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gsoft.argentina.guardaclaves.R
import com.gsoft.argentina.guardaclaves.data.local.SharedPref
import com.gsoft.argentina.guardaclaves.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference = SharedPref(this.requireContext())
        val pin = sharedPreference.getFromPrefs("pin")
        binding = FragmentLoginBinding.bind(view)
        binding.bLogin.setOnClickListener(){
            println("BOTON LOGIN")
            val pass = binding.tClave.text.toString()

            if(pass == pin){
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }else{
                binding.tPinIncorrecto.visibility = View.VISIBLE
            }
        }

    }

}