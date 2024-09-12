package com.example.fetch_rewards_exercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.fetch_rewards_exercise.ui.theme.Fetch_Rewards_exerciseTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
        setContent {
            Fetch_Rewards_exerciseTheme {
                    Result_List()

            }

        }
    }
}
}

