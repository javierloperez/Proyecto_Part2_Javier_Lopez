package com.example.proyecto_part1_javier_lopez

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import viewmodel.AppViewModel

enum class Pantallas() {
    Inicio,
    ListarPedidos,
    RealizarPedidos,
    ResumenPago,
    ResumenPedido,
    FormularioPago
}

@Composable
fun AplicacionPrincipal(
    navController: NavHostController = rememberNavController(),
            viewModel: AppViewModel = viewModel()
) {
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name

    )
    val uIState by viewModel.uiState.collectAsState()



    Scaffold(
        topBar = {
            BarraArriba(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Pantallas.Inicio.name,
            modifier = Modifier.padding(innerPadding)

        ) {
            composable(route = Pantallas.Inicio.name) {
                Llamada(
                    onHacerPedido = { navController.navigate(Pantallas.RealizarPedidos.name) },
                    onListarPedidos = { navController.navigate(Pantallas.ListarPedidos.name) }
                )
            }

            composable(route = Pantallas.RealizarPedidos.name){
                HacerPedido(
                    onAceptar = {
                        viewModel.actualizarPedido(it)
                        navController.navigate(Pantallas.ResumenPedido.name) }
                )
            }

            composable(route = Pantallas.ListarPedidos.name){
                LlamadaPedidos(
                    pedidos=uIState.listaPedidos

                )

            }

            composable( route = Pantallas.ResumenPedido.name){
                ResumenPedido(
                    pedido=uIState.pedidos,
                    onPagar = {
                        viewModel.anyadirPedido(uIState.pedidos)
                        navController.navigate(Pantallas.FormularioPago.name)}
                )
            }

            composable(route = Pantallas.ResumenPago.name){
                ResumenPago(
                    pago = uIState.pago,
                    pedidos = uIState.pedidos,
                    onAceptar={navController.popBackStack(Pantallas.Inicio.name, inclusive = false)}
                )
            }

            composable(route = Pantallas.FormularioPago.name){

                FormularioPago(
                    onAceptar={
                        viewModel.actualizarPago(it)
                        navController.navigate(Pantallas.ResumenPago.name)

                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraArriba(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.atras)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if(puedeNavegarAtras){
                IconButton(onClick = onNavegarAtras){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.atras)
                    )
                }
            }
        },
        modifier = Modifier)
}
