/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum

// TODO: AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(

) {
    // TODO: Create Controller and initialization

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenName.valueOf(
        backStackEntry?.destination?.route ?: ScreenName.Start.name
    )


    Scaffold(
        topBar = {
            // TODO: AppBar
          LunchtrayAppBar(
              currentScreen = currentScreen,
              canNavigateBack = navController.previousBackStackEntry != null,
              navigateUp = {
                  navController.navigateUp()

              })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        
       NavHost(navController = navController , startDestination =ScreenName.Start.name,
           modifier = Modifier.padding(innerPadding)){
           composable(route = ScreenName.Start.name){
               StartOrderScreen(onStartOrderButtonClicked = {
                                                            navController.navigate(ScreenName.EntreeMenu.name)

               },
                           modifier = Modifier
                               .padding(dimensionResource(R.dimen.padding_medium))
                               .fillMaxSize()
                   )

           }

           composable(route = ScreenName.EntreeMenu.name){
               EntreeMenuScreen(
                   options = DataSource.entreeMenuItems,
                   onCancelButtonClicked = {
                                           cancelOrderAndNavigateToStart(viewModel,navController)
                   },
                   onNextButtonClicked = {navController.navigate(ScreenName.SideDishMenu.name)
                                         },
                   onSelectionChanged = {
                       viewModel.updateEntree(it)

                   },
                   modifier = Modifier
                       .padding(dimensionResource(R.dimen.padding_medium))
                       .verticalScroll(rememberScrollState())
               )
           }


           composable(route = ScreenName.SideDishMenu.name){
               SideDishMenuScreen(
                   options = DataSource.sideDishMenuItems,
                   onNextButtonClicked = {
                                         navController.navigate(ScreenName.AccompanimentMenu.name)
                   },
                   onCancelButtonClicked = {
                       cancelOrderAndNavigateToStart(viewModel,navController)
                   },
                   onSelectionChanged = {
                                        viewModel.updateSideDish(it)

                   },
                   modifier = Modifier
                       .padding(dimensionResource(R.dimen.padding_medium))
                       .verticalScroll(rememberScrollState())
               )

           }

           composable(route = ScreenName.AccompanimentMenu.name){
               AccompanimentMenuScreen(
                   options = DataSource.accompanimentMenuItems,
                   onNextButtonClicked = {
                                         navController.navigate(ScreenName.Checkout.name)
                   },
                   onCancelButtonClicked = {
                       cancelOrderAndNavigateToStart(viewModel,navController)
                   },
                   onSelectionChanged = {
                                        viewModel.updateAccompaniment(it)

                   },
                   modifier = Modifier
                       .padding(dimensionResource(R.dimen.padding_medium))
                       .verticalScroll(rememberScrollState())
               )


           }
           composable(route = ScreenName.Checkout.name ){
               CheckoutScreen(
                   orderUiState = uiState ,
                   onNextButtonClicked = {
                                         cancelOrderAndNavigateToStart(viewModel,navController)
                   },
                   onCancelButtonClicked = {
                       cancelOrderAndNavigateToStart(viewModel,navController)
                   },
                   modifier = Modifier
                       .padding(dimensionResource(R.dimen.padding_medium))
                       .verticalScroll(rememberScrollState())


                   )






           }



       }


        // TODO: Navigation host
    }
}

enum class ScreenName(title:Int){
    Start(title = R.string.app_name),
    EntreeMenu(title = R.string.choose_entree),
    SideDishMenu(title = R.string.choose_side_dish),
    AccompanimentMenu(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)


}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(ScreenName.Start.name, inclusive = false)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchtrayAppBar(
    currentScreen: ScreenName,

    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.name) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
