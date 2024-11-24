package com.example.proyecto_part1_javier_lopez

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datos.Datos
import modelos.Moto
import modelos.Patinete
import modelos.Pedidos
import modelos.Turismo

@Composable
fun LlamadaPedidos(modifier: Modifier = Modifier, pedidos: List<Pedidos>) {


    var pedidoElegido by remember { mutableStateOf(pedidos[0]) }
    var vehiculos = listOf(R.string.turismo, R.string.patinete, R.string.moto)
    var tipo by remember { mutableIntStateOf(vehiculos[0]) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(pedidos) { i, pedido ->
                Button(
                    modifier = Modifier
                        .width(250.dp)
                        .height(60.dp)
                        .padding(bottom = 10.dp),
                    onClick = {
                        when (pedido.vehiculos) {
                            is Turismo -> {
                                tipo = R.string.turismo
                                pedidoElegido = pedido


                            }

                            is Patinete -> {
                                tipo = R.string.patinete
                                pedidoElegido = pedido

                            }

                            is Moto -> {
                                tipo = R.string.moto
                                pedidoElegido = pedido

                            }
                        }

                    }
                ) {

                    Text(
                        text = stringResource(R.string.pedido, i + 1),
                        style = TextStyle(fontSize = 17.sp)
                    )

                }


            }
        }


        MostrarPedido(modifier = modifier.weight(0.5f),pedidoElegido)

    }
}

@Composable
fun MostrarPedido(modifier: Modifier = Modifier, pedido: Pedidos) {

        var gpsRespuesta: Int = 0
        @StringRes var tipoVehiculo by remember { mutableStateOf(0) }
        var imagen by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = stringResource(R.string.listarPedidos).uppercase(),
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier

            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 30.dp)
            ) {


                when (pedido.vehiculos) {
                    is Moto -> {
                        tipoVehiculo = R.string.moto
                        Text(
                            text = stringResource(R.string.tipoDeVehiculo) + ": " + stringResource(
                                tipoVehiculo
                            )
                                    + "\n\n" + stringResource(R.string.cilindrada) + ": " + (pedido.vehiculos as Moto).tipo_cilindrada,
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        )

                        when ((pedido.vehiculos as Moto).tipo_cilindrada) {
                            "50cc" -> {
                                imagen = R.drawable.moto50cc
                            }

                            "125cc" -> {
                                imagen = R.drawable.moto125cc
                            }

                            "250cc" -> {
                                imagen = R.drawable.moto250cc
                            }
                        }

                    }

                    is Turismo -> {
                        tipoVehiculo = R.string.turismo

                        Text(
                            text = stringResource(R.string.tipoDeVehiculo) + ": " + stringResource(
                                tipoVehiculo
                            )
                                    + "\n\n" + stringResource(R.string.combustible) + ": " + stringResource(
                                (pedido.vehiculos as Turismo).tipo_combustible
                            ),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        )

                        when ((pedido.vehiculos as Turismo).tipo_combustible) {
                            R.string.gasolina -> {
                                imagen = R.drawable.cochegasolina
                            }

                            R.string.diesel -> {
                                imagen = R.drawable.cochediesel
                            }

                            R.string.electrico -> {
                                imagen = R.drawable.cocheelectrico
                            }
                        }
                    }

                    is Patinete -> {
                        tipoVehiculo = R.string.patinete
                        Text(
                            text = stringResource(R.string.tipoDeVehiculo) + ": " + stringResource(
                                tipoVehiculo
                            )
                                    + "\n\n" + stringResource(R.string.patineteTipo) + ": " + stringResource(
                                (pedido.vehiculos as Patinete).tipo_combustible
                            ),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        )

                        imagen = R.drawable.patineteelectrico
                    }

                }
                if (pedido.vehiculos?.gps == true) {
                    gpsRespuesta = R.string.si
                } else {
                    gpsRespuesta = R.string.no
                }
                Text(
                    text = "\n" + stringResource(R.string.gps) + ": " + stringResource(gpsRespuesta)
                            + "\n\n" + stringResource(R.string.diasAlquilados) + ": " + pedido.dia.toString()
                            + "\n\n" + stringResource(R.string.precio) + ": " + pedido.precio.toString() + "€",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )

            }
            Image(
                painter = painterResource(imagen),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .padding(top = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                }
            }
        }
    }




