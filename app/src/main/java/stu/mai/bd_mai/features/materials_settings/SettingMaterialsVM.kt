package stu.mai.bd_mai.features.materials_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Material

class SettingMaterialsVM(val database: AppDatabase) : ViewModel() {

    fun addMaterial(name: String, count: Int) {
        viewModelScope.launch {
            val newMaterial = Material(
                NAME = name,
                COUNT = count
            )
            database.getMaterialDao().insertMaterial(newMaterial)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return SettingMaterialsVM(database) as T
            }
        }
    }
}