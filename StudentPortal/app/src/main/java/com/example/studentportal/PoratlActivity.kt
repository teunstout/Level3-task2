package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORATL_REQUEST_CODE = 100;

class MainActivity : AppCompatActivity() {
    val urlList = arrayListOf<Portal>()
    val portalAdapter = PortalAdapter(urlList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        for ((index, value) in Portal.links.withIndex())
            urlList.add(Portal(Portal.title[index], value))

        initView()
    }

    private fun initView() {
        rvPortal.layoutManager = StaggeredGridLayoutManager(2, 1)
        rvPortal.adapter = portalAdapter

        fab.setOnClickListener {
            startActivity()
        }

        val portal = intent.getParcelableExtra<Portal>(EXTRA_PORTAL)

        if (portal != null){
            urlList.add(portal)
        }

        portalAdapter.notifyDataSetChanged()
    }

    private fun startActivity() {
        val intent = Intent(this, AddPortal::class.java)
        startActivityForResult(intent, ADD_PORATL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORATL_REQUEST_CODE -> {
                    if (data != null) {
                        val portaldata = data.getParcelableExtra<Portal>(EXTRA_PORTAL) as Portal
                        urlList.add(portaldata)
                        portalAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
