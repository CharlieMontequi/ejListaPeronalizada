package com.example.ejlistaperonalizada

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    /** TODO
     * realiar una lista personalizada en la que se vean las provincias y unas fotos con lo que este asociado
     * se debe usar un adaptador personalizado-> ejercicios de referencia son: 08 spinner personalizado
     *                                                                        05 listas
     *
     * primero es necesario crear como quieres que se vea cada fila de la lista personalizada layout-> fila_lista
     */
    private val listadoCiudades= arrayOf("leon", "valladolid", "pucela", "desdees")
    private val listadoFotosCiudad = intArrayOf(R.drawable.leon, R.drawable.valladolid, R.drawable.pucela, R.drawable.desde)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tras asociar las vistas con su correspondiente se crea el layout
        val listaCiudades = findViewById<ListView>(R.id.listView_listadoCiudades)
        val textViewCiudadSeleccionada = findViewById<TextView>(R.id.textView_ciudadSeleccionada)
        // se establece el adaptadorPersonalizado-> que debe ser una clase interna para que pueda acceder
        // a los datos de los array y a las vistas definidas
      //  val listaProvincias:Array<String> = arrayOf(R.string.arrayCiudades)
val adaptadorPersonalizado = AdaptadorPersonalizado(this, R.layout.fila_lista,listadoCiudades)

    }
// adaptador personalizado con los paramatros que requiere el arrayAdapter correspondiente que es el que hereda
    private inner class AdaptadorPersonalizado(
    // se definen los parametros del adaptador personlizado que son los mismo que los del que hereda
    contexto: Context,
    recursos: Int,
    objetos: Array<String>
    ): ArrayAdapter<String>(contexto, recursos, objetos){// la herencia de la clase con sus parametros
        // dentro de esta clase lo que quieres que haga
        // en una adaptador se pasa:el contexto, la vista y la coleccion

        ///// CREO-PIENSO-IMAGINO////
        private fun crearFilaPersonalizada(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {

            // Crea un objeto LayoutInflater para inflar la vista personalizada desde un diseño XML
            // inflar un layout es dada la propiedad del layout meter datos
            // contexto es la vista que viene de la clase interna
            val layoutInflater = LayoutInflater.from(context)

            //Declaro una vista de mi fila, y la preparo para inflarla con datos
            // Los parametros son: XML descriptivo
            // Vista padre
            // Booleano que indica si se debe ceñir a las características del padre
            val rowView = convertView ?: layoutInflater.inflate(R.layout.fila_lista, parent, false)
            /**lo de arriba es una ternaria y hace lo siguiente
             *  si converView existe coge el inflador y lo infla-> esto lo dice en el ?:
             *  lo infla con el recurso( que es el spinner que se va a ver, cada elemento) el padre y
             *  si se quiere que sea igual que el padre( que no-> false)
             */

            //Fijamos el nombre de la ciudad
            rowView.findViewById<TextView>(R.id.textView_nombreCiudad).text = listadoCiudades[position]


            //Fijamos la imagen de la ciudad
            rowView.findViewById<ImageView>(R.id.imageView_ciudad).setImageResource(listadoFotosCiudad[position])

            // Devuelve la vista de fila personalizada
            return rowView
        }
    }
}