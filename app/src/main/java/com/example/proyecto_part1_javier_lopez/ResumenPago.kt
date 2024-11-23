package com.example.proyecto_part1_javier_lopez

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modelos.AppUIState
import modelos.Pago
import modelos.Pedidos

@Composable
fun ResumenPago(modifier: Modifier = Modifier, onAceptar: () -> Unit, pago: Pago, pedidos: Pedidos) {
    var mensaje by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.resumenPago).uppercase(),
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )

        Text(
            text = stringResource(R.string.tipoTarjeta)+": "+ pago.tipoTarjeta
            +"\n\n"+stringResource(R.string.numTarjeta)+": "+ pago.numeroTarjeta
            +"\n\n"+stringResource(R.string.cvv)+": "+ pago.cvvTarjeta
            +"\n\n"+stringResource(R.string.fechaCad)+": "+ pago.caducidadTarjeta
            +"\n\n"+stringResource(R.string.precio)+": "+ pedidos.precio,

            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp),
            modifier = Modifier
                .padding(top = 20.dp)
            )


        Row(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

            ) {
                Button(
                    onClick = onAceptar,
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    modifier = Modifier
                        .width(150.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text =stringResource(R.string.aceptar),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        mensaje = true
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .width(150.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = stringResource(R.string.enviar),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mensaje == true) {
                    Text(
                        text = stringResource(R.string.justificante),
                        style = TextStyle(fontSize = 25.sp)
                    )
                }
            }
        }
    }
}



