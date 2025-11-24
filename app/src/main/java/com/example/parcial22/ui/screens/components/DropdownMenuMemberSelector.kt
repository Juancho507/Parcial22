package com.example.parcial22.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.parcial22.data.model.Member

@Composable
fun DropdownMenuMemberSelector(
    members: List<Member>,
    selectedMember: Member?,
    onMemberSelected: (Member) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = { expanded = true }) {
            Text(text = selectedMember?.name ?: "Select a member")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            members.forEach { member ->
                DropdownMenuItem(onClick = {
                    onMemberSelected(member)
                    expanded = false
                }, text = { Text(text = member.name) })
            }
        }
    }
}