package com.example.proyecto_part1_javier_lopez

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datos.Datos
import modelos.Pago
import modelos.Pedidos
import modelos.Users

@Composable
fun ResumenPago(modifier: Modifier = Modifier, onAceptar: () -> Unit, pago: Pago, pedidos: Pedidos) {
    var mensaje by remember { mutableStateOf(false) }
    val contexto = LocalContext.current
    val asunto:String = stringResource(R.string.justificanteAsunto)
    val user : Users = Datos().infoUser()
    val tipoTarjeta:String = stringResource(R.string.tipoTarjeta)
    val cvv:String = stringResource(R.string.cvv)
    val numTarjeta:String = stringResource(R.string.numTarjeta)
    val fechaCad:String = stringResource(R.string.fechaCad)
    val precio:String = stringResource(R.string.precio)
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
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("usuario@example.com"))
                            putExtra(Intent.EXTRA_SUBJECT, asunto + user.nombre)
                            putExtra(Intent.EXTRA_TEXT, tipoTarjeta+": " + pago.tipoTarjeta
                            +"\n\n"+numTarjeta+": "+pago.numeroTarjeta
                            +"\n\n"+fechaCad+": "+pago.caducidadTarjeta
                            +"\n\n"+cvv+": "+pago.cvvTarjeta
                            +"\n\n"+precio+": "+pedidos.precio+"€")
                            putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
                        }

                        try {
                            contexto.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                        }
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



