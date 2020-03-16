package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortal : AppCompatActivity() {

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
            val portalActivityIntent = Intent().putExtra(EXTRA_PORTAL, Portal(titleInput, urlInput))
            setResult(Activity.RESULT_OK, portalActivityIntent)
            finish()
        }

    }

    /**
     * check if the inputs are filled in correct
     */
    private fun checkInputsOfView(url: String, title: String): Boolean {

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
        val stringWithoutHttp = url.split("http://")[1]
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
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
