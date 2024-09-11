package com.example.fetch_rewards_exercise

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun ApiCellPreview(){
    ApiCell(DataApi(1, 1, "hello"))
}

@Composable
fun ApiCell(item: DataApi) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .border(4.dp, Color.LightGray)
            .padding(16.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
            )
            {
                Text(
                    text = "List Id: ${item.listId}"
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
                Text(
                    text = "Id: ${item.id}"
                )
            }
            Text(
                modifier = Modifier.weight(1f).align(Alignment.CenterVertically),
                text = "Name: ${item.name}",
                fontWeight = FontWeight.Bold

            )
        }
    }
}