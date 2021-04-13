package com.gsoft.argentina.guardaclaves.presentation

import androidx.lifecycle.*
import com.gsoft.argentina.guardaclaves.data.model.Entidad
import com.gsoft.argentina.guardaclaves.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel (private val repo : Repository) : ViewModel() {


    val allEntidates : LiveData<MutableList<Entidad>> = repo.getEntidades()

    fun getEntidad(id:Int)  = viewModelScope.launch {
        repo.getEntidad(id)
    }

    fun saveEntidad(entidad: Entidad) = viewModelScope.launch {
        repo.saveEntidad(entidad)
    }

    fun deleteEntidad(entidad: Entidad) = viewModelScope.launch {
        repo.deleteEntidad(entidad)
    }

    fun updateEntidad (entidad: Entidad){
        viewModelScope.launch {
            repo.update(entidad)
        }
    }


    fun wipeDB(){
        viewModelScope.launch {
            repo.deleteTodo()
        }
    }

}


class EntidadViewModelFactory(private val repo: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}