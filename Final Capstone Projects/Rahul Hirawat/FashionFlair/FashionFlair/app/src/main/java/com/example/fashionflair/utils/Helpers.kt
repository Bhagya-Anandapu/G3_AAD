package com.example.fashionflair.utils

import android.view.View
import androidx.appcompat.widget.AppCompatButton

fun AppCompatButton.setLabel(label: String) {
    this.text = label
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}