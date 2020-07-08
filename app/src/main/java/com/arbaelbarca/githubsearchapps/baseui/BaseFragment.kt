package com.arbaelbarca.githubsearchapps.baseui

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }
}
