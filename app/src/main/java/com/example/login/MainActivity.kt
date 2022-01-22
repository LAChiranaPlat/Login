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

    lateinit var userDB:String;
    lateinit var passDB:String;

    lateinit var nUser:String;
    lateinit var lnUser:String;

    lateinit var avatarDB:String;

    var login:Int=1;
    var intentosLogin:Int =0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        colaPeticion=Volley.newRequestQueue(this)

        contentView= ActivityMainBinding.inflate(layoutInflater)


        setContentView(contentView.root)

        val btnAcces=contentView.btnIntro

        btnAcces.setOnClickListener { v->

            if(login.equals(1)){
                val nick=contentView.txtusernick.editText?.text.toString()

                if(nick.isEmpty()){
                    Toast.makeText(this,"Ingrese su nombre de usuario",Toast.LENGTH_LONG).show()
                }
                else{
                    val url="https://geniomaticrodas.edu.pe/resources/JsonObjectRequest.php?nick=${nick}"

                    val query=JsonObjectRequest(
                        Request.Method.GET,
                        url,null,
                        Response.Listener { resp->
                            Log.i("result",resp.toString())

                            if(resp.get("registros").equals(1))
                            {

                                contentView.txtpassuser.visibility= View.VISIBLE
                                contentView.txtpassuser.requestFocus()

                                userDB=nick
                                passDB=resp.get("pass").toString()
                                avatarDB=resp.get("profile").toString()
                               // nUser=resp.get("nombres").toString()
                               // lnUser=resp.get("apellidos").toString()
                                login=2

                                val res:ImageView?=contentView.avatar as ImageView?
                                val resImage="https://geniomaticrodas.edu.pe/resources/${resp.get("profile")}"
                                res?.setImage(resImage)

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

            }else if(login.equals(2)){

                val pass=contentView.txtpassuser.editText?.text.toString()

                if(pass.isEmpty()){
                    Toast.makeText(this,"Ingrese su clave de acceso",Toast.LENGTH_LONG).show()
                }else{
                    if(pass.equals(passDB)){
                        val intent= Intent(this,system::class.java)

                        intent.putExtra(EXTRA_MESSAGE,"${nUser},${lnUser} ")
                        intent.putExtra("avatar",avatarDB)
                        startActivity(intent)
                        finish()
                    }else{
                        contentView.txtusernick.visibility=View.VISIBLE
                        contentView.txtpassuser.visibility=View.GONE
                        Toast.makeText(this,"Contraseñar Incorrecta",Toast.LENGTH_SHORT).show()
                    }
                }
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