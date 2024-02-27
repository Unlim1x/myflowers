package ru.unlim1x.myflowersclassic.presentation

import android.widget.ImageView

interface OnImageViewLoadRequestListener {
    fun requestDrawable(viewId: Int, parameter : Int)
}