package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_portal.*

class PortalActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portal)
        setSupportActionBar(toolbar)

        initView()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        savePortal.setOnClickListener { onSavePortal() }
    }

    private fun onSavePortal() {
        val titleInput = txtTitleUrl.text.toString()
        val urlInput = inHttp.text.toString()
        val inputsValidTrue = checkInputsOfView(urlInput, titleInput)

        if (inputsValidTrue) {
            val portalActivityIntent = Intent(this, PortalAdapter::class.java)
                .putExtra(PORTAL_EXTRA, Portal(titleInput, urlInput))

            startActivity(portalActivityIntent)
        }

    }

    /**
     * check if the inputs are filled in correct
     */
    private fun checkInputsOfView(url: String, title: String): Boolean {
        var stringWithoutHttp = "";

        // check if url is empty
        if (title.isEmpty()) {
            toastMessage(getString(R.string.emptyString, "Title"))
            return false
        }

        // check if url starts with http
        if (!url.startsWith("http://")) {
            toastMessage(getString(R.string.mustStartWithHttp))
            return false
        }

        // check if after http there is a string
        stringWithoutHttp = url.split("http://")[1]
        if (stringWithoutHttp.isEmpty()) {
            toastMessage(getString(R.string.emptyString, "URL"))
            return false
        }

        return true
    }

    /**
     * Toast message; message is the one you give with it
     */
    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Back button works
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
