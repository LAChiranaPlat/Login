package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.login.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var contentView:ActivityMainBinding
    lateinit var colaPeticion:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView= ActivityMainBinding.inflate(layoutInflater)

        setContentView(contentView.root)

        val btnAcces=contentView.btnIntro

        val txtUser=contentView.txtusernick.editText
        val txtPassUser=contentView.txtpassuser.editText

        colaPeticion=Volley.newRequestQueue(this)

        var url="http://192.168.1.50:90/myApp/save.php"

        btnAcces.setOnClickListener { v->

            val datosWeb=HashMap<String,Any?>()
            datosWeb["name"]=txtUser?.text.toString()
            datosWeb["dni"]="999999"
            val _jsonReq=JSONObject(datosWeb)

            val petObj=JsonObjectRequest(
                Request.Method.POST,
                url,
                _jsonReq,
                Response.Listener { response ->
                    Log.i("result","Size: "+response.length())
                    Log.i("result",response.get("proceso").toString())
                    Log.i("result",response.get("message").toString())
                },
                Response.ErrorListener { response ->
                    Log.i("result","Error: "+response.message.toString())
                }
            )

            colaPeticion.add(petObj)


            /*
            val user=txtUser?.text.toString()
            val pass=txtPassUser?.text.toString()

            if(user=="huarseral" && pass=="developer21"){

                val intent= Intent(this,system::class.java).apply{
                    putExtra(EXTRA_MESSAGE,user)
                }

                startActivity(intent)

            }else{

            }
            */



        }

    }

}