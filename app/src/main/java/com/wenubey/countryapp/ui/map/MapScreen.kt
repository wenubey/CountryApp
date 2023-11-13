package com.wenubey.countryapp.ui.map

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wenubey.countryapp.ui.country.CountryViewModel
import com.wenubey.countryapp.ui.country.list.CountryListEvent
import com.wenubey.countryapp.ui.map.components.GoogleMaps
import com.wenubey.countryapp.ui.map.components.MapScreenTopBar
import com.wenubey.countryapp.utils.Constants.TAG
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {

    val countryViewModel: CountryViewModel = getViewModel()
    val isSearchBarActive = remember {
        mutableStateOf(false)
    }
    Scaffold { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            MapScreenTopBar()
            Spacer(modifier = Modifier.height(4.dp))

            SearchBar(
                query = countryViewModel.searchQuery.value,
                onQueryChange = {
                    countryViewModel.onEvent(CountryListEvent.OnSearchQueryChange(it))
                },
                onSearch = {
                    countryViewModel.onEvent(CountryListEvent.OnSearchQueryChange(it))
                },
                active = isSearchBarActive.value,
                onActiveChange = { newValue ->
                    isSearchBarActive.value = newValue
                },
                placeholder = { Text(text = "Hello World") },
                modifier = Modifier.padding(4.dp)
            ) {
                countryViewModel.countryListDataState.countries?.let { countries ->
                    Log.i(TAG, "MapScreen: data: ${countries.first().countryCommonName}")
                    LazyColumn {
                        items(countries) { item ->
                            Card(modifier = Modifier.padding(4.dp)){
                                Column {
                                    Text(text = item.countryCommonName ?: "")
                                    Text(text = item.region ?: "")
                                    Text(text = item.subRegion ?: "")
                                }
                            }
                        }
                    }

                }
            }
            GoogleMaps(onMapClick = {})
        }

    }
}