package stu.mai.bd_mai.features.settings.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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

    fun addProduct(name: String, price: Double, description: String) {
        viewModelScope.launch {
            val newProduct = Product(
                NAME = name,
                PRICE = price,
                DESCRIPTION = description,
            )
            database.getProductDao().insertProduct(newProduct)
        }
    }

}