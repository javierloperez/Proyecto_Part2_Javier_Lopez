package com.example.proyecto_part1_javier_lopez

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import modelos.Moto
import modelos.Patinete
import modelos.Pedidos
import modelos.Turismo
import modelos.Vehiculos
import viewmodel.AppViewModel



@Composable
fun HacerPedido(
    modifier: Modifier = Modifier,
    onAceptar: (pedidos: Pedidos) -> Unit,
) {
    val (estado, paraEstado) = remember { mutableStateOf(0) }
    val (estadoGps, paraEstadoGps) = remember { mutableStateOf(0) }
    val tipoVehiculo = listOf(R.string.turismo, R.string.moto, R.string.patinete)
    val respuesta = listOf(R.string.si, R.string.no)
    @StringRes var combustible: Int = 0
    var cilindrada: String = ""
    var num by remember { mutableStateOf(0) }
    var gps: Boolean by remember { mutableStateOf(false) }
    var dias by remember { mutableStateOf("") }
    var diasNum = dias.toIntOrNull() ?: 0
    var pedidos: Pedidos
    var precio: Int = 0
    var vehiculo: Vehiculos = Vehiculos()
    var comprobarDias by remember { mutableStateOf(false) }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.realizarPedido).uppercase(),
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            style = TextStyle(fontSize = 30.sp)

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .selectableGroup()
                    .padding(end = 15.dp),

                ) {

                Text(
                    text = stringResource(R.string.tipoDeVehiculo),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 10.dp),
                    style = TextStyle(fontSize = 25.sp),

                    )
                tipoVehiculo.forEach { valor ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (valor == estado),
                                onClick = {
                                    paraEstado(valor)
                                    num = tipoVehiculo.indexOf(valor)
                                },
                                role = Role.RadioButton
                            )
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (valor == estado),
                            onClick = {
                                paraEstado(valor)
                                num = tipoVehiculo.indexOf(valor)
                            },
                        )
                        Text(
                            text = stringResource(valor),
                            modifier = Modifier
                                .weight(1f)
                        )


                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .selectableGroup()


            ) {
                when (num) {
                    0 -> {
                        combustible = SeleccionarTurismos()
                        when (combustible) {
                            R.string.gasolina -> precio += 25
                            R.string.diesel -> precio += 20
                            R.string.electrico -> precio += 10
                        }

                        vehiculo = Turismo(gps, combustible)
                    }

                    1 -> {
                        cilindrada = SeleccionarMoto()
                        when (cilindrada) {
                            "50cc" -> precio += 10
                            "125cc" -> precio += 15
                            "250cc" -> precio += 20
                        }
                        vehiculo = Moto(gps, cilindrada)

                    }

                    2 -> {
                        precio += SeleccionarPatinete()
                        vehiculo = Patinete(gps)
                    }

                }
                precio = precio * diasNum

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            //GPS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .selectableGroup()
                    .padding(end = 15.dp),
                verticalArrangement = Arrangement.Center

            ) {

                Text(
                    text = "GPS",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp),
                    style = TextStyle(fontSize = 25.sp)

                )
                respuesta.forEach { valor ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (valor == estadoGps),
                                onClick = {
                                    paraEstadoGps(valor)

                                    when (respuesta.indexOf(valor)) {
                                        0 -> gps = true
                                        1 -> gps = false
                                    }
                                },
                                role = Role.RadioButton
                            )
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (valor == estadoGps),
                            onClick = {
                                paraEstadoGps(valor)
                                when (respuesta.indexOf(valor)) {
                                    0 -> gps = true
                                    1 -> gps = false
                                }
                            },


                            )
                        Text(
                            text = stringResource(valor),
                            modifier = Modifier
                                .weight(1f),
                            style = TextStyle(fontSize = 18.sp)
                        )


                    }
                }
            }

            if (gps == true) {
                precio += 5 * diasNum

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center


            ) {
                Text(
                    text = stringResource(R.string.diasAlquilar),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp),
                    style = TextStyle(fontSize = 25.sp)

                )
                TextField(
                    value = dias,
                    onValueChange = { dias = it },
                    label = {
                        Text(
                            text = stringResource(R.string.diasAlquilar),
                            style = TextStyle(fontSize = 18.sp)
                        )
                        if (diasNum > 0 || dias.length == 0) {
                            comprobarDias = false

                        } else {
                            comprobarDias = true

                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .padding(top = 15.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = comprobarDias

                )
            }


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            if (comprobarDias == true) {
                precio = 0
            }
            Text(
                text = stringResource(R.string.precio) + ": " + precio + "€",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp)
            )


        }
        Row(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
        ) {
            //button cancelar
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                pedidos = Pedidos(vehiculo,diasNum,precio)
                Button(
                    onClick = { onAceptar(pedidos) },
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .height(80.dp)
                        .width(250.dp)

                ) {
                    Text(
                        text = stringResource(R.string.guardar),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)

                    )
                }
            }
        }


    }
}


@Composable
fun SeleccionarPatinete(): Int {
    val coste: Int = 5
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.patineteTipo),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 10.dp),
            style = TextStyle(fontSize = 25.sp)
        )
        Text(
            text = "-" + stringResource(R.string.electrico),
            modifier = Modifier
                .padding(top = 20.dp),
            style = TextStyle(fontSize = 18.sp)
        )
    }

    return coste
}

@Composable
fun SeleccionarMoto(): String {
    val (estado, paraEstado) = remember { mutableStateOf("") }
    val cilindradas = listOf("50cc", "125cc", "250cc")
    var seleccion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()

    ) {

        Text(
            text = stringResource(R.string.cilindrada) + "\n",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 10.dp),
            style = TextStyle(fontSize = 25.sp)
        )
        cilindradas.forEach { valor ->
            Row(
                Modifier
                    .selectable(
                        selected = (valor == estado),
                        onClick = {
                            paraEstado(valor)
                            seleccion = valor
                        },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                RadioButton(
                    selected = (valor == estado),
                    onClick = {
                        paraEstado(valor)
                        seleccion = valor
                    },


                    )
                Text(
                    text = valor,
                    modifier = Modifier
                        .weight(1f),
                    style = TextStyle(fontSize = 18.sp)
                )

            }
        }
    }
    return seleccion
}

@Composable
fun SeleccionarTurismos(): Int {
    val (estado, paraEstado) = remember { mutableStateOf(0) }
    val combustibles = listOf(R.string.gasolina, R.string.diesel, R.string.electrico)
    var seleccion by remember { mutableStateOf(0) }
    var coste: Int = 0


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()


    ) {

        Text(
            text = stringResource(R.string.combustible) + "\n",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 10.dp),
            style = TextStyle(fontSize = 25.sp)
        )
        combustibles.forEach { valor ->
            Row(
                Modifier
                    .selectable(
                        selected = (valor == estado),
                        onClick = {
                            paraEstado(valor)
                            seleccion = valor

                        },

                        role = Role.RadioButton
                    )
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (valor == estado),
                    onClick = {
                        paraEstado(valor)
                        seleccion = valor


                    },


                    )
                Text(
                    text = stringResource(valor),
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontSize = 18.sp)
                )


            }

        }
    }


    return seleccion
}



