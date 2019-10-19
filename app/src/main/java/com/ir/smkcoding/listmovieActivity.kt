package com.ir.smkcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_listmovie.*
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.tableLayout

class listmovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listmovie)

        vpage.adapter = tabLayoutAdapter(supportFragmentManager, this)

    tbl.setupWithViewPager(vpage)
    }
}
