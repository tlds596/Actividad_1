package com.example.myapplication
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import android.R.attr.type
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import android.app.Activity


sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{id}"){
        fun createRoute(id: Int) = "details/$id"
        const val arg = "id"
    }
    data object Settings: Screen("settings")
}


@Composable
fun App (){
    val nav = rememberNavController()
    AppNavGraph(navController = nav)
}

@Composable
fun AppNavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToDetails = {id ->
                    navController.navigate(Screen.Details.createRoute(id))
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }


            )
        }
        composable(route = Screen.Details.route,
            arguments = listOf(navArgument(Screen.Details.arg) {
                type = NavType.IntType
            })
        ) {
            stackEntry ->
            val id = stackEntry.arguments?.getInt(Screen.Details.arg)?:-1
            Details(
                id = id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(
               onBack = {
                   navController.popBackStack()
               }
            )
        }
    }
}

@Composable
fun HomeScreen(onNavigateToDetails: (Int) -> Unit,
               onNavigateToSettings: () -> Unit) {
    Scaffold (modifier = Modifier,
        topBar = {
            Text(text = "Home")})
    {
            padding ->
        Column (modifier = Modifier.padding(paddingValues = padding)
            .fillMaxSize()) {
            Text(modifier = Modifier.padding(16.dp),
                text = "Pantalla Home de navegation Compose")
            LazyRow {
                item {
                    Row (modifier = Modifier){
                        listOf(1,2,3,4,5,6,7).forEach { id->
                            AssistChip(
                                modifier = Modifier.padding(8.dp),
                                onClick = {
                                    onNavigateToDetails(id)
                                },
                                label = {
                                    Text(text = "Details $id")
                                }
                            )
                        }
                    }
                }
            }
            Button(onClick = {onNavigateToSettings()}) {
                Text(text = "Settings")
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(id: Int, onBack:()-> Unit) {
    Scaffold (
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {Text(text = "Details")},
                navigationIcon= {
                 Text(modifier = Modifier.clickable{
                     onBack()
                 },
                     text = "⬅️"
                    )
                }
            )
        }
    ){padding ->
        Column (modifier = Modifier.padding(paddingValues = padding)
            .fillMaxSize()){
            Text(modifier = Modifier.padding(16.dp),
                text = "Detalles del cliente con el id $id")
            Button(onClick = {onBack()}) {
                Text(text = "Volver")
            }
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Scaffold (
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {Text(text = "Settings")},
                navigationIcon= {
                    Text(modifier = Modifier.clickable{
                        onBack()
                    },
                        text = "⬅️"
                    )
                }
            )
        }
    ){padding ->
        Column (modifier = Modifier.padding(paddingValues = padding)
            .fillMaxSize()){
            Text(modifier = Modifier.padding(16.dp),
                text = "Ajustes de la aplicacion")
            Spacer(Modifier.height(12.dp))
            Button(onClick = {onBack()}) {
                Text(text = "Volver")
            }
        }
    }
}



