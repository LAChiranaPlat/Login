package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.login.databinding.ActivitySystemBinding

class system : templateMain() {

    lateinit var contentView:ActivitySystemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView= ActivitySystemBinding.inflate(layoutInflater)
        Log.i("result","Uhmmmmmmm")
        var user=intent.getStringExtra(EXTRA_MESSAGE)
        var imgRes=intent.getStringExtra("avatar").toString()
        Log.i("result","En System: Link=https://geniomaticrodas.edu.pe/resources/${imgRes}")

        var res:ImageView?=contentView.imageView2 as ImageView?
        var resImage="https://geniomaticrodas.edu.pe/resources/${imgRes}"
        res?.setImage(resImage)

        contentView.lblUser.text=user

        setContentView(contentView.root)

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