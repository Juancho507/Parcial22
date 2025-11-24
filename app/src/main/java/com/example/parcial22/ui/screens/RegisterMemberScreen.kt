package com.example.parcial22.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial22.ui.viewmodel.PlanViewModel
import com.example.parcial22.ui.theme.PurpleNeon
import com.example.parcial22.ui.theme.GlowColor
import androidx.compose.ui.graphics.Color
import com.example.parcial22.ui.screens.components.GlowButton
import com.example.parcial22.ui.screens.components.NeonText

@Composable
fun RegisterMemberScreen(
    planId: String,
    onMemberCreated: () -> Unit,
    viewModel: PlanViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    val memberCreated by viewModel.memberCreated.observeAsState(false)

    LaunchedEffect(memberCreated) {
        if (memberCreated) {
            onMemberCreated()
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        NeonText("Registrar Miembro")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del Miembro") },


            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurpleNeon,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = PurpleNeon,
                unfocusedLabelColor = Color.Gray,
                cursorColor = PurpleNeon,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        GlowButton(
            text = "Registrar",
            onClick = {
                if (name.isNotEmpty()) {
                    viewModel.createMember(name, planId)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
