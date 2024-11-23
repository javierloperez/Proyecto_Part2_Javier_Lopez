package com.example.proyecto_part1_javier_lopez

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modelos.Pago

@Composable
fun FormularioPago(modifier: Modifier = Modifier, onAceptar: (Pago) -> Unit) {

    var (estado, paraEstado) = remember { mutableStateOf("") }
    val tiposTarjeta = listOf("VISA", "MasterCard", "Euro 6000")
    var tarjetaElegida:String by remember { mutableStateOf("") }
    var numTarjeta by remember { mutableStateOf("") }

    var caducidad by remember { mutableStateOf("") }
    var cvvString by remember { mutableStateOf("") }
    var cvvInt = cvvString.toIntOrNull()?:0

    val fechaComprobacion =Regex("^(0[1-9]|1[0-2])/\\d{4}$")
    val tarjetaComprobacion =Regex("^\\d{4}\\d{4}\\d{4}\\d{4}$")
    var errorFecha by remember { mutableStateOf(false) }
    var errorNumTarjeta by remember { mutableStateOf(false) }
    var errorCvv by remember { mutableStateOf(false) }

    var pago: Pago

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.formularioPago).uppercase(),
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 30.sp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .selectableGroup()
                    .padding(top = 20.dp)

            ) {
                Tarjetas(tiposTarjeta, estado, paraEstado,tarjetaElegida, onRespuestaCambiada = {tarjetaElegida=it})


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 20.dp, top = 28.dp)

            ) {

                Text(
                    text = stringResource(R.string.numTarjeta),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center),


                    )
                TextField(
                    value = numTarjeta,
                    onValueChange = { numTarjeta = it },
                    label = {
                        Text(
                            text = "----------------",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)

                        )
                        if(numTarjeta.length==16||numTarjeta.length==0||tarjetaComprobacion.matches(numTarjeta)){
                            errorNumTarjeta=false
                        }else{
                            errorNumTarjeta=true
                        }

                    },
                    singleLine = true,
                    modifier = Modifier
                        .padding(top = 30.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errorNumTarjeta
                )


            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .selectableGroup()
                    .padding(top = 20.dp)

            ) {
                Text(
                    text = stringResource(R.string.fechaCad),
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                TextField(
                    value = caducidad,
                    onValueChange = { caducidad = it },
                    label = {
                        Text(
                            text = "mm/aaaa",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)

                        )
                        if(fechaComprobacion.matches(caducidad)||caducidad.length==0){
                            errorFecha=false
                        }else{
                            errorFecha=true
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .padding(top = 30.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = errorFecha
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 20.dp, top = 20.dp)

            ) {

                Text(
                    text = stringResource(R.string.cvv),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally),

                    )
                TextField(
                    value = cvvString,
                    onValueChange = { cvvString = it },
                    label = {
                        Text(
                            text = "---",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally),

                        )
                        if (cvvString.length==0||cvvString.length==3){
                            errorCvv=false
                        }else{
                            errorCvv=true
                        }
                    },
                    singleLine = true,

                    modifier = Modifier
                        .padding(top = 30.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errorCvv
                )
            }//fin column
        }//fin row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                pago = Pago(tarjetaElegida,numTarjeta, caducidad,cvvInt)
                Button(
                    onClick = { onAceptar(pago) },
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .width(250.dp)
                        .height(80.dp)

                ) {
                    Text(
                        "$ "+stringResource(R.string.aceptar)+" $",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

    }
}

@Composable
private fun Tarjetas(
    tiposTarjeta: List<String>,
    estado: String,
    paraEstado: (String) -> Unit,
    tarjetaElegida:String,
    onRespuestaCambiada:(String)-> Unit
) {
    Text(
        text = stringResource(R.string.tipoTarjeta),
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = 10.dp),

        style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center)
    )
    tiposTarjeta.forEach { valor ->
        Row(
            Modifier
                .selectable(
                    selected = (valor == estado),
                    onClick = {
                        paraEstado(valor)
                        onRespuestaCambiada(valor)
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
                    onRespuestaCambiada(valor)
                },
            )
            Text(
                text = valor,
                modifier = Modifier
                    .weight(1f)
            )


        }
    }
}