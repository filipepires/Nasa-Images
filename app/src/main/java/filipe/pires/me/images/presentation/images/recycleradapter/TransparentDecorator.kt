package filipe.pires.me.images.presentation.images.recycleradapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class TransparentDecorator(
        private val padding: Int,
        private val gridSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val frameWidth = ((parent.width - padding.toFloat() * (gridSize - 1)) / gridSize).toInt()
        val padding = parent.width / gridSize - frameWidth
        outRect.top = padding
        outRect.left = padding
        outRect.right = padding
        outRect.bottom = padding
    }
}