package com.example.arduinocar

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hp.bluetoothjhr.BluetoothJhr

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

// Check to see if the Bluetooth classic feature is available.
        val bluetoothAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)

// Check to see if the BLE feature is available.
        val bluetoothLEAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

        val lista_dispositivos=findViewById<ListView>(R.id.lista_dispositivos)
        val bluetoothJhr= BluetoothJhr(this,lista_dispositivos)
        bluetoothJhr.EncenderBluetooth()
        lista_dispositivos.setOnItemClickListener { adapterView, view, i, l ->
            bluetoothJhr.Disp_Seleccionado(view,i,MainActivity2::class.java)
        }
    }
}