package stu.mai.bd_mai.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.features.collections.domain.entity.CustomerWithTotalPurchase
import stu.mai.bd_mai.features.collections.domain.entity.MaterialWithTotalUsed
import stu.mai.bd_mai.features.collections.domain.entity.ProductWithTotalSold
import stu.mai.bd_mai.features.collections.domain.entity.SupplierWithTotalMaterials

@Dao
interface CollectionDao {

    @Query("""
        SELECT MATERIALS.*, SUM(MATERIALS_IN_PRODUCT.COUNT) AS TOTAL_USED
        FROM MATERIALS
        JOIN MATERIALS_IN_PRODUCT ON MATERIALS.MATERIAL_ID = MATERIALS_IN_PRODUCT.MATERIAL_ID
        GROUP BY MATERIALS.MATERIAL_ID
        ORDER BY TOTAL_USED DESC
    """)
     fun getMaterialsWithTotalUsed(): Flow<List<MaterialWithTotalUsed>>

    @Query("""
        SELECT SUPPLIERS.*, COUNT(MATERIALS_SUPPLIERS.MATERIAL_ID) AS TOTAL_MATERIALS_PROVIDED
        FROM SUPPLIERS
        JOIN MATERIALS_SUPPLIERS ON SUPPLIERS.SUPPLIER_ID = MATERIALS_SUPPLIERS.SUPPLIER_ID
        GROUP BY SUPPLIERS.SUPPLIER_ID
        ORDER BY TOTAL_MATERIALS_PROVIDED DESC
    """)
    fun getSuppliersWithTotalMaterials(): Flow<List<SupplierWithTotalMaterials>>

    @Query("""
        SELECT CUSTOMERS.*, SUM(PRODUCTS.PRICE * PRODUCT_IN_ORDER.COUNT) AS TOTAL_PURCHASE_AMOUNT
        FROM CUSTOMERS
        JOIN ORDERS ON CUSTOMERS.CUSTOMER_ID = ORDERS.CUSTOMER_ID
        JOIN PRODUCT_IN_ORDER ON ORDERS.ORDER_ID = PRODUCT_IN_ORDER.ORDER_ID
        JOIN PRODUCTS ON PRODUCT_IN_ORDER.PRODUCT_ID = PRODUCTS.PRODUCT_ID
        GROUP BY CUSTOMERS.CUSTOMER_ID
        ORDER BY TOTAL_PURCHASE_AMOUNT DESC
        LIMIT 1
    """)
    fun getCustomerWithTotalPurchase(): Flow<List<CustomerWithTotalPurchase>>

    @Query("""
        SELECT PRODUCTS.*, SUM(PRODUCT_IN_ORDER.COUNT) AS TOTAL_SOLD
        FROM PRODUCTS
        JOIN PRODUCT_IN_ORDER ON PRODUCTS.PRODUCT_ID = PRODUCT_IN_ORDER.PRODUCT_ID
        GROUP BY PRODUCTS.PRODUCT_ID
        ORDER BY TOTAL_SOLD DESC
    """)
    fun getProductsWithTotalSold(): Flow<List<ProductWithTotalSold>>


    @Query("""
        SELECT CUSTOMERS.*, SUM(PRODUCTS.PRICE * PRODUCT_IN_ORDER.COUNT) AS TOTAL_PURCHASE_AMOUNT
        FROM CUSTOMERS
        JOIN ORDERS ON CUSTOMERS.CUSTOMER_ID = ORDERS.CUSTOMER_ID
        JOIN PRODUCT_IN_ORDER ON ORDERS.ORDER_ID = PRODUCT_IN_ORDER.ORDER_ID
        JOIN PRODUCTS ON PRODUCT_IN_ORDER.PRODUCT_ID = PRODUCTS.PRODUCT_ID
        GROUP BY CUSTOMERS.CUSTOMER_ID
        ORDER BY TOTAL_PURCHASE_AMOUNT DESC
    """)
    fun getCustomersWithTotalPurchase(): Flow<List<CustomerWithTotalPurchase>>
}





