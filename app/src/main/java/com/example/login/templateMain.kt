package com.example.login

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class templateMain: AppCompatActivity()  {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflador=menuInflater

        inflador.inflate(R.menu.main,menu)

        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         return when(item.itemId){
            R.id.idPerfil ->{

                val intent= Intent(this, profile::class.java)

                startActivity(intent)

                true
            }
            R.id.idConfig ->{
                Log.i("Message","Touch en Config")
                 true
            }
            R.id.idClose ->{
                Log.i("Message","Touch en Salir")
                 true
            }
             else->super.onOptionsItemSelected(item)
        }

    }

}