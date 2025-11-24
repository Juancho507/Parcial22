package com.example.parcial22.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial22.ui.viewmodel.PlanViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial22.ui.screens.components.GlowButton
import com.example.parcial22.ui.screens.components.NeonText
import com.example.parcial22.ui.theme.BackgroundDark
import com.example.parcial22.ui.theme.CardDark
import com.example.parcial22.ui.theme.PurpleNeon

@Composable
fun PlanDetailScreen(
    planId: String,
    viewModel: PlanViewModel = viewModel()
) {

    val members by viewModel.members.observeAsState(emptyList())
    val payments by viewModel.payments.observeAsState(emptyList())

    var showAddMemberDialog by remember { mutableStateOf(false) }
    var newMemberName by remember { mutableStateOf("") }

    var showAddPaymentDialog by remember { mutableStateOf(false) }
    var paymentAmount by remember { mutableStateOf("") }
    var selectedMemberId by remember { mutableStateOf("") }

    LaunchedEffect(planId) {
        println("PLAN ID RECIBIDO → $planId")
        viewModel.loadMembers(planId)
        viewModel.loadPayments(planId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(16.dp)
    ) {

        Column {


            NeonText(
                text = "Detalles del Plan",
                modifier = Modifier.padding(bottom = 20.dp)
            )

            NeonText("Miembros")
            Spacer(Modifier.height(10.dp))


            members.forEach { member ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = CardDark)
                ) {
                    Text(
                        "- ${member.name}",
                        modifier = Modifier.padding(12.dp),
                        color = PurpleNeon
                    )
                }
            }

            Spacer(Modifier.height(10.dp))


            GlowButton(
                text = "Agregar Miembro",
                onClick = { showAddMemberDialog = true },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(25.dp))

            NeonText("Pagos")
            Spacer(Modifier.height(10.dp))

            payments.forEach { p ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = CardDark)
                ) {
                    val memberName = members.find { it.id == p.memberId }?.name ?: "Desconocido"

                    Text(
                        text = "Pago: ${p.amount} • Miembro: $memberName",
                        modifier = Modifier.padding(12.dp),
                        color = PurpleNeon
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            GlowButton(
                text = "Registrar Pago",
                onClick = { showAddPaymentDialog = true },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (showAddMemberDialog) {
        AlertDialog(
            onDismissRequest = { showAddMemberDialog = false },
            confirmButton = {
                GlowButton(
                    text = "Guardar",
                    onClick = {
                        viewModel.addMemberToPlan(
                            name = newMemberName,
                            planId = planId,
                            contributionPerMonth = 0
                        )
                        newMemberName = ""
                        showAddMemberDialog = false
                    }
                )
            },
            dismissButton = {
                GlowButton(
                    text = "Cancelar",
                    onClick = { showAddMemberDialog = false }
                )
            },
            title = { NeonText("Nuevo miembro") },
            text = {
                TextField(
                    value = newMemberName,
                    onValueChange = { newMemberName = it },
                    label = { Text("Nombre del miembro") }
                )
            }
        )
    }

    if (showAddPaymentDialog) {
        AlertDialog(
            onDismissRequest = { showAddPaymentDialog = false },
            confirmButton = {
                GlowButton(
                    text = "Guardar Pago",
                    onClick = {
                        if (paymentAmount.isNotBlank() && selectedMemberId.isNotBlank()) {
                            viewModel.addPaymentToPlan(
                                amount = paymentAmount.toInt(),
                                planId = planId,
                                memberId = selectedMemberId
                            )
                        }
                        paymentAmount = ""
                        selectedMemberId = ""
                        showAddPaymentDialog = false
                    }
                )
            },
            dismissButton = {
                GlowButton(
                    text = "Cancelar",
                    onClick = { showAddPaymentDialog = false }
                )
            },
            title = { NeonText("Registrar Pago") },
            text = {
                Column {
                    TextField(
                        value = paymentAmount,
                        onValueChange = { paymentAmount = it },
                        label = { Text("Valor del pago") }
                    )

                    Spacer(Modifier.height(10.dp))

                    NeonText("Selecciona un miembro:")

                    members.forEach { member ->
                        Row {
                            RadioButton(
                                selected = selectedMemberId == member.id,
                                onClick = { selectedMemberId = member.id ?: "" }
                            )
                            Text(member.name, color = PurpleNeon)
                        }
                    }
                }
            }
        )
    }
}
