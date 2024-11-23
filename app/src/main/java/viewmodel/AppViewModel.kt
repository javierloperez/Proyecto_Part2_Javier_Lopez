package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import datos.Datos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import modelos.AppUIState
import modelos.Pago
import modelos.Pedidos

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUIState())

    val uiState: StateFlow<AppUIState> = _uiState.asStateFlow()

    var pedidoMod by mutableStateOf(Pedidos(null,0,0))
    private set
    var pagoMod by mutableStateOf(Pago("", "","",0))
    private set

    fun actualizarPedido(pedidos: Pedidos){
        pedidoMod = pedidos
        _uiState.update { estadoActual ->
            estadoActual.copy(
                pedidos = pedidoMod
            )
        }
    }

    fun actualizarPago(pago: Pago){
        pagoMod = pago
        _uiState.update { estadoActual ->
            estadoActual.copy(
                pago = pagoMod
            )
        }
    }

    fun anyadirPedido(pedido: Pedidos){
        _uiState.update { estadoActual ->
            estadoActual.copy(
                listaPedidos = estadoActual.listaPedidos.plus(pedido)
            )
        }
    }




}