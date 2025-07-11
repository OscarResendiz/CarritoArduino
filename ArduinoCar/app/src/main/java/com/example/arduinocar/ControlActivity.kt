package com.example.arduinocar

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hp.bluetoothjhr.BluetoothJhr

class ControlActivity : AppCompatActivity() {
    lateinit var bluetoothJhr: BluetoothJhr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_control)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //inicializo el bluethoop
        bluetoothJhr=BluetoothJhr(MainActivity::class.java,this)
        //declaro los botones a utilizar
        val flecha_arriba=findViewById<ImageView>(R.id.flecha_arriba)
        val flecha_izquierda=findViewById<ImageView>(R.id.flecha_izquierda)
        val flecha_derecha=findViewById<ImageView>(R.id.flecha_derecha)
        val flecha_stop=findViewById<ImageView>(R.id.flecha_stop)
        val flecha_abajo=findViewById<ImageView>(R.id.flecha_abajo)
        //genero los eventos de los botones
        flecha_arriba.setOnClickListener{
           // Toast.makeText(this, "se presionó Arriba", Toast.LENGTH_SHORT).show()
            bluetoothJhr.Tx("A") // Avanzar
        }

        flecha_izquierda.setOnClickListener{
//            Toast.makeText(this, "se presionó izquierda", Toast.LENGTH_SHORT).show()
            bluetoothJhr.Tx("I") //de Izquierda
        }

        flecha_derecha.setOnClickListener{
//            Toast.makeText(this, "se presionó derecha", Toast.LENGTH_SHORT).show()
            bluetoothJhr.Tx("D") //de Derecha
        }

        flecha_stop.setOnClickListener{
            //Toast.makeText(this, "se presionó stop", Toast.LENGTH_SHORT).show()
            bluetoothJhr.Tx("P") //de Parar

        }
        flecha_abajo.setOnClickListener{
            //Toast.makeText(this, "se presionó stop", Toast.LENGTH_SHORT).show()
            bluetoothJhr.Tx("R") //de Reversa

        }

    }
    override fun onResume() {
        super.onResume()
        bluetoothJhr.ConectaBluetooth()
        bluetoothJhr.ResetearRx()
    }

    override fun onPause() {
        super.onPause()
        bluetoothJhr.CierraConexion()
    }
}