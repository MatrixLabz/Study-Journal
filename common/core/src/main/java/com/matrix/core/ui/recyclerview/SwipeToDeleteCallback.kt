/* Android with clean and multi module architecture */
package com.matrix.core.ui.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.matrix.core.R

/**
 * Implementation of [ItemTouchHelper.Callback] that adds swipe support for the lesson list.
 */
class SwipeToDeleteCallback(
    private val context: Context,
    @DrawableRes private val resId: Int = R.drawable.ic_baseline_delete_24,
    private val color: Int = R.color.red_600,
    private val onSwiped: (Int) -> Unit
) : ItemTouchHelper.Callback() {

    private val paint = Paint().also { it.color = ContextCompat.getColor(context, color) }

    override fun getMovementFlags(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ) =
        makeMovementFlags(0, ItemTouchHelper.LEFT)

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT) {
            onSwiped(viewHolder.bindingAdapterPosition)
        }
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        drawBackground(c, viewHolder.itemView, dX)
        drawIcon(c, viewHolder.itemView)
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawBackground(canvas: Canvas, view: View, dX: Float) {
        val background = RectF(
            view.right + dX, view.top.toFloat(),
            view.right.toFloat(), view.bottom.toFloat()
        )
        canvas.drawRect(background, paint)
    }

    private fun drawIcon(canvas: Canvas, view: View) {
        ContextCompat.getDrawable(context, resId)?.toBitmap()?.let {
            canvas.drawBitmap(it, null, createContainer(view, calculateWidth(view)), paint)
        }
    }

    private fun createContainer(view: View, width: Float) = RectF(
        view.right - 2 * width, view.top + width,
        view.right - width, view.bottom - width
    )

    private fun calculateWidth(view: View, scale: Int = 3) =
        (view.bottom.toFloat() - view.top.toFloat()) / scale
}

/**
 * Set a callback to be invoked when this list item view is swiped.
 */
fun RecyclerView.setOnSwipedListener(context: Context, onSwiped: (Int) -> Unit) {
    ItemTouchHelper(SwipeToDeleteCallback(context, onSwiped = onSwiped))
        .attachToRecyclerView(this)
}
