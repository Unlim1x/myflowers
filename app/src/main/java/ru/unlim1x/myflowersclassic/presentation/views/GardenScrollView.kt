package ru.unlim1x.myflowersclassic.presentation.views


import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnDragListener
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.core.widget.NestedScrollView
import ru.unlim1x.myflowersclassic.R


class GardenScrollView(context: Context, attr: AttributeSet?) : NestedScrollView(context, attr),
    OnDragListener {
    private var counter = 1
    private lateinit var parentLayout: ConstraintLayout
    private var listOfIDs = mutableListOf<Int>()


    override fun onDrag(v: View?, event: DragEvent?): Boolean {

        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                this.findViewById<TextView>(R.id.scroll_view_plus_text).visibility = View.VISIBLE
            }

            DragEvent.ACTION_DROP -> {
                this.findViewById<TextView>(R.id.scroll_view_plus_text).visibility = View.INVISIBLE
                this.setBackgroundColor(resources.getColor(R.color.DarkGreen, null))
                plant(this)
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                this.findViewById<TextView>(R.id.scroll_view_plus_text).visibility = View.INVISIBLE
                this.setBackgroundColor(resources.getColor(R.color.DarkGreen, null))
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                this.setBackgroundColor(resources.getColor(R.color.OliveDrab, null))
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                this.findViewById<TextView>(R.id.scroll_view_plus_text).visibility = View.INVISIBLE
                this.setBackgroundColor(resources.getColor(R.color.DarkGreen, null))

            }

        }
        return true
    }


    fun plant(view: View) {
        if (counter < 22) {
            parentLayout = (parent as CoordinatorLayout)
                .findViewById(R.id.ConstraintLayoutInScrollViewGarden)      //.findViewById<ConstraintLayout>(R.id.ConstraintLayoutInScrollViewGarden)
            //val dirtView = DirtView(context = requireContext(), null)
            val dirtView =
                LayoutInflater.from(context).inflate(R.layout.flower_garden_item, null)
            dirtView.id = View.generateViewId()
            listOfIDs.add(dirtView.id)
            dirtView.findViewById<TextView>(R.id.flower_garden_card_name).text =
                "Цветок ${counter++ - 1}"
            dirtView.layoutParams = getLayoutParams(view, listOfIDs.size)

            parentLayout.addView(dirtView)
            updateParams()
            this.post{
                fullScroll(View.FOCUS_DOWN)
            }
            Log.e("KEK", "ID: ${dirtView.id}")

            val nestedScrollView = view.findViewById<NestedScrollView>(R.id.scrollViewInGarden)
            Log.e("KEK", "nestedscrollviewHeight: ${nestedScrollView.height}")
        }
    }

    private fun getLayoutParams(view: View, id: Int): ConstraintLayout.LayoutParams {
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        if (id % 3 == 1) {
            layoutParams.leftMargin = 12
            layoutParams.startToStart = parentLayout.id
            layoutParams.rightToRight  = parentLayout.id
        }
        if (id % 3 == 2) {
            layoutParams.leftToRight = view.findViewById<CardView>(listOfIDs[id - 2]).id
            layoutParams.rightToRight = parentLayout.id
        }
        if (id < 4)
            layoutParams.topToTop = parentLayout.id
        else
            layoutParams.topToBottom = view.findViewById<CardView>(listOfIDs[id - 4])?.id!!
        if (id % 3 == 0) {
            layoutParams.endToEnd = parentLayout.id
            layoutParams.rightMargin = 12
        }


        layoutParams.topMargin = 12
        return layoutParams
    }

    private fun updateParams() {
        parentLayout.children.forEach {
            if (listOfIDs.contains(it.id)) {
                val id = listOfIDs.indexOf(it.id)
                if (id % 3 == 2) {
                    val dirtViewRight = findViewById<CardView>(listOfIDs[id])
                    val dirtViewCenter = findViewById<CardView>(listOfIDs[id-1])
                    if (dirtViewRight != null && dirtViewCenter != null) {
                        val layoutParams: ConstraintLayout.LayoutParams =
                            dirtViewCenter.layoutParams as ConstraintLayout.LayoutParams
                        layoutParams.rightToLeft = dirtViewRight.id
                        dirtViewCenter.layoutParams = layoutParams
                    }
                }
                if (id % 3 == 1) {
                    val dirtViewCenter = findViewById<CardView>(listOfIDs[id])
                    val dirtViewLeft = findViewById<CardView>(listOfIDs[id-1])
                    if (dirtViewCenter != null && dirtViewLeft != null) {
                        val layoutParams: ConstraintLayout.LayoutParams =
                            dirtViewLeft.layoutParams as ConstraintLayout.LayoutParams
                        layoutParams.rightToLeft = dirtViewCenter.id

                        dirtViewLeft.layoutParams = layoutParams
                    }
                }
            }
        }
    }
}