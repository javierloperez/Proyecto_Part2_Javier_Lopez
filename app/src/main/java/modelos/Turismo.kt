package modelos

import androidx.annotation.StringRes

class Turismo(
    gps: Boolean,
    @StringRes var tipo_combustible:Int = 0
): Vehiculos(gps) {

}