package com.teams.vidhividhan.utils

import android.content.Context
import android.widget.Toast

object MyViewutils {

    fun showToast(context: Context?, message: String?) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}