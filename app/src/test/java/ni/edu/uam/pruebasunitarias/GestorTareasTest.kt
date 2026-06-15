package ni.edu.uam.pruebasunitarias


import ni.edu.uam.pruebasunitarias.model.EstadoTarea
import ni.edu.uam.pruebasunitarias.model.Tarea
import org.junit.Assert.assertEquals
import org.junit.Test

class GestorTareasTest {

    @Test
    fun agregarTarea_incrementaListaEnUno() {
        val gestor = GestorTareas()

        val tarea = Tarea(
            id = 1,
            titulo = "Estudiar",
            descripcion = "Estudiar para el examen"
        )

        gestor.agregarTarea(tarea)

        assertEquals(1, gestor.obtenerTodasLasTareas().size)
    }

    @Test
    fun eliminarTarea_desapareceDeLaLista() {
        val gestor = GestorTareas()

        val tarea = Tarea(
            id = 1,
            titulo = "Comprar pan",
            descripcion = "Ir a la pulpería"
        )

        gestor.agregarTarea(tarea)
        gestor.eliminarTarea(1)

        assertEquals(0, gestor.obtenerTodasLasTareas().size)
    }

    @Test
    fun completarTarea_cambiaEstadoACompletada() {
        val gestor = GestorTareas()

        val tarea = Tarea(
            id = 1,
            titulo = "Hacer tarea",
            descripcion = "Terminar práctica de Android"
        )

        gestor.agregarTarea(tarea)
        gestor.marcarComoCompletada(1)

        assertEquals(
            EstadoTarea.COMPLETADA,
            gestor.obtenerTodasLasTareas()[0].estado
        )
    }

    @Test
    fun contarTareasPendientes_retornaValorCorrecto() {
        val gestor = GestorTareas()

        val tarea1 = Tarea(
            id = 1,
            titulo = "Tarea 1",
            descripcion = "Primera tarea"
        )

        val tarea2 = Tarea(
            id = 2,
            titulo = "Tarea 2",
            descripcion = "Segunda tarea"
        )

        gestor.agregarTarea(tarea1)
        gestor.agregarTarea(tarea2)

        gestor.marcarComoCompletada(1)

        assertEquals(1, gestor.contarTareasPendientes())
    }

    @Test
    fun listaVacia_retornaCeroPendientes() {
        val gestor = GestorTareas()

        assertEquals(0, gestor.contarTareasPendientes())
    }
}
