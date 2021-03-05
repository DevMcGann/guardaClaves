package com.gsoft.argentina.guardaclaves.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.gsoft.argentina.guardaclaves.R
import com.gsoft.argentina.guardaclaves.data.local.SharedPref
import com.gsoft.argentina.guardaclaves.databinding.FragmentLoginBinding
import com.gsoft.argentina.guardaclaves.databinding.FragmentMainBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference = SharedPref(this.requireContext())

        val firstTime = sharedPreference.getFromPrefs("first")
        if (firstTime.isNullOrEmpty()){
            val mDialogView = LayoutInflater.from(this.requireContext()).inflate(R.layout.first_time, null)
            val mBuilder = AlertDialog.Builder(this.requireContext())
                .setView(mDialogView)
            val loginButton = mDialogView.findViewById<Button>(R.id.b_set_first_time)
            val textInput = mDialogView.findViewById<EditText>(R.id.t_f_pin)
            val mAlertDialog = mBuilder.show()
           loginButton.setOnClickListener {
                val pin = textInput.text.toString()
                sharedPreference.savePref("first","true")
                sharedPreference.savePref("pin", textInput.toString())
                mAlertDialog.dismiss()
            }
            mBuilder.show()


        }

        val pin = sharedPreference.getFromPrefs("pin")





        binding = FragmentLoginBinding.bind(view)

        binding.bLogin.setOnClickListener(){
            println("BOTON LOGIN")
            val pass = binding.tClave.text.toString()

            if(pass == pin){
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }

        }

    }

}