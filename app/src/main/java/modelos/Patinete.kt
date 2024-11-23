package modelos


import androidx.annotation.StringRes
import com.example.proyecto_part1_javier_lopez.R

class Patinete(
    gps: Boolean,
    var tipo_combustible:Int = R.string.electrico
): Vehiculos(gps) {

}