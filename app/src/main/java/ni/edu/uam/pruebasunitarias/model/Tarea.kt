package ni.edu.uam.pruebasunitarias.model

enum class EstadoTarea {
    PENDIENTE,
    COMPLETADA
}

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    var estado: EstadoTarea = EstadoTarea.PENDIENTE
)