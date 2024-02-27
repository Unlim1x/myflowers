package ru.unlim1x.myflowersclassic.presentation

import android.view.DragEvent
import android.view.View

interface InventoryListener : View.OnDragListener {
    fun pressed(prev:Int, chosen:Boolean)
    override fun onDrag(v: View?, event: DragEvent?): Boolean
}