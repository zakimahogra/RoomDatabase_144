package com.example.roomdatabase.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdatabase.view.route.DestinasiHome
import com.example.roomdatabase.R
import com.example.roomdatabase.room.Siswa
import com.example.roomdatabase.viewmodel.HomeViewModel
import com.example.roomdatabase.viewmodel.provider.PenyediaViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_siswa)
                )
            }
        }
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemSiswa = uiStateSiswa.ListSiswa,
            onSiswaClick = navigateToItemUpdate,
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
        )

    }
}

@Composable
fun BodyHome(
    itemSiswa: List<Siswa>,
    onSiswaClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        if (itemSiswa.isEmpty()){
            Text(stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
        else{
            ListSiswa(
                itemSiswa = itemSiswa,
                onSiswaClick = { onSiswaClick(it.id) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
@Composable
fun ListSiswa(
    itemSiswa: List<Siswa>,
    onSiswaClick: (Siswa) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = Modifier) {
        items(items = itemSiswa, key = {it.id}){
                person -> DataSiswa(
            siswa = person,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .clickable{onSiswaClick(person)}
        )
        }
    }
}
