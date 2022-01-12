package com.example.login

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
                Log.i("Message","Touch en Perfil")
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