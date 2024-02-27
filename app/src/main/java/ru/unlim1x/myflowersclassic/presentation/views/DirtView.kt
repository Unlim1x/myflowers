package ru.unlim1x.myflowersclassic.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import ru.unlim1x.myflowersclassic.R
import java.lang.Integer.min


class DirtView(context: Context, attr:AttributeSet?) : View(context, attr) {
    var rect_dirt = RectF(0f,0f, width.toFloat(), height.toFloat())
    val brown = Paint()
    private var customColor : Int = resources.getColor(R.color.Peru, null)

    init {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.DirtView)
        rect_dirt = RectF(0f,0f, 100f, 50f)
        customColor = typedArray.getColor(R.styleable.DirtView_customColor,  resources.getColor(R.color.Peru, null))
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 100 // Предполагаемая ширина View
        val desiredHeight = 50 // Предполагаемая высота View

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize // Задан конкретный размер для ширины
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize) // Размер не должен превышать заданный размер
            else -> desiredWidth // Задать предпочтительный размер, если точного или максимального размера не задано
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize // Задан конкретный размер для высоты
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize) // Размер не должен превышать заданный размер
            else -> desiredHeight // Задать предпочтительный размер, если точного или максимального размера не задано
        }

        setMeasuredDimension(width, height) // Устанавливаем фактический размер View
    }

    override fun onDraw(canvas: Canvas?) {

        brown.color = customColor
        canvas?.drawArc(rect_dirt, 180f, 180f, true, brown)
        super.onDraw(canvas)
    }
}