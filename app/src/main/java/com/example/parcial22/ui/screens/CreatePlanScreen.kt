package com.example.parcial22.ui.screens

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial22.ui.screens.components.GlowButton
import com.example.parcial22.ui.screens.components.NeonText
import com.example.parcial22.ui.theme.BackgroundDark
import com.example.parcial22.ui.theme.PurpleNeon
import com.example.parcial22.ui.viewmodel.PlanViewModel

@Composable
fun CreatePlanScreen(
    navController: NavController,
    viewModel: PlanViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var targetAmount by remember { mutableStateOf("") }
    var months by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(16.dp)
    ) {

        NeonText(
            text = "Crear Nuevo Plan",
            modifier = Modifier.padding(bottom = 25.dp)
        )


        val neonFieldColors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = PurpleNeon,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = PurpleNeon,
            focusedLabelColor = PurpleNeon,
            unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del plan") },
            colors = neonFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = targetAmount,
            onValueChange = { targetAmount = it },
            label = { Text("Meta") },
            colors = neonFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = months,
            onValueChange = { months = it },
            label = { Text("Meses") },
            colors = neonFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = reason,
            onValueChange = { reason = it },
            label = { Text("Motivo") },
            colors = neonFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(25.dp))

        GlowButton(
            text = "Crear Plan",
            onClick = {
                if (name.isNotEmpty() && months.isNotEmpty() && targetAmount.isNotEmpty()) {
                    viewModel.createPlan(
                        name = name,
                        motive = reason,
                        targetAmount = targetAmount.toInt(),
                        months = months.toInt()
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
