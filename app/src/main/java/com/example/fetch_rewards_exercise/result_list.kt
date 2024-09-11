package com.example.fetch_rewards_exercise

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun Result_List(
    modifier: Modifier = Modifier
) {
    val repository = remember { DataRepository() }
    var filteredList by remember { mutableStateOf<List<DataApi>>(emptyList()) }

    LaunchedEffect(key1 = "Data_Fetch") {
        try {
            val dataList = repository.fetchData()
            //filter empty or null strings
            filteredList = dataList.filter { !it.name.isNullOrBlank() }
                .sortedBy { it.name }
                .sortedBy { it.listId }
            //this sorts by list_id, then by name... but that results in item 47 being after 461 if in same list id which idk if is correct
        } catch (e: Exception) {
            val error = "Error fetching data: ${e.message}"
            Log.d("Data_Fetch", error)
        }
    }
    LazyColumn(modifier = modifier) {
        items(filteredList) { item ->
            ApiCell(item = item)
        }
    }
}

