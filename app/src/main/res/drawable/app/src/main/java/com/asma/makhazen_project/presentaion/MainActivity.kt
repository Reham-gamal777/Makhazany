package com.asma.makhazen_project.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.asma.makhazen_project.screens.CustomersScreen
import com.asma.makhazen_project.screens.HomeScreen
import com.asma.makhazen_project.screens.IncomingScreen
import com.asma.makhazen_project.screens.OutgoingScreen
import com.asma.makhazen_project.screens.WarehousesScreen
import com.asma.makhazen_project.ui.theme.Makhazen_ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Makhazen_ProjectTheme {
                var currentScreen by remember { mutableStateOf("home") }

                when (currentScreen) {
                    "home" -> HomeScreen(
                        onOutgoingClick = { currentScreen = "outgoing" },
                        onIncomingClick = { currentScreen = "incoming" },
                        onCustomersClick = { currentScreen = "customers" },
                        onWarehousesClick = { currentScreen = "warehouses" }
                    )
                    "outgoing" -> OutgoingScreen(onBackClick = { currentScreen = "home" })
                    "incoming" -> IncomingScreen(onBackClick = { currentScreen = "home" })
                    "customers" -> CustomersScreen(onBackClick = { currentScreen = "home" })
                    "warehouses" -> WarehousesScreen(onBackClick = { currentScreen = "home" })
                }
            }
        }
    }
}
