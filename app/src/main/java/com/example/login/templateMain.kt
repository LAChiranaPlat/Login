package com.example.login

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

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
                val prefManager=PreferenceManager.getDefaultSharedPreferences(this)
                val manager=prefManager.edit()

                manager.remove("user")
                manager.remove("log")
                manager.remove("foto")
                manager.apply()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                 true
            }
             else->super.onOptionsItemSelected(item)
        }

    }

}