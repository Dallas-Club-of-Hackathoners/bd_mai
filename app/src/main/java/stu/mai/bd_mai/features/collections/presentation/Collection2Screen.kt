package stu.mai.bd_mai.features.collections.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import stu.mai.bd_mai.features.collections.domain.entity.SupplierWithTotalMaterials

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Collection2Screen(
    onNavigateBack: () -> Unit,
    viewModel: Collection2VM = hiltViewModel(),
) {

    val suppliersWithTotalMaterials = viewModel.getSupplierWithTotalMaterials().collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty title to use custom title in the body */ },
                colors = TopAppBarDefaults.topAppBarColors( Color.Cyan),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        // Отобразите содержимое экрана
        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(40.dp))
            Text("Suppliers Collection", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            SupplierListContent(suppliersWithTotalMaterials)
        }
    }
}

@Composable
fun SupplierListContent(suppliers: List<SupplierWithTotalMaterials>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        if (suppliers.isEmpty()) {
            item {
                Text("No suppliers available", modifier = Modifier.padding(16.dp))
            }
        } else {
            items(suppliers) { supplier ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Supplier ID: ${supplier.supplierId}")
                        Text("Supplier Name: ${supplier.supplierName}")
                        Text(text = "Supplier Phone: ${supplier.supplierPhone}")
                        Text(text = "Supplier Email: ${supplier.supplierEmail}")
                        Text("Total Materials Provided: ${supplier.totalMaterialsProvided}")
                    }
                }
            }
        }
    }
}
