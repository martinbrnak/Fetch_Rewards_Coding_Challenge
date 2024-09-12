package com.example.fetch_rewards_exercise

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Result_List(
    modifier: Modifier = Modifier
) {
    val repository = remember { DataRepository() }
    var groupedList by remember { mutableStateOf<Map<Long, List<DataApi>>>(emptyMap())}

    LaunchedEffect(key1 = "Data_Fetch") {
        try {
            val dataList = repository.fetchData()
            //filter empty or null strings
            groupedList = dataList
                .filter { !it.name.isNullOrBlank() }
                .sortedWith(compareBy({ it.listId }, { it.name }))
                .groupBy { it.listId }
            //this sorts by listId, then by name... but that results in item 47 being after 461 if in same list
        } catch (e: Exception) {
            val error = "Error fetching data: ${e.message}"
            Log.d("Data_Fetch", error)
        }
    }

    LazyColumn(modifier = modifier) {
        groupedList.forEach { (listId, items) ->
            stickyHeader {
                ListIdHeader(listId = listId)
            }
            items(items) { item ->
                ApiCell(item = item)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ListIdHeader(listId: Long) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black)
    ) {
        Text(
            text = "List ID: $listId",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}