package stu.mai.bd_mai.features.products_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Material
import stu.mai.bd_mai.database.entities.Product
import javax.inject.Inject

@HiltViewModel
class SettingProductsVM @Inject constructor(val database: AppDatabase) : ViewModel() {


    // Получить список материалов из базы данных
    fun getMaterials(): Flow<List<Material>> {
        return database.getMaterialDao().getAllMaterials()
    }

    fun addProduct(name: String, price: Double, description: String, materialId: Int) {
        viewModelScope.launch {
            val newProduct = Product(
                NAME = name,
                PRICE = price,
                DESCRIPTION = description,
                MATERIAL_ID = materialId
            )
            database.getProductDao().insertProduct(newProduct)
        }
    }

//        companion object {
//        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
//                return SettingProductsVM(database) as T
//            }
//        }
//    }
}