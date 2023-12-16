package stu.mai.bd_mai.features.settings.materials

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Material
import javax.inject.Inject

@HiltViewModel
class SettingMaterialsVM @Inject constructor(val database: AppDatabase) : ViewModel() {

    fun addMaterial(name: String, dimension: String) {
        viewModelScope.launch {
            val newMaterial = Material(
                NAME = name,
                DIMENSION = dimension,
            )
            database.getMaterialDao().insertMaterial(newMaterial)
        }
    }

}