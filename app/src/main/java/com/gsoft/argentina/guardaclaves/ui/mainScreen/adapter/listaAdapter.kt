package com.gsoft.argentina.guardaclaves.ui.mainScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gsoft.argentina.guardaclaves.R
import com.gsoft.argentina.guardaclaves.core.BaseViewHolder
import com.gsoft.argentina.guardaclaves.data.model.Entidad
import com.gsoft.argentina.guardaclaves.databinding.ItemRowBinding

class Adaptador(listaDeElementos: MutableList<Entidad>) : RecyclerView.Adapter<Adaptador.EjemploViewHolder>() {


    private var lista : MutableList<Entidad> = listaDeElementos



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjemploViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent , false)
        return EjemploViewHolder(vista)
    }

    override fun onBindViewHolder(holder: EjemploViewHolder, position: Int) {
        holder.titulo.text = lista[position].titulo
        holder.usuario.text = lista[position].usuario
        holder.password.text = lista[position].password
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    inner class EjemploViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo : TextView = itemView.findViewById(R.id.t_titulo)
        val usuario : TextView = itemView.findViewById(R.id.t_user)
        val password: TextView = itemView.findViewById(R.id.t_password)

        init {
            itemView.setOnClickListener(){
                val position : Int = adapterPosition
                Toast.makeText(itemView.context, "Usuario: ${lista[position].usuario} -Password: ${lista[position].password}", Toast.LENGTH_LONG).show()
            }
        }
    }

}