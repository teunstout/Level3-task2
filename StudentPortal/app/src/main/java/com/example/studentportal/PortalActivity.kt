package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.logging.Logger

const val ADD_PORATL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private val urlList = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(urlList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // basic portals
        urlList.add(
            Portal(
                "Task 3 Level 2",
                "https://www.android-development.app/level-3-multi-screen-app/level3-task2"
            )
        )
        urlList.add(
            Portal(
                "Task 3 Level 1",
                "www.android-development.app/level-3-multi-screen-app/level3-task1"
            )
        ) //https://
        urlList.add(
            Portal(
                "Task 3 Example",
                "https://www.android-development.app/level-3-multi-screen-app/level3-example"
            )
        )

        initView()
    }

    private fun initView() {
        rvPortal.layoutManager = StaggeredGridLayoutManager(2, 1)
        rvPortal.adapter = portalAdapter

        fab.setOnClickListener { startActivity() }
    }

    /**
     * start adding a portal
     */
    private fun startActivity() {
        val intent = Intent(this, AddPortal::class.java)
        startActivityForResult(intent, ADD_PORATL_REQUEST_CODE)
    }

    /**
     * when result is done add it to the list and call adapter with change
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORATL_REQUEST_CODE -> {
                    if (data != null) {
                        val portaldata = data.getParcelableExtra<Portal>(EXTRA_PORTAL)
                        urlList.add(portaldata)
                        portalAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
