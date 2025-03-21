package com.example.feedm.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.feedm.petsFeature.ui.view.screens.AddFoodScreen
import com.example.feedm.petsFeature.ui.view.screens.AddMealScreen
import com.example.feedm.petsFeature.ui.view.screens.AddPetScreen
import com.example.feedm.petsFeature.ui.view.screens.DashBoardScreen
import com.example.feedm.petsFeature.ui.view.screens.FoodListScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DashBoardScreen) {
        composable<DashBoardScreen> {
            DashBoardScreen { destination, petId ->
                when (destination) {
                    "AddPetScreen" -> navController.navigate(AddPetScreen)
                    "AddMealScreen" -> navController.navigate(AddMealScreen(petId!!))
                    "AddFoodScreen" -> navController.navigate(AddFoodScreen())
                    "EditPetScreen" -> navController.navigate(EditPetScreen(petId!!))
                }
            }
        }
        composable<AddPetScreen> {
            AddPetScreen { navController.popBackStack() }
        }
        composable<AddMealScreen> {
            val petId = it.toRoute<AddMealScreen>().petId
            AddMealScreen(
                navToFoodList = { navController.navigate(FoodListScreen("FromAddMeal", petId)) },
                navToBackStack = { navController.popBackStack() })
        }
        composable<AddFoodScreen> {
            val origin = it.toRoute<AddFoodScreen>().origin
            val getBackDestination = {
                if (origin == "FromFoodList") {
                    navController.navigate(FoodListScreen("FromAddMeal")) {
                        popUpTo<FoodListScreen> { inclusive = true }
                    }
                } else {
                    navController.popBackStack()
                }
            }
            val getDestination = {
                if (origin == "FromAddMeal") {
                    navController.navigate(FoodListScreen("FromAddMeal")) {
                        popUpTo<FoodListScreen> { inclusive = true }
                    }
                } else {
                    navController.navigate(FoodListScreen("FromAddFood"))
                }
            }
            BackHandler { getBackDestination() }
            AddFoodScreen(navToBackStack = { getBackDestination() },
                navToFoodList = {
                    getDestination()
                })
        }
        composable<FoodListScreen> {
            val foodListOrigin = it.toRoute<FoodListScreen>().origin
            val petId = it.toRoute<FoodListScreen>().petId
            val getBackDestination = {
                when (foodListOrigin) {
                    "FromAddFood" -> navController.navigate(DashBoardScreen) {
                        popUpTo<DashBoardScreen> { inclusive = true }
                    }
                    "FromAddMeal" -> navController.navigate(AddMealScreen(petId!!)) {
                        popUpTo<AddMealScreen> { inclusive = true }
                    }
                    else -> {}
                }
            }
            BackHandler { getBackDestination() }
            FoodListScreen(
                navToAddFood = { navController.navigate(AddFoodScreen(foodListOrigin)) },
                navToBackStack = { getBackDestination() }
            )
        }
    }
}