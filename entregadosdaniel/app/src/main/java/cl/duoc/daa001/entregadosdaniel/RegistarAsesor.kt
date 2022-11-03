package cl.duoc.daa001.entregadosdaniel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RegistarAsesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar_asesor)

        findViewById<Button>(R.id.btnCrearAsesor_registrar).setOnClickListener {

            crearAsesor(this)

            val i = Intent(this, loginAsesor::class.java)
            startActivity(i)

            //Toast.makeText(this, "Asesor Creado", Toast.LENGTH_SHORT).show()
        }

    }




    private fun crearAsesor(context: Context) {

        val rut = findViewById<TextView>(R.id.txtRut_registrar).text.toString()
        val nombreCompleto = findViewById<TextView>(R.id.txtNombreCompleto_registrar).text.toString()
        val correo = findViewById<TextView>(R.id.txtCorreo_registrar).text.toString()
        val password = findViewById<TextView>(R.id.txtPassword_registrar).text.toString()
        val token = "10020015"

        val mediaType: MediaType? = MediaType.parse("application/json; charset=utf-8")
        var json = "{ \"rut\": \""+ rut +"\""+
                ", \"nombre_completo\": \""+ nombreCompleto +"\""+
                ", \"correo\": \""+ correo+"\""+
                ", \"password\": \""+ password +"\""+
                ", \"token_equipo\": \""+ token +"\""+
                "}"

        val client = OkHttpClient()
        val body: RequestBody = RequestBody.create(mediaType, json)
        val url = "https://duoc.grupodevcon.cl/API/v2/crearAsesor"
        val request: Request = Request.Builder().url(url).post(body).build()

        client.newCall(request).enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("SVL: Error: "+ e.message)
            }

            override fun onResponse(call: Call, response: Response) {

                val jsonData = response.body()!!.string()
                val json= JSONObject(jsonData)
                val resultado = json.getString("result")

                // Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
                procesarRespuesta(context, resultado)

            }
        })
    }

    fun procesarRespuesta( context: Context?, msg: String?) {
        if (context != null && msg != null) {
            Handler(Looper.getMainLooper()).post(Runnable {
                Toast.makeText(
                    context,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
    }
}