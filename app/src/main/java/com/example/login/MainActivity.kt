package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var contentView:ActivityMainBinding
    lateinit var colaPeticion:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        colaPeticion=Volley.newRequestQueue(this)

        contentView= ActivityMainBinding.inflate(layoutInflater)

        setContentView(contentView.root)

        val btnAcces=contentView.btnIntro

        btnAcces.setOnClickListener { v->

            val nick=contentView.txtusernick.editText?.text.toString()
            val pass=contentView.txtpassuser.editText?.text.toString()

            if(nick.isEmpty()){
                Toast.makeText(this,"Ingrese su nombre de usuario",Toast.LENGTH_LONG).show()
            }else{
                val url="https://geniomaticrodas.edu.pe/resources/JsonObjectRequest.php?nick=${nick}&pass=${pass}"

                val query=JsonObjectRequest(
                    Request.Method.GET,
                    url,null,
                    Response.Listener { resp->
                        Log.i("result",resp.toString())

                        if(resp.get("registros").equals(1))
                        {
                            Log.i("result","Lanza la otra Activity")

                           contentView.txtpassuser.visibility= View.VISIBLE

                            val res:ImageView?=contentView.avatar as ImageView?
                            val resImage="https://geniomaticrodas.edu.pe/resources/${resp.get("profile")}"
                            res?.setImage(resImage)

                            val intent= Intent(this,system::class.java)

                            intent.putExtra(EXTRA_MESSAGE,"${resp.get("name")} ${ resp.get("lname")}")
                            intent.putExtra("avatar",resp.get("profile").toString())
                            startActivity(intent)
                            finish()


                        }else{
                            Toast.makeText(this,"Usuario incorrecto",Toast.LENGTH_LONG).show()
                        }


                    },
                    Response.ErrorListener { eResp->
                            Log.i("result","Error: ${eResp.message.toString()}")
                }
                    
                )

                colaPeticion.add(query)
            }


        }

    }

    fun ImageView.setImage(url: String){
            Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_error_24)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
            this.setBackgroundResource(R.drawable.back)
    }
}