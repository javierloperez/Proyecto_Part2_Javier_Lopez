package com.example.proyecto_part1_javier_lopez

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datos.Datos
import modelos.AppUIState
import modelos.Moto
import modelos.Patinete
import modelos.Pedidos
import modelos.Turismo

@Composable
fun ResumenPedido(modifier: Modifier = Modifier, onPagar: () -> Unit, pedido: Pedidos, ) {

    var gpsRespuesta:Int = 0
    @StringRes var tipoVehiculo by remember { mutableStateOf(0) }

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


            when(pedido.vehiculos){
                is Moto ->{
                    tipoVehiculo=R.string.moto
                    Text(
                    text = stringResource(R.string.tipoDeVehiculo)+": "+ stringResource(tipoVehiculo)
                    +"\n\n"+stringResource(R.string.cilindrada)+": " +(pedido.vehiculos as Moto).tipo_cilindrada,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                }
                is Turismo->{
                    tipoVehiculo=R.string.turismo

                    Text(
                        text = stringResource(R.string.tipoDeVehiculo)+": "+ stringResource(tipoVehiculo)
                        +"\n\n"+stringResource(R.string.combustible) +": " +stringResource((pedido.vehiculos as Turismo).tipo_combustible),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                }
                is Patinete->{
                    tipoVehiculo=R.string.patinete
                    Text(
                        text = stringResource(R.string.tipoDeVehiculo)+": "+ stringResource(tipoVehiculo)
                        +"\n\n"+stringResource(R.string.patineteTipo) +": " +stringResource((pedido.vehiculos as Patinete).tipo_combustible),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                }

            }
            if(pedido.vehiculos?.gps==true){
                gpsRespuesta = R.string.si
            }else{
                gpsRespuesta = R.string.no
            }
            Text(
                text = "\n"+stringResource(R.string.gps)+": "  + stringResource(gpsRespuesta)
                        +"\n\n"+stringResource(R.string.diasAlquilados)+": "  + pedido.dia.toString()
                        +"\n\n"+stringResource(R.string.precio)+": "  + pedido.precio.toString()+"€",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )

        }
        Image(
            painter = painterResource(R.drawable.imagen_desarrollo),
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

                Button(
                    onClick = {
                        onPagar()
                        Datos().pedidos.plus(pedido)
                              },
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .width(250.dp)
                        .height(80.dp)
                        .padding(top = 20.dp),


                ) {
                    Text(
                        text = stringResource(R.string.pagar),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }


            }
        }

    }
}

