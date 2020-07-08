package com.arbaelbarca.githubsearchapps.baseui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }
}
