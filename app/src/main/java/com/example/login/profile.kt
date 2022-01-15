package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class profile : templateMain() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val item=menu?.findItem(R.id.idPerfil)

        item?.setVisible(false)
        item?.isEnabled=false

        return super.onPrepareOptionsMenu(menu)
    }

}