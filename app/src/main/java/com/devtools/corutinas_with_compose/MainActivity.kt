package com.devtools.corutinas_with_compose

import Material3SearchBar
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import com.devtools.corutinas_with_compose.model.RetrofitHelper
import com.devtools.corutinas_with_compose.ui.theme.Corutinas_with_ComposeTheme
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    val retrofit = RetrofitHelper.intance()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            Corutinas_with_ComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(24.dp))

                        var images by remember { mutableStateOf(listOf<String>()) }


                        Material3SearchBar(
                            images = images,
                            onSearch = { query ->

                                //Coroutines
                                lifecycleScope.launch(Dispatchers.IO) {
                                    val response = retrofit.getDogsByBreeds("$query/images")
                                    val puppies = response.body()

                                    if (response.isSuccessful) {
                                        images = puppies?.message ?: emptyList()
                                    } else {
                                        // show error
                                    }
                                }


                            },
                            onClear = {
                                images = emptyList()
                            }
                        )
                    }
                }
                }

            }
        }

    }







/**
 *
 *  [Main] = Hilo principal usado para la creacion de la UI
 *   [IO] = Retrofit para peticiones o acciones que no requieren mucho procesamiento
 *   [Default] = es usado para procesar mucha informacion y que requiere mucha informacion
 *
 * Las corutinas en Kotlin son una forma de manejar la concurrencia de manera más sencilla y
 * eficiente.
 *
 * Permiten escribir código asíncrono de manera secuencial,
 * lo que facilita la lectura y el mantenimiento del código.
 *
 * Conceptos Clave:
 *
 * [Corutina]: Una corutina es una tarea que puede ser [PAUSADA]
 * y [reanudada] sin bloquear el [hilo] en el que se ejecuta.
 *
 *
 *
  */




/*
*
*
* Podríamos definir [API REST] como un servicio que nos provee de
* las funciones que necesitamos para poder obtener información de
* un cliente externo, como por ejemplo, una base de datos alojada en
* cualquier parte del mundo desde dentro de nuestra propia aplicació
*
*
* Disponemos de cuatro tipos distintos de peticiones como norma general.
*
* [Get]
*
* [Post]
*
* [put]
*
* [Delete]
*
* */








