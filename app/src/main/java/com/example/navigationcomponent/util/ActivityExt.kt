package com.example.navigationcomponent.util

import android.app.Activity
import android.widget.Toast

fun Activity.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}