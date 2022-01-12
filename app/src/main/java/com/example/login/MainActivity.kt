package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var contentView:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView= ActivityMainBinding.inflate(layoutInflater)

        setContentView(contentView.root)

        val btnAcces=contentView.btnIntro

        val txtUser=contentView.txtusernick.editText
        val txtPassUser=contentView.txtpassuser.editText

        btnAcces.setOnClickListener { v->
            val user=txtUser?.text.toString()
            val pass=txtPassUser?.text.toString()

            if(user=="huarseral" && pass=="developer21"){

                val intent= Intent(this,system::class.java).apply{
                    putExtra(EXTRA_MESSAGE,user)
                }

                startActivity(intent)

            }else{

            }


        }

    }

}