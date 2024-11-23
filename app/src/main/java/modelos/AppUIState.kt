package modelos

import datos.Datos

data class AppUIState (

    val pedidos: Pedidos = Pedidos(),
    val pago: Pago = Pago(),
    var listaPedidos: List<Pedidos> = Datos().pedidos
    )