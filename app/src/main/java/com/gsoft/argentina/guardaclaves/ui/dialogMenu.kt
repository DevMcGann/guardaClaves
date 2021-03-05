/*
package com.gsoft.argentina.guardaclaves.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.gsoft.argentina.guardaclaves.R
import com.gsoft.argentina.guardaclaves.data.model.Entidad

class dialogMenu(ctx:Context) {
    //Inflate the dialog with custom view
    val mDialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_agregar, null)

    //AlertDialogBuilder
    val mBuilder = AlertDialog.Builder(ctx)
        .setView(mDialogView)

    //show dialog
    val yes_button = mDialogView.findViewById<Button>(R.id.b_m_agregar)
    val no_button = mDialogView.findViewById<Button>(R.id.b_m_cancelar)
    val nombre = mDialogView.findViewById<EditText>(R.id.t_m_nombre)
    val usuario = mDialogView.findViewById<EditText>(R.id.t_m_usuario)
    val password = mDialogView.findViewById<EditText>(R.id.t_m_password)
    val mAlertDialog = mBuilder.show()

    //accept button click of custom layout
    yes_button.setOnClickListener
    {
        //dismiss dialog
        mAlertDialog.dismiss()
        //get text from EditTexts of custom layout
        val entidad = Entidad(
            id = 0,
            titulo = nombre.text.toString(),
            usuario = usuario.text.toString(),
            password = password.text.toString()
        )
        viewModel.saveEntidad(entidad)
        mAlertDialog.dismiss()
    }

    no_button.setOnClickListener
    {
        mAlertDialog.dismiss()
    }

}*/
