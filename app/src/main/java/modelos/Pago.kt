package modelos

data class Pago(
    var tipoTarjeta:String="",
    var numeroTarjeta:String= "",
    var caducidadTarjeta:String="",
    var cvvTarjeta:Int=0
)