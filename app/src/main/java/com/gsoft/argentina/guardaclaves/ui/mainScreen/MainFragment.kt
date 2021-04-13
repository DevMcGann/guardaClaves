package com.gsoft.argentina.guardaclaves.ui.mainScreen


import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.gsoft.argentina.guardaclaves.R
import com.gsoft.argentina.guardaclaves.data.local.AppDatabase
import com.gsoft.argentina.guardaclaves.data.local.DataSource
import com.gsoft.argentina.guardaclaves.data.model.Entidad
import com.gsoft.argentina.guardaclaves.databinding.FragmentMainBinding
import com.gsoft.argentina.guardaclaves.presentation.EntidadViewModelFactory
import com.gsoft.argentina.guardaclaves.presentation.MainViewModel
import com.gsoft.argentina.guardaclaves.repository.RepositoryImpl
import com.gsoft.argentina.guardaclaves.ui.mainScreen.adapter.Adaptador
import com.gsoft.argentina.guardaclaves.ui.mainScreen.swipe.SwipeHelper
import kotlinx.coroutines.Job
import java.util.*


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    val listaVisible = mutableListOf<Entidad>()
    val listaReal = mutableListOf<Entidad>()


    private val viewModel by viewModels<MainViewModel> {
        EntidadViewModelFactory(
            RepositoryImpl(
                DataSource(AppDatabase.getDatabase(requireContext()).dataDao())
            )
        )
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)


        viewModel.allEntidates.observe(viewLifecycleOwner, Observer{ entidades->
            listaReal.clear()
            listaReal.addAll(entidades)
            binding.rvLista.adapter = Adaptador(listaReal)
        })



        ///////////////////////////// swipe de botones ///////////////////////
        // a futuro, reemplazar esta abominación con algo más claro y escalable!!  //
        object : SwipeHelper(this.requireContext(), binding.rvLista, false) {

            override fun instantiateUnderlayButton(
                viewHolder: RecyclerView.ViewHolder?,
                underlayButtons: MutableList<UnderlayButton>?
            ) {
                // Eliminar Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                    "",
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_delete
                    ),
                    //color de fondo y color de texto
                    Color.parseColor("#FF0000"), Color.parseColor("#ffffff"))
                { pos: Int ->
                    //val item = viewModel.allEntidates.value?.get(pos)
                    var lista = mutableListOf<Entidad>()

                    if(listaVisible.isNotEmpty()){
                        lista= listaVisible
                    }else{
                        lista=listaReal
                    }

                    val item : Entidad = lista[pos]
                    val entidad = item?.let { Entidad(item.id, item.titulo, item.usuario, item.password) }
                    if (entidad != null) {
                        try {
                            viewModel.deleteEntidad(entidad)
                            binding.rvLista.adapter?.notifyItemRemoved(pos)
                            Toast.makeText(context, "Entidad Eliminada!", Toast.LENGTH_LONG).show()
                        }catch (e: Exception){
                            println("Iba a crashear chinwewencha")
                        }
                    }
                })

                //Editar Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                    "",
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_edit
                    ),
                    Color.parseColor("#127512"), Color.parseColor("#ffffff"),
                    UnderlayButtonClickListener { pos: Int ->

                        //val item = viewModel.allEntidates.value?.get(pos)
                        var lista = mutableListOf<Entidad>()

                        if(listaVisible.isNotEmpty()){
                            lista= listaVisible
                        }else{
                            lista=listaReal
                        }

                        val item : Entidad = lista[pos]

                        val entidad = item?.let { Entidad(item.id, item.titulo, item.usuario, item.password) }
                        val mDialogView = LayoutInflater.from(context!!).inflate(R.layout.dialog_agregar, null)
                        val mBuilder = AlertDialog.Builder(context!!)
                            .setView(mDialogView)
                        val yes_button = mDialogView.findViewById<Button>(R.id.b_m_agregar)
                        val no_button = mDialogView.findViewById<Button>(R.id.b_m_cancelar)
                        val nombre  = mDialogView.findViewById<EditText>(R.id.t_m_nombre)
                        val usuario = mDialogView.findViewById<EditText>(R.id.t_m_usuario)
                        val password = mDialogView.findViewById<EditText>(R.id.t_m_password)

                        if (entidad != null) {
                            nombre.text = entidad.titulo.toEditable()
                        }
                        if (entidad != null) {
                            usuario.text = entidad.usuario.toEditable()
                        }
                        if (entidad != null) {
                            password.text = entidad.password.toEditable()
                        }
                        // Toast.makeText(context, "Entidad:   $entidad", Toast.LENGTH_LONG).show()

                        val mAlertDialog = mBuilder.show()

                        //accept button click of custom layout
                        yes_button.setOnClickListener {
                            val entidadModificada =
                                entidad?.let { it1 ->
                                    Entidad(id = it1.id, titulo = nombre.text.toString(),
                                        usuario = usuario.text.toString(), password = password.text.toString())
                                }
                            Toast.makeText(context, "Modificacion Exitosa!", Toast.LENGTH_LONG).show()
                            if (entidadModificada != null) {
                                viewModel.updateEntidad(entidadModificada)
                                mAlertDialog.dismiss()
                            }
                            mAlertDialog.dismiss()
                        }

                        no_button.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }
                ))
            }
        }//BOTONES


        //menu
        binding.bAgregar.setOnClickListener() {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this.requireContext()).inflate(R.layout.dialog_agregar, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this.requireContext())
                .setView(mDialogView)
            //show dialog
            val yes_button = mDialogView.findViewById<Button>(R.id.b_m_agregar)
            val no_button = mDialogView.findViewById<Button>(R.id.b_m_cancelar)
            val nombre = mDialogView.findViewById<EditText>(R.id.t_m_nombre)
            val usuario = mDialogView.findViewById<EditText>(R.id.t_m_usuario)
            val password = mDialogView.findViewById<EditText>(R.id.t_m_password)
            val mAlertDialog = mBuilder.show()
            //accept button click of custom layout
            yes_button.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val entidad = Entidad(id = 0, titulo = nombre.text.toString(), usuario = usuario.text.toString(), password = password.text.toString())
                viewModel.saveEntidad(entidad)
                Toast.makeText(context, "Entidad Creada!", Toast.LENGTH_LONG).show()
                mAlertDialog.dismiss()
            }

            no_button.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

    } //ON CREATED


    //Buscar

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.main_menu, menu)
        var item: MenuItem = menu!!.findItem(R.id.action_search)

        if (item != null){
            var searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        listaVisible.clear()
                        var busqueda = newText.toLowerCase(Locale.getDefault())

                        for (entidad in listaReal){
                            if (entidad.titulo.toLowerCase(Locale.getDefault()).contains(busqueda)){
                                listaVisible.add(entidad)
                            }
                            binding.rvLista.adapter!!.notifyDataSetChanged()
                            binding.rvLista.adapter = Adaptador(listaVisible)
                        }
                    }else{
                        listaVisible.clear()
                        listaVisible.addAll(listaReal)
                        binding.rvLista.adapter!!.notifyDataSetChanged()
                        binding.rvLista.adapter = Adaptador(listaReal)
                    }

                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu, inflater)

        

    }


}