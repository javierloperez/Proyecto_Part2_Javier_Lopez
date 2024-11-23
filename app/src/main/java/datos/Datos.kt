package datos

import com.example.proyecto_part1_javier_lopez.R
import modelos.Patinete
import modelos.Pedidos
import modelos.Turismo
import modelos.Users

class Datos {
    fun infoUser(): Users =
        Users(
            R.drawable.perfil,
            "Pepito",
            "Grillo",
            "Correo@gmail.com",
            596487590
        )

    public var pedidos:List<Pedidos> = listOf(
        Pedidos(Patinete(true ),5,200),
        Pedidos(Turismo(false, R.string.diesel ),2,150),
        Pedidos(Patinete(false ),1,200),
    )


}