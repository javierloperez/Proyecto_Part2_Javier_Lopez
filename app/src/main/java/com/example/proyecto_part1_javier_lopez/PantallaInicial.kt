package com.example.proyecto_part1_javier_lopez

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datos.Datos
import modelos.Users

@Composable
fun Llamada(modifier: Modifier = Modifier, onHacerPedido: () -> Unit, onListarPedidos: () -> Unit) {
    var usuario: Users = Datos().infoUser()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        PantallaInicial(
            usuario,
            modifier = modifier,
            onHacerPedido = onHacerPedido,
            onListarPedidos = onListarPedidos
        )

    }
}

@Composable
fun PantallaInicial(
    usuario: Users,
    modifier: Modifier = Modifier,
    onListarPedidos: () -> Unit,
    onHacerPedido: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 15.dp)
                .fillMaxWidth()

        )
        {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)

            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(usuario.imagen),
                        contentDescription = "",
                        contentScale = Crop,
                        modifier = Modifier
                            .border(5.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                            .padding(5.dp)
                            .size(125.dp)
                    )

                }


                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 25.dp),

                ) {
                    Text(
                        text = stringResource(R.string.info),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 23.sp)
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = stringResource(R.string.nombre) + ":\n" + usuario.nombre,
                        style = TextStyle(fontSize = 18.sp)
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = stringResource(R.string.apellidos) + ":\n" + usuario.apellidos,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            }


            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp)
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 13.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 5.dp, start = 15.dp),
                        text = stringResource(R.string.gmail) + ":\n" + usuario.correo,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 25.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = stringResource(R.string.telefono) + ":\n" + usuario.telefono,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            }
        }

        Button(
            onClick = onHacerPedido,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, top = 100.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(
                text = stringResource(R.string.realizarPedido),
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),

            )

        }

        Button(
            onClick = onListarPedidos,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, top = 50.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(
                text = stringResource(R.string.listarPedidos),
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),


            )
        }

    }
}



