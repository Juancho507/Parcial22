package com.example.parcial22.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.parcial22.ui.screens.CreatePlanScreen
import com.example.parcial22.ui.screens.PlanListScreen
import com.example.parcial22.ui.screens.PlanDetailScreen
import com.example.parcial22.ui.screens.RegisterMemberScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "planList") {

        composable("planList") {
            PlanListScreen(navController)
        }

        composable("planDetail/{planId}") { backStackEntry ->
            val planId = backStackEntry.arguments?.getString("planId") ?: ""
            PlanDetailScreen(planId)
        }
        composable("registerMember/{planId}") { backStackEntry ->
            val planId = backStackEntry.arguments?.getString("planId") ?: ""
            RegisterMemberScreen(
                planId = planId,
                onMemberCreated = { navController.popBackStack() }
            )
        }
        composable("createPlan") {
            CreatePlanScreen(navController)
        }


    }
}