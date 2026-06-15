package ni.edu.uam.pruebasunitarias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import ni.edu.uam.pruebasunitarias.model.EstadoTarea
import ni.edu.uam.pruebasunitarias.model.Tarea

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PantallaTareas()
            }
        }
    }
}

@Composable
fun PantallaTareas() {

    var titulo by remember { mutableStateOf("") }
    var contadorId by remember { mutableStateOf(1) }
    val tareas = remember { mutableStateListOf<Tarea>() }

    val pendientes = tareas.count { tarea ->
        tarea.estado == EstadoTarea.PENDIENTE
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Gestión de Tareas")

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = titulo, onValueChange = { nuevoTexto ->
                titulo = nuevoTexto
            }, label = {
                Text(text = "Título de la tarea")
            }, modifier = Modifier
                .fillMaxWidth()
                .testTag("campoTitulo")
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (titulo.isNotBlank()) {
                    val nuevaTarea = Tarea(
                        id = contadorId,
                        titulo = titulo,
                        descripcion = "Sin descripción",
                        estado = EstadoTarea.PENDIENTE
                    )

                    tareas.add(nuevaTarea)
                    contadorId = contadorId + 1
                    titulo = ""
                }
            }, modifier = Modifier.testTag("botonAgregar")
        ) {
            Text(text = "Agregar tarea")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Tareas pendientes: $pendientes",
            modifier = Modifier.testTag("contadorPendientes")
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("listaTareas")
        ) {

            items(
                items = tareas, key = { tarea -> tarea.id }) { tarea ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .testTag("tarea_${tarea.id}")
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(text = tarea.titulo)

                        Text(text = "Estado: ${tarea.estado}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {

                            Button(
                                onClick = {
                                    val posicion = tareas.indexOfFirst { item ->
                                        item.id == tarea.id
                                    }

                                    if (posicion != -1) {
                                        tareas[posicion] = tarea.copy(
                                            estado = EstadoTarea.COMPLETADA
                                        )
                                    }
                                }, modifier = Modifier.testTag("botonCompletar_${tarea.id}")
                            ) {
                                Text(text = "Completar")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    tareas.remove(tarea)
                                }, modifier = Modifier.testTag("botonEliminar_${tarea.id}")
                            ) {
                                Text(text = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}