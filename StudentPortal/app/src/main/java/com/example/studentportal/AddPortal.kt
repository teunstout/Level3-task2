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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        savePortal.setOnClickListener { onSavePortal() }
    }

    /**
     * save the portal
     */
    private fun onSavePortal() {
        val titleInput = txtTitleUrl.text.toString()
        val urlInput = inHttp.text.toString()

        if (checkInputsOfView(urlInput, titleInput)) {
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
        if (!url.startsWith("http://www.")) {
            toastMessage(getString(R.string.mustStartWithHttp))
            return false
        }
        val splittedArray = url.split(".") // should be array of 3 after split

        // check if after http there is a string
        if (splittedArray[1].isEmpty()) {
            toastMessage(getString(R.string.emptyString, "URL"))
            return false
        }

        // check if it ends with domain ex. .com
        if (splittedArray[2].isEmpty()) {
            toastMessage(getString(R.string.mustEndWithHttp))
            return false
        }

        return true
    }

    /**
     * Toast message; message is the one you give with it
     */
    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Go back by finishing intent
     */
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
