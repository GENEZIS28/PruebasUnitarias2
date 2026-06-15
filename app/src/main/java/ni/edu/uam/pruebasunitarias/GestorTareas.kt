package ni.edu.uam.pruebasunitarias

import ni.edu.uam.pruebasunitarias.model.EstadoTarea
import ni.edu.uam.pruebasunitarias.model.Tarea


class GestorTareas {

    private val tareas = mutableListOf<Tarea>()

    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun marcarComoCompletada(id: Int) {
        val tarea = tareas.find { it.id == id }
        tarea?.estado = EstadoTarea.COMPLETADA
    }

    fun obtenerTareasPendientes(): List<Tarea> {
        return tareas.filter { it.estado == EstadoTarea.PENDIENTE }
    }

    fun contarTareasPendientes(): Int {
        return obtenerTareasPendientes().size
    }

    fun obtenerTodasLasTareas(): List<Tarea> {
        return tareas
    }

    fun obtenerTareasCompletadas(): List<Tarea> {
        return tareas.filter { it.estado == EstadoTarea.COMPLETADA }
    }

    fun ordenarTareasAlfabeticamente(): List<Tarea> {
        return tareas.sortedBy { it.titulo }
    }

    fun porcentajeCompletadas(): Double {
        if (tareas.isEmpty()) {
            return 0.0
        }

        val completadas = tareas.count { it.estado == EstadoTarea.COMPLETADA }
        return (completadas.toDouble() / tareas.size) * 100
    }
}