package cl.duoc.daa001.entregadosdaniel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btnCrearAsesor_home).setOnClickListener {


            val i = Intent(this, RegistarAsesor::class.java)
            startActivity(i)
        }

        findViewById<Button>(R.id.btningresar_home).setOnClickListener {


            val i = Intent(this, loginAsesor::class.java)
            startActivity(i)
        }
    }
}