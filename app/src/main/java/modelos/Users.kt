package modelos

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Users(

    @DrawableRes val imagen:Int,
    var nombre: String,
    var apellidos: String,
    var correo: String,
    var telefono: Int,


    )