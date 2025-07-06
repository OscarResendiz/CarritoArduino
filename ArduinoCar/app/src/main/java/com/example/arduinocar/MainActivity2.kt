package com.example.arduinocar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hp.bluetoothjhr.BluetoothJhr
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity() {

    lateinit var bluetoothJhr: BluetoothJhr
    var continuar:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bluetoothJhr=BluetoothJhr(MainActivity::class.java,this)

        val enviar=findViewById<Button>(R.id.enviar)
        val editor=findViewById<EditText>(R.id.editor)
        val consola=findViewById<TextView>(R.id.consola)
        enviar.setOnClickListener{
            var mensaje: String=editor.text.toString()
            bluetoothJhr.Tx(mensaje)
        }

        thread(start = true) {
            while(continuar)
            {
                Thread.sleep(1000)
                consola.text=bluetoothJhr.Rx()
            }
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
        continuar=false
    }
} 