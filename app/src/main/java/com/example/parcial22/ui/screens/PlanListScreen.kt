package com.example.parcial22.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import com.example.parcial22.ui.viewmodel.PlanViewModel
import com.example.parcial22.ui.screens.components.GlowButton
import com.example.parcial22.ui.screens.components.NeonText
import com.example.parcial22.ui.theme.GlowBlue

import com.example.parcial2.ui.theme.CardDark

@Composable
fun PlanListScreen(
    navController: NavController,
    viewModel: PlanViewModel = viewModel()
) {

    val planes by viewModel.planes.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadPlanes()
    }


    val alphaAnim = rememberInfiniteTransition().animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1500, easing = LinearEasing),
            RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            NeonText(
                text = "PLANES DE AHORRO FAMILIARES",
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .alpha(alphaAnim.value),
                fontSize = 24.sp,
                glow = true
            )
        }




        GlowButton(
            text = "Crear Nuevo Plan",
            onClick = { navController.navigate("createPlan") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(planes) { plan ->

                // LOG
                println("PLAN ID ENVIADO → ${plan.id}")

                Card(
                    colors = CardDefaults.cardColors(containerColor = CardDark),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable {
                            navController.navigate("planDetail/${plan.id ?: ""}")
                        }
                ) {
                    Column(
                        Modifier
                            .padding(18.dp)
                    ) {
                        NeonText(text = plan.name)

                        Spacer(Modifier.height(6.dp))

                        Text(
                            text = "Motivo: ${plan.motive}",
                            color = GlowBlue
                        )

                        Text(
                            text = "Meta: $${plan.targetAmount}",
                            color = GlowBlue
                        )

                        Text(
                            text = "Meses: ${plan.months}",
                            color = GlowBlue
                        )
                    }
                }
            }
        }
    }
}
