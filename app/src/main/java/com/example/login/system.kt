package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.example.login.databinding.ActivitySystemBinding

class system : templateMain() {

    lateinit var contentView:ActivitySystemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user=intent.getStringExtra(EXTRA_MESSAGE)

        contentView= ActivitySystemBinding.inflate(layoutInflater)
        contentView.lblUser.text=user

        setContentView(contentView.root)

    }

}