package com.ir.smkcoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.onClick {
            val intent = Intent(this@MainActivity,listmovieActivity::class.java)
            startActivity(intent)
        }

        btn2.onClick{
            val intent = Intent(this@MainActivity,ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
