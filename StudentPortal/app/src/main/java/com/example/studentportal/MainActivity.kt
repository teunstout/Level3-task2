package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception
import java.util.logging.Logger
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val linkList = arrayListOf<Portal>()
    val portalAdapter = PortalAdapter(linkList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        for ((index, value) in Portal.links.withIndex())
            linkList.add(Portal(Portal.title[index], value))

        initView()
    }

    private fun initView() {
        rvPortal.layoutManager = StaggeredGridLayoutManager(2, 1)
        rvPortal.adapter = portalAdapter

        fab.setOnClickListener {
            startActivity(Intent(this, PortalActivity::class.java))
        }
    }
}
