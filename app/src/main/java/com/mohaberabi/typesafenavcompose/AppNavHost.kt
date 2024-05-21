package com.mohaberabi.typesafenavcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenA,
    ) {

        composable<ScreenA> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(
                            ScreenB(
                                "The Loser", 26
                            )
                        )
                    },
                ) {
                    Text(text = "Go To Screen B")
                }
            }
        }
        composable<ScreenB> {

            LoserScreen()
        }
    }
}


@Serializable
object ScreenA


@Serializable
data class ScreenB(
    val name: String?,
    val age: Int,
)

@Composable
fun LoserScreen(
    viewModel: LoserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "${state.name} is ${state.age} years old ")
    }
}